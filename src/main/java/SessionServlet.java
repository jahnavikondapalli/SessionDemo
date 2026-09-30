package org.example.SessionDemo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/session")
public class SessionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");

        HttpSession session = request.getSession();

        session.setAttribute("username", username);

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Session Result</title></head>");
        out.println("<body>");

        out.println("<h2>Session Created Successfully!</h2>");

        out.println("<p>Welcome, " + username + "</p>");

        out.println("<p>Session ID: " + session.getId() + "</p>");

        out.println("<p>Your name is stored in the session.</p>");

        out.println("<br>");

        out.println("<a href='session'>View Session</a>");

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Session Details</title></head>");
        out.println("<body>");

        if (session != null &&
                session.getAttribute("username") != null) {

            String username =
                    (String) session.getAttribute("username");

            out.println("<h2>Session Information</h2>");
            out.println("<p>Username: " + username + "</p>");
            out.println("<p>Session ID: " + session.getId() + "</p>");

        } else {

            out.println("<h2>No Session Found</h2>");
            out.println("<a href='index.html'>Create Session</a>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}