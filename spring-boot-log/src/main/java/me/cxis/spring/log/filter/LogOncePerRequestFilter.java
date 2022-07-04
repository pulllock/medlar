package me.cxis.spring.log.filter;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Component
public class LogOncePerRequestFilter extends OncePerRequestFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(LogOncePerRequestFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(wrappedRequest, wrappedResponse);

        String method = request.getMethod();
        String uri = request.getRequestURI();
        String parameters = getRequestParameters(request);
        String ip = getIp(request);
        String contentType = request.getContentType();

        String requestBody;
        if (contentType != null && contentType.equals(MULTIPART_FORM_DATA_VALUE)) {
            requestBody = "MULTIPART_FORM_DATA";
        } else {
            requestBody = IOUtils.toString(wrappedRequest.getContentAsByteArray(), wrappedRequest.getCharacterEncoding());
        }

        LOGGER.info("Request, ip: {}, content type: {}, method: {}, URI: {}, parameters: {}, request body: {}", ip, contentType, method, uri, parameters, requestBody);

        String responseBody = IOUtils.toString(wrappedResponse.getContentAsByteArray(), wrappedResponse.getCharacterEncoding());
        LOGGER.info("Response, ip: {}, content type: {}, method: {}, URI: {}, parameters: {}, response body: {}", ip, contentType, method, uri, parameters, responseBody);

        wrappedResponse.copyBodyToResponse();
    }

    private String getIp(HttpServletRequest request) {
        String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
        if (ipFromHeader != null && ipFromHeader.length() > 0) {
            return ipFromHeader;
        }

        return request.getRemoteAddr();
    }

    private String getRequestParameters(HttpServletRequest request) {
        StringBuffer result = new StringBuffer();
        Enumeration<String> names = request.getParameterNames();
        if (names != null) {
            result.append("?");
        }

        while (names.hasMoreElements()) {
            if (result.length() > 1) {
                result.append("&");
            }

            String current = names.nextElement();
            result.append(current + "=");
            result.append(request.getParameter(current));
        }
        return result.toString();
    }
}
