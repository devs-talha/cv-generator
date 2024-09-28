package com.github.tm_425006.webproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class CVControllerRequestValidator {

    public boolean validateId(HttpServletRequest request, HttpServletResponse response) throws IOException {
            String param = request.getParameter("id");
            if (param == null) {
                response.sendRedirect("index.jsp?error=Id parameter not found");
                return false;
            } else if (param.trim().isEmpty()) {
                response.sendRedirect("index.jsp?error=Id empty");
                return false;
            }
        return true;
    }

}