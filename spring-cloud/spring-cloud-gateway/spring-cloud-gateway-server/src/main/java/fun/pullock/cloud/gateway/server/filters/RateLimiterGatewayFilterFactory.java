package fun.pullock.cloud.gateway.server.filters;

import fun.pullock.cloud.gateway.server.exception.ErrorCode;
import fun.pullock.cloud.gateway.server.exception.GatewayException;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.RequestRateLimiterGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.HttpStatusHolder;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.setResponseStatus;

/**
 * 自定义限流过滤器，返回错误信息
 *
 * Spring Cloud Gateway自带的限流过滤器工厂会返回响应码429，这里参考{@link RequestRateLimiterGatewayFilterFactory}的代码，
 * 将返回结果包装成自定义格式的错误信息进行返回
 */
public class RateLimiterGatewayFilterFactory extends RequestRateLimiterGatewayFilterFactory {

    private static final String EMPTY_KEY = "____EMPTY_KEY__";

    public RateLimiterGatewayFilterFactory(RateLimiter defaultRateLimiter, KeyResolver defaultKeyResolver) {
        super(defaultRateLimiter, defaultKeyResolver);
    }

    @Override
    public GatewayFilter apply(Config config) {
        KeyResolver resolver = getOrDefault(config.getKeyResolver(), getDefaultKeyResolver());
        RateLimiter<Object> limiter = getOrDefault(config.getRateLimiter(), getDefaultRateLimiter());
        boolean denyEmpty = getOrDefault(config.getDenyEmptyKey(), isDenyEmptyKey());
        HttpStatusHolder emptyKeyStatus = HttpStatusHolder
                .parse(getOrDefault(config.getEmptyKeyStatus(), getEmptyKeyStatusCode()));

        return (exchange, chain) -> resolver.resolve(exchange).defaultIfEmpty(EMPTY_KEY).flatMap(key -> {
            if (EMPTY_KEY.equals(key)) {
                if (denyEmpty) {
                    setResponseStatus(exchange, emptyKeyStatus);
                    return exchange.getResponse().setComplete();
                }
                return chain.filter(exchange);
            }
            String routeId = config.getRouteId();
            if (routeId == null) {
                Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
                routeId = route.getId();
            }
            return limiter.isAllowed(routeId, key).flatMap(response -> {

                for (Map.Entry<String, String> header : response.getHeaders().entrySet()) {
                    if (header.getKey().equals(RedisRateLimiter.REMAINING_HEADER) ||
                            header.getKey().equals(RedisRateLimiter.BURST_CAPACITY_HEADER) ||
                            header.getKey().equals(RedisRateLimiter.REPLENISH_RATE_HEADER) ||
                            header.getKey().equals(RedisRateLimiter.REQUESTED_TOKENS_HEADER)) {
                        continue;
                    }
                    exchange.getResponse().getHeaders().add(header.getKey(), header.getValue());
                }

                if (response.isAllowed()) {
                    return chain.filter(exchange);
                }

                setResponseStatus(exchange, config.getStatusCode());

                ServerHttpResponse httpResponse = exchange.getResponse();
                if (!httpResponse.getHeaders().containsKey(HttpHeaders.CONTENT_TYPE)) {
                    httpResponse.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                }

                return Mono.error(new GatewayException(ErrorCode.TOO_MANY_REQUESTS));
                /*Result<?> result = Result.error(ErrorCode.TOO_MANY_REQUESTS);

                return httpResponse.writeWith(Mono.just(
                        httpResponse.bufferFactory().wrap(
                                JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8)
                        )
                ));*/
            });
        });
    }



    private <T> T getOrDefault(T configValue, T defaultValue) {
        return (configValue != null) ? configValue : defaultValue;
    }
}
