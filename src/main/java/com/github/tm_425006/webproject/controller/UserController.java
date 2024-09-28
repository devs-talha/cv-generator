package com.github.tm_425006.webproject.controller;

import com.github.tm_425006.webproject.SessionUtils;
import com.github.tm_425006.webproject.Utils;
import com.github.tm_425006.webproject.bean.BeanUtils;
import com.github.tm_425006.webproject.bean.CV;
import com.github.tm_425006.webproject.bean.User;
import com.github.tm_425006.webproject.exception.CVGeneratorException;
import com.github.tm_425006.webproject.model.CVDAO;
import com.github.tm_425006.webproject.model.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

public class UserController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (!ControllerUtils.validateAction(request, response, List.of("login", "signup", "logout", "updateaccount",
                "changepassword")))
            return;
        if (!request.getParameter("action").trim().equalsIgnoreCase("login") &&
                !request.getParameter("action").trim().equalsIgnoreCase("signup") &&
                !SessionUtils.verifySession(request, response))
            return;
        try {
            this.handleRequest(request, response);
        } catch (SQLException | ClassNotFoundException | CVGeneratorException e) {
            request.setAttribute("exception", e);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException, CVGeneratorException, ClassNotFoundException, ServletException, IOException {
        switch (request.getParameter("action").trim().toLowerCase(Locale.ROOT)) {
            case "login" -> {
                this.handleLogin(request, response);
            }
            case "signup" -> {
                this.handleSignup(request, response);
            }
            case "logout" -> {
                this.handleLogout(request, response);
            }
            case "updateaccount" -> {
                this.handleUpdateAccount(request, response);
            }
            case "changepassword" -> {
                this.handleChangePassword(request, response);
            }
        }
    }

        public void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException, CVGeneratorException {
        if (!new UserControllerRequestValidator().validateLogin(request, response))
            return;
        Properties dbProperties = Utils.servletContextToProperties(this.getServletContext());
        UserDAO userDAO = new UserDAO(dbProperties);
        User user = userDAO.get(request.getParameter("email").trim());
        if (user == null || !user.getPassword().equals(request.getParameter("password"))) {
            response.sendRedirect("login.jsp?error=incorrect email/password");
            return;
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);
        request.getRequestDispatcher("cvcontroller?action=getAll").forward(request, response);
    }

    public void handleSignup(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ClassNotFoundException, CVGeneratorException, ServletException {
        if (!new UserControllerRequestValidator().validateSignup(request, response))
            return;

        User user = BeanUtils.createUser(request.getParameterMap());
        UserDAO userDAO = new UserDAO(Utils.servletContextToProperties(this.getServletContext()));
        if (userDAO.get(user.getEmail()) != null) {
            response.sendRedirect("signup.jsp?error=Email already in use");
        } else {
            userDAO.insert(user);
            response.sendRedirect("signup.jsp?success=Successfully registered");
        }
    }

    public void handleLogout(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ClassNotFoundException, CVGeneratorException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null)
            session.invalidate();
        response.sendRedirect("login.jsp");
    }

    public void handleUpdateAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ClassNotFoundException, CVGeneratorException, ServletException {
        if (!new UserControllerRequestValidator().validateUpdateAccount(request, response))
            return;

        User user = BeanUtils.createUser(request.getParameterMap());
        User sessionUser = (User)request.getSession().getAttribute("user");
        if (!user.getPassword().equals(sessionUser.getPassword())) {
            response.sendRedirect("updateaccount.jsp?error=Incorrect password");
            return;
        }
        user.setId(sessionUser.getId());
        UserDAO userDAO = new UserDAO(Utils.servletContextToProperties(this.getServletContext()));
        userDAO.update(user);
        request.getSession().setAttribute("user", user);
        response.sendRedirect("updateaccount.jsp?success=Account updated successfully");
    }

    public void handleChangePassword(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ClassNotFoundException, CVGeneratorException, ServletException {
        if (!new UserControllerRequestValidator().validateChangePassword(request, response))
            return;

        User user = (User)request.getSession().getAttribute("user");
        if (!user.getPassword().equals(request.getParameter("currentPassword").trim())) {
            response.sendRedirect("changepassword.jsp?error=Incorrect current password");
            return;
        }
        user.setPassword(request.getParameter("newPassword").trim());
        UserDAO userDAO = new UserDAO(Utils.servletContextToProperties(this.getServletContext()));
        userDAO.update(user);
        request.getSession().setAttribute("user", user);
        response.sendRedirect("changepassword.jsp?success=Password changed successfully");
    }

}