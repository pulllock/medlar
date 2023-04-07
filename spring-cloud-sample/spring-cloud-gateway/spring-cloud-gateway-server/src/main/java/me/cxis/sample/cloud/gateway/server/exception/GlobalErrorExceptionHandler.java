package me.cxis.sample.cloud.gateway.server.exception;

import com.alibaba.fastjson2.JSON;
import me.cxis.sample.cloud.gateway.server.model.result.Result;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 自定义全局异常处理
 */
public class GlobalErrorExceptionHandler implements ErrorWebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        if (response.isCommitted()) {
            return Mono.error(ex);
        }

        Result<?> errorResult = new Result<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

        if (ex instanceof ResponseStatusException) {
            errorResult.setCode(((ResponseStatusException) ex).getStatusCode().value());
            errorResult.setMessage(ex.getMessage());
        }

        else if (ex instanceof GatewayException) {
            errorResult.setCode(((GatewayException) ex).getErrorCode().getCode());
            errorResult.setMessage(ex.getMessage());
        }

        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.setStatusCode(HttpStatus.OK);

        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            return bufferFactory.wrap(JSON.toJSONString(errorResult).getBytes(StandardCharsets.UTF_8));
        }));
    }
}
