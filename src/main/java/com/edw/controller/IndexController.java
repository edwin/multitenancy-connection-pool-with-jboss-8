package com.edw.controller;

import com.edw.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *  com.edw.controller.IndexController
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 20 May 2025 23:27
 */
@WebServlet(name = "IndexController", urlPatterns = "/index")
public class IndexController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserService userService = new UserService();;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String customerId = request.getParameter("customerId");
        List<Map> result = userService.select(customerId, 0, 10);

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<h1> Success </h1>");
        for (int i = 0; i < result.size(); i++) {
            out.println(result.get(i)+" <br />");
        }

    }

}
