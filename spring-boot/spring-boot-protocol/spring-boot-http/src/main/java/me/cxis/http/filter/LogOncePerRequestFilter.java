package me.cxis.http.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

        // user-agent
        String userAgent = request.getHeader("user-agent");
        LOGGER.info("Request: user-agent: {}", userAgent);

        // referer
        String referer = request.getHeader("referer");
        LOGGER.info("Request, referer: {}", referer);

        // accept
        String accept = request.getHeader("accept");
        LOGGER.info("Request, accept: {}", accept);

        // accept-charset
        String acceptCharset = request.getHeader("accept-charset");
        LOGGER.info("Request, accept-charset: {}", acceptCharset);

        // accept-encoding
        String acceptEncoding = request.getHeader("accept-encoding");
        LOGGER.info("Request, accept-encoding: {}", acceptEncoding);

        // accept-language
        String acceptLanguage = request.getHeader("accept-language");
        LOGGER.info("Request, accept-language: {}", acceptLanguage);

        // range
        String range = request.getHeader("range");
        LOGGER.info("Request, range: {}", range);

        response.setHeader("access-control-allow-credentials", "true");
        response.setHeader("access-control-allow-headers", "*");
        response.setHeader("access-control-allow-methods", "*");
        response.setHeader("access-control-allow-origin", "*");
        response.setHeader("access-control-max-age", "3600");

        // set-cookie
        Cookie setCookie = new Cookie("my-key", "my-value");
        response.addCookie(setCookie);

        // cookie
        String cookie = request.getHeader("cookie");
        LOGGER.info("Request, cookie: {}", cookie);

        // dofFilter
        filterChain.doFilter(request, response);

        // content-type
        String contentTypeResponse = response.getHeader("content-type");
        LOGGER.info("Response, content-type: {}", contentTypeResponse);

        // content-length
        String contentLengthResponse = response.getHeader("content-length");
        LOGGER.info("Response, content-length: {}", contentLengthResponse);

        // accept-ranges
        String acceptRanges = response.getHeader("accept-ranges");
        LOGGER.info("Response, accept-ranges: {}", acceptRanges);

        // access-control-allow-credentials
        String accessCredentials = response.getHeader("access-control-allow-credentials");
        LOGGER.info("Response, access-control-allow-credentials: {}", accessCredentials);

        // access-control-allow-headers
        String accessHeaders = response.getHeader("access-control-allow-headers");
        LOGGER.info("Response, access-control-allow-headers: {}", accessHeaders);

        // access-control-allow-methods
        String accessMethods = response.getHeader("access-control-allow-methods");
        LOGGER.info("Response, access-control-allow-methods: {}", accessMethods);

        // access-control-allow-origin
        String accessOrigin = response.getHeader("access-control-allow-origin");
        LOGGER.info("Response, access-control-allow-origin: {}", accessOrigin);

        // access-control-max-age
        String accessAge = response.getHeader("access-control-max-age");
        LOGGER.info("Response, access-control-max-age: {}", accessAge);
    }
}
