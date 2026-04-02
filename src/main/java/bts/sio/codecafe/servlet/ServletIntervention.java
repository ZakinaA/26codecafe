package bts.sio.codecafe.servlet;

import bts.sio.codecafe.database.DaoIntervention;
import bts.sio.codecafe.model.Intervention;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * Author: JoackimV
 * Created: 02/04/2026 19:32
 * Last modified: 02/04/2026 19:32
 */
@WebServlet(name = "ServletIntervention", urlPatterns = {"/ServletIntervention"})
public class ServletIntervention extends HttpServlet {

    Connection cnx;

    @Override
    public void init() {
        ServletContext servletContext = getServletContext();
        cnx = (Connection) servletContext.getAttribute("connection");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletIntervention</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletIntervention at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURI();
        System.out.println("Passage dans la Servlet avec l'URL : " + request.getRequestURI());

        if (url.equals("/26CodeCafe/ServletIntervention/lister")) {
            ArrayList<Intervention> lesInterventions = DaoIntervention.getLesInterventions(cnx);
            request.setAttribute("pLesInterventions", lesInterventions);
            getServletContext().getRequestDispatcher("/vues/intervention/listerInterventions.jsp").forward(request, response);
        }

        if (url.equals("/26CodeCafe/ServletIntervention/consulter")) {
            int idIntervention = Integer.parseInt((String) request.getParameter("idIntervention"));
            System.out.println("intervention à afficher = " + idIntervention);
            Intervention c = DaoIntervention.getInterventionById(cnx, idIntervention);
            request.setAttribute("pIntervention", c);
            getServletContext().getRequestDispatcher("/vues/intervention/consulterIntervention.jsp").forward(request, response);


        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </e// ditor-fold>
}