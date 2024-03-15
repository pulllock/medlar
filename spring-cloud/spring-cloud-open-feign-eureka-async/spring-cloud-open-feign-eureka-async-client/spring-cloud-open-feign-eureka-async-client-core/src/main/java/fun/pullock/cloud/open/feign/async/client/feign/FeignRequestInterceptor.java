package fun.pullock.cloud.open.feign.async.client.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;

public class FeignRequestInterceptor implements RequestInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeignRequestInterceptor.class);

    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            LOGGER.warn("Servlet request attributes empty");
        } else {
            HttpServletRequest request = requestAttributes.getRequest();
            Enumeration<String> enumeration = requestAttributes.getRequest().getHeaderNames();
            while (enumeration.hasMoreElements()) {
                String name = enumeration.nextElement();
                String value = request.getHeader(name);
                LOGGER.info("Header: {} = {}", name, value);
                template.header(name + "-from-client", value);
            }
        }
    }
}
