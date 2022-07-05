package me.cxis.http.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOncePerRequestFilter extends OncePerRequestFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(LogOncePerRequestFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // remote addr
        String clientIp = request.getRemoteAddr();
        LOGGER.info("Request, client ip: {}", clientIp);

        // x-forwarded-for
        String xff = request.getHeader("x-forwarded-for");
        LOGGER.info("Request, x-forwarded-for: {}", xff);

        if (xff != null && xff.length() > 0) {
            clientIp = xff.split(",")[0];
        }
        LOGGER.info("Request, real client ip: {}", clientIp);

        // content-type
        String contentTypeRequest = request.getHeader("content-type");
        LOGGER.info("Request, content-type: {}", contentTypeRequest);

        // content-length
        String contentLengthRequest = request.getHeader("content-length");
        LOGGER.info("Request, content-length: {}", contentLengthRequest);

        filterChain.doFilter(request, response);

        // content-type
        String contentTypeResponse = response.getHeader("content-type");
        LOGGER.info("Response, content-type: {}", contentTypeResponse);

        // content-length
        String contentLengthResponse = response.getHeader("content-length");
        LOGGER.info("Response, content-length: {}", contentLengthResponse);
    }
}
