package com.github.tm_425006.webproject;

import com.github.tm_425006.webproject.bean.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionUtils {
    public static boolean verifySession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession() == null) {
            response.sendRedirect("login.jsp?error=Invalid session");
            return false;
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || user.getId() == null || user.getId() == -1) {
            response.sendRedirect("login.jsp?error=Invalid session");
            return false;
        }
        return true;
    }
}
