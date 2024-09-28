package com.github.tm_425006.webproject.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class UserControllerRequestValidator {

    public boolean validateLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> requiredParameters = List.of("email", "password");
        for (String parameter : requiredParameters) {
            String param = request.getParameter(parameter);
            if (param == null) {
                response.sendRedirect("login.jsp?error=" + parameter + " parameter not found");
                return false;
            } else if (param.trim().isEmpty()) {
                response.sendRedirect("login.jsp?error=" + parameter + " empty");
                return false;
            }
        }
        if (!Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$", Pattern.CASE_INSENSITIVE)
                .matcher(request.getParameter("email").trim()).matches()) {
            response.sendRedirect("login.jsp?error=Email not correct");
            return false;
        }
        return true;
    }

    public boolean validateSignup(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> requiredParameters = List.of("id", "firstName", "lastName", "email", "birthDate",
                "password", "confirmPassword", "address");

        for (String parameter : requiredParameters) {
            String param = request.getParameter(parameter);
            if (param == null) {
                response.sendRedirect("signup.jsp?error=" + parameter + " parameter not found");
                return false;
            } else if (param.trim().isEmpty()) {
                response.sendRedirect("signup.jsp?error=" + parameter + " empty");
                return false;
            }
        }
        if (!Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$", Pattern.CASE_INSENSITIVE)
                .matcher(request.getParameter("email").trim()).matches()) {
            response.sendRedirect("signup.jsp?error=Invalid email format");
            return false;
        } else if (!Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$", Pattern.CASE_INSENSITIVE)
                .matcher(request.getParameter("birthDate").trim()).matches()) {
            response.sendRedirect("signup.jsp?error=Invalid date of birth format");
            return false;
        } else if (!request.getParameter("password").trim().equals(
                request.getParameter("confirmPassword").trim())) {
            response.sendRedirect("signup.jsp?error=Password and confirm password do not match");
            return false;
        }
        return true;
    }

    public boolean validateUpdateAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> requiredParameters = List.of("firstName", "lastName", "email", "birthDate",
                "password", "address");

        for (String parameter : requiredParameters) {
            String param = request.getParameter(parameter);
            if (param == null) {
                response.sendRedirect("updateaccount.jsp?error=" + parameter + " parameter not found");
                return false;
            } else if (param.trim().isEmpty()) {
                response.sendRedirect("updateaccount.jsp?error=" + parameter + " empty");
                return false;
            }
        }
        if (!Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$", Pattern.CASE_INSENSITIVE)
                .matcher(request.getParameter("email").trim()).matches()) {
            response.sendRedirect("updateaccount.jsp?error=Invalid email format");
            return false;
        } else if (!Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$", Pattern.CASE_INSENSITIVE)
                .matcher(request.getParameter("birthDate").trim()).matches()) {
            response.sendRedirect("updateaccount.jsp?error=Invalid date of birth format");
            return false;
        }
        return true;
    }

    public boolean validateChangePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> requiredParameters = List.of("currentPassword", "newPassword", "confirmPassword");

        for (String parameter : requiredParameters) {
            String param = request.getParameter(parameter);
            if (param == null) {
                response.sendRedirect("changepassword.jsp?error=" + parameter + " parameter not found");
                return false;
            } else if (param.trim().isEmpty()) {
                response.sendRedirect("changepassword.jsp?error=" + parameter + " empty");
                return false;
            }
        }

        if (!request.getParameter("newPassword").trim().equals(
                request.getParameter("confirmPassword").trim())) {
            response.sendRedirect("changepassword.jsp?error=New password and confirm password do not match");
            return false;
        }

        return true;
    }

}