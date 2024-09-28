package com.github.tm_425006.webproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ControllerUtils {
    public static boolean validateAction(HttpServletRequest request, HttpServletResponse response,
                                         List<String> supportedActions) throws IOException {
        if (!request.getParameterMap().containsKey("action")) {
            response.sendRedirect("error.jsp?error=No action specified");
            return false;
        }
        String action = request.getParameter("action").trim().toLowerCase(Locale.ROOT);
        boolean actionSupported = false;
        for (String supportedAction : supportedActions) {
            if (action.equalsIgnoreCase(supportedAction)) {
                actionSupported = true;
                break;
            }
        }
        if (!actionSupported) {
            response.sendRedirect("error.jsp?error=Invalid action");
            return false;
        }
        return true;
    }
}
