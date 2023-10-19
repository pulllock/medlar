package fun.pullock.cloud.gateway.session;

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
        if (uid == null || uid.length() == 0) {
            return 0L;
        }

        return Long.valueOf(uid);
    }
}
