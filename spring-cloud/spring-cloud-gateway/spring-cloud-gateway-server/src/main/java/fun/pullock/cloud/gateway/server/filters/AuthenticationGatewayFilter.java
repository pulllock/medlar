package fun.pullock.cloud.gateway.server.filters;

import fun.pullock.cloud.gateway.server.exception.ErrorCode;
import fun.pullock.cloud.gateway.server.json.Json;
import fun.pullock.cloud.gateway.server.model.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 鉴权认证过滤器
 */
public class AuthenticationGatewayFilter implements GlobalFilter, Ordered {

    private final static Logger LOGGER = LoggerFactory.getLogger(AuthenticationGatewayFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String requestUrl = request.getPath().value();
        if (requestUrl.equals("登录接口/注册接口/等等")) {
            return chain.filter(exchange);
        }

        String token = request.getHeaders().getFirst("tk");
        if (token == null || token.length() == 0) {
            LOGGER.info("Gateway authentication failed, token is empty");
            if (!response.getHeaders().containsKey(HttpHeaders.CONTENT_TYPE)) {
                response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            }

            Result<?> result = Result.error(ErrorCode.UNAUTHORIZED);

            return response.writeWith(Mono.just(
                    response.bufferFactory().wrap(
                            Json.toJsonString(result).getBytes(StandardCharsets.UTF_8)
                    )
            ));
        }

        // TODO 调用认证服务进行认证

        // 认证后，设置userId等信息到Header中
        request = request.mutate()
                .header("uid", "123456")
                .build();
        return chain.filter(exchange.mutate().request(request).build());
    }

    @Override
    public int getOrder() {
        return -99;
    }
}
