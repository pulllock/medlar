package me.cxis.cloud.gateway.server.filters;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.util.List;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR;

/**
 * 全局日志过滤器
 *
 * 实现参考：
 * - {@link org.springframework.cloud.gateway.filter.factory.rewrite.ModifyRequestBodyGatewayFilterFactory}
 * - {@link org.springframework.cloud.gateway.filter.factory.rewrite.ModifyResponseBodyGatewayFilterFactory}
 *
 * TODO 实现还不完整
 */
public class LoggingGatewayFilter implements GlobalFilter, Ordered {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoggingGatewayFilter.class);

    public final List<HttpMessageReader<?>> messageReaders = HandlerStrategies.withDefaults().messageReaders();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        StringBuilder commonParamBuilder = new StringBuilder();
        String ip = getIp(request);
        commonParamBuilder.append(ip);
        commonParamBuilder.append(" ");
        commonParamBuilder.append("[");
        commonParamBuilder.append(request.getId());
        commonParamBuilder.append("] ");
        commonParamBuilder.append(request.getMethodValue());
        commonParamBuilder.append(" ");
        commonParamBuilder.append(request.getURI());


        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        String routeId = route.getId();
        commonParamBuilder.append(", routeId=");
        commonParamBuilder.append(routeId);
        commonParamBuilder.append(", referer=");
        commonParamBuilder.append("TODO");

        commonParamBuilder.append(", ");
        commonParamBuilder.append(request.getHeaders());

        MediaType mediaType = request.getHeaders().getContentType();
        LOGGER.info("mediaType: {}", mediaType);

        // request
        ServerRequest serverRequest = ServerRequest.create(exchange, messageReaders);
        // 获取原始request body中的内容，转为String类型
        // TODO 需要判断Content-Type
        Mono<String> requestBody = serverRequest.bodyToMono(String.class);
        Mono<String> modifiedBody = requestBody
                .flatMap(originalBody -> {
                    StringBuilder requestBuilder = new StringBuilder();
                    requestBuilder.append("Request ");
                    requestBuilder.append(commonParamBuilder);
                    requestBuilder.append(", payload=");
                    requestBuilder.append(originalBody);
                    LOGGER.info(requestBuilder.toString());
                    return Mono.just(originalBody);
                }).switchIfEmpty(Mono.defer(() -> {
                    StringBuilder requestBuilder = new StringBuilder();
                    requestBuilder.append("Request ");
                    requestBuilder.append(commonParamBuilder);
                    requestBuilder.append(", payload=");
                    requestBuilder.append("EMPTY_PAYLOAD");
                    LOGGER.info(requestBuilder.toString());
                    return Mono.empty();
                }));
        // 重新封装修改后的request body
        BodyInserter<Mono<String>, ReactiveHttpOutputMessage> bodyInserter = BodyInserters.fromPublisher(modifiedBody, String.class);
        HttpHeaders headers = new HttpHeaders();
        headers.putAll(exchange.getRequest().getHeaders());
        headers.remove(HttpHeaders.CONTENT_LENGTH);

        CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);
        ServerHttpRequestDecorator requestDecorator = decorateRequest(exchange, headers, outputMessage);


        // 打印response日志，并重新封装response
        ServerHttpResponseDecorator responseDecorator = decorateResponse(exchange, commonParamBuilder);

        return bodyInserter.insert(outputMessage, new BodyInserterContext())
                .then(Mono.defer(() -> chain.filter(exchange.mutate().request(requestDecorator).response(responseDecorator).build())));
    }

    private ServerHttpResponseDecorator decorateResponse(ServerWebExchange exchange, StringBuilder commonParamBuilder) {
        return new ServerHttpResponseDecorator(exchange.getResponse()) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                // TODO 需要判断Content-Type
                String originalResponseContentType = exchange.getAttribute(ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR);
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add(HttpHeaders.CONTENT_TYPE, originalResponseContentType);

                ClientResponse clientResponse = prepareClientResponse(body, httpHeaders);

                Mono<String> modifiedBody = clientResponse.bodyToMono(String.class)
                        .flatMap(originalBody -> {
                            StringBuilder responseBuilder = new StringBuilder();
                            responseBuilder.append("Response ");
                            responseBuilder.append(exchange.getResponse().getStatusCode().value());
                            responseBuilder.append(" ");
                            responseBuilder.append(commonParamBuilder);
                            responseBuilder.append(", payload=");
                            responseBuilder.append(originalBody);
                            LOGGER.info(responseBuilder.toString());
                            return Mono.just(originalBody);
                        });

                BodyInserter<Mono<String>, ReactiveHttpOutputMessage> bodyInserter = BodyInserters
                        .fromPublisher(modifiedBody, String.class);
                CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(
                        exchange,
                        exchange.getResponse().getHeaders()
                );
                return bodyInserter.insert(outputMessage, new BodyInserterContext())
                        .then(Mono.defer(() -> getDelegate().writeWith(DataBufferUtils.join(outputMessage.getBody()))));

            }

            @Override
            public Mono<Void> writeAndFlushWith(Publisher<? extends Publisher<? extends DataBuffer>> body) {
                return writeWith(Flux.from(body).flatMapSequential(p -> p));
            }

            private ClientResponse prepareClientResponse(Publisher<? extends DataBuffer> body, HttpHeaders httpHeaders) {
                ClientResponse.Builder builder;
                builder = ClientResponse.create(exchange.getResponse().getStatusCode());
                return builder.headers(headers -> headers.putAll(httpHeaders)).body(Flux.from(body)).build();
            }
        };
    }

    ServerHttpRequestDecorator decorateRequest(ServerWebExchange exchange, HttpHeaders headers,
                                               CachedBodyOutputMessage outputMessage) {
        return new ServerHttpRequestDecorator(exchange.getRequest()) {
            @Override
            public HttpHeaders getHeaders() {
                long contentLength = headers.getContentLength();
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.putAll(headers);
                if (contentLength > 0) {
                    httpHeaders.setContentLength(contentLength);
                }
                else {
                    httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
                }
                return httpHeaders;
            }

            @Override
            public Flux<DataBuffer> getBody() {
                return outputMessage.getBody();
            }
        };
    }


    @Override
    public int getOrder() {
        return -100;
    }

    private String getIp(ServerHttpRequest request) {
        String ipFromHeader = request.getHeaders().getFirst("X-FORWARDED-FOR");
        if (ipFromHeader != null && ipFromHeader.length() > 0) {
            return ipFromHeader;
        }

        InetAddress inetAddress = request.getRemoteAddress().getAddress();
        if (inetAddress != null) {
            return inetAddress.getHostAddress();
        }

        return "UNKNOWN_IP";
    }
}
