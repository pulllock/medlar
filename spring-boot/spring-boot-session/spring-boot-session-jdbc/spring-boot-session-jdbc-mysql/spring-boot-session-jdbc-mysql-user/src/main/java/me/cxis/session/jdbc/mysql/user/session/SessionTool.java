package me.cxis.session.jdbc.mysql.user.session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SessionTool {

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return null;
        }

        return attributes.getRequest();
    }

    public static HttpSession getSession() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        }

        return request.getSession();
    }

    public static void addUserIdToSession(Long userId) {
        getSession().setAttribute("USER_ID", userId);
    }

    public static Long getUserIdFromSession() {
        Object userIdFromSession = getSession().getAttribute("USER_ID");
        if (userIdFromSession == null) {
            return 0L;
        }

        return (Long) userIdFromSession;
    }
}
