package fun.pullock.session.jdbc.mysql.user.session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Long userId = SessionTool.getUserIdFromSession();
        if (userId <= 0) {
            needLogin(response);
            return false;
        }

        return true;
    }

    private void needLogin(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try {
            response.getWriter().print("{\"code\":401,\"message\":\"Need Login!\"}");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
