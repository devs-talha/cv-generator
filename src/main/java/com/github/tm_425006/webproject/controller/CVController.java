package com.github.tm_425006.webproject.controller;

import com.github.tm_425006.webproject.CVGenerator;
import com.github.tm_425006.webproject.SessionUtils;
import com.github.tm_425006.webproject.bean.BeanUtils;
import com.github.tm_425006.webproject.bean.CV;
import com.github.tm_425006.webproject.bean.User;
import com.github.tm_425006.webproject.exception.CVGeneratorException;
import com.github.tm_425006.webproject.model.CVDAO;
import com.github.tm_425006.webproject.Utils;
import com.github.tm_425006.webproject.service.CVService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.FileSystems;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

public class CVController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (!ControllerUtils.validateAction(request, response, List.of("getall", "delete", "edit",
                "save", "preview", "download")))
            return;
        if (!SessionUtils.verifySession(request, response))
            return;
        try {
            this.handleRequest(request, response);
        } catch (SQLException | CVGeneratorException | ClassNotFoundException e) {
            request.setAttribute("exception", e);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException, CVGeneratorException, ClassNotFoundException, ServletException, IOException {
        switch (request.getParameter("action").trim().toLowerCase(Locale.ROOT)) {
            case "getall" -> {
                this.handleGetAll(request, response);
            }
            case "delete" -> {
                this.handleDelete(request, response);
            }
            case "edit" -> {
                this.handleEdit(request, response);
            }
            case "save" -> {
                this.handleSave(request, response);
            }
            case "preview" -> {
                this.handlePreview(request, response);
            }
            case "download" -> {
                this.handleDownload(request, response);
            }
        }
    }

    public void handleGetAll(HttpServletRequest request, HttpServletResponse response) throws SQLException, CVGeneratorException, ClassNotFoundException, ServletException, IOException {
        CVDAO cvdao = new CVDAO(Utils.servletContextToProperties(this.getServletContext()));
        ArrayList<CV> cvList = cvdao.getAll(((User)request.getSession().getAttribute("user")).getId());
        request.setAttribute("cvList", cvList);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    public void handleDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, CVGeneratorException, ClassNotFoundException, ServletException {
        if (!new CVControllerRequestValidator().validateId(request, response))
            return;
        CVDAO cvdao = new CVDAO(Utils.servletContextToProperties(this.getServletContext()));
        CV cv = BeanUtils.createCV(request.getParameterMap());
        cvdao.delete(cv);
        this.handleGetAll(request, response);
    }

    public void handleEdit(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, CVGeneratorException, ClassNotFoundException, ServletException {
        if (!new CVControllerRequestValidator().validateId(request, response))
            return;
        CVService cvService = new CVService(Utils.servletContextToProperties(this.getServletContext()));
        CV cv = cvService.get(Integer.parseInt(request.getParameter("id").trim()));
        request.setAttribute("cv", cv);
        request.getRequestDispatcher("cv.jsp").forward(request, response);
    }

    public void handleSave(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, CVGeneratorException, ClassNotFoundException, ServletException {
        Properties dbProperties = Utils.servletContextToProperties(this.getServletContext());
        CVService cvService = new CVService(dbProperties);
        CV cv = BeanUtils.createCV(request.getParameterMap());
        Integer cvId = cv.getId();
        if (cv.getId() == -1)
            cvId = cvService.insert(cv);
        else {
            // getting created date as it is not saved in hidden field
            cv.setCreatedDate(new CVDAO(dbProperties).get(cv.getId()).getCreatedDate());
            cvService.update(cv);
        }
        cv = cvService.get(cvId);
        request.setAttribute("cv", cv);
        request.getRequestDispatcher("cv.jsp?success=Saved successfully").forward(request, response);
    }

    public void handlePreview(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, CVGeneratorException, ClassNotFoundException, ServletException {
        if (!new CVControllerRequestValidator().validateId(request, response))
            return;
        CVService cvService = new CVService(Utils.servletContextToProperties(this.getServletContext()));
        CV cv = cvService.get(Integer.parseInt(request.getParameter("id").trim()));
        String generatedPreview = new CVGenerator(cv).generateHTML();
        request.setAttribute("preview", generatedPreview);
        request.getRequestDispatcher("preview.jsp").forward(request, response);
    }

    public void handleDownload(HttpServletRequest request, HttpServletResponse response) throws SQLException, CVGeneratorException, ClassNotFoundException, IOException {
        if (!new CVControllerRequestValidator().validateId(request, response))
            return;
        CVService cvService = new CVService(Utils.servletContextToProperties(this.getServletContext()));
        CV cv = cvService.get(Integer.parseInt(request.getParameter("id").trim()));
        User user = (User) request.getSession().getAttribute("user");

        byte[] generatedPDF = new CVGenerator(cv).generatePDF(
                this.getServletContext().getRealPath("/").concat("WEB-INF"));

        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", String.format("attachment; filename=%s's CV.pdf",
                user.getFirstName()));

        OutputStream outputStream = response.getOutputStream();
        outputStream.write(generatedPDF);
        outputStream.close();
    }

}