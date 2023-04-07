package me.cxis.cloud.gateway.session;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

public class SessionTool {

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return null;
        }

        return attributes.getRequest();
    }

    public static Long getUid() {
        String uid = getRequest().getHeader("uid");
        if (StringUtils.isEmpty(uid)) {
            return 0L;
        }

        return Long.valueOf(uid);
    }
}
