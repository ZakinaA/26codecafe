<%-- 
    Document   : listerCasernes
    Created on : 2 avr. 2026, 10:38:49
    Author     : ts1sio
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="bts.sio.codecafe.model.Caserne"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="/vues/components/header.jspf"%>
    <h1>Liste des Casernes du Calvados</h1>

            <%
                ArrayList<Caserne> lesCasernes = (ArrayList)request.getAttribute("pLesCasernes");
            %>
            <table>  
            <thead>
                <tr>             
                    <th>Id</th>
                    <th>Nom</th>
                    <th>Rue</th>
                    <th>Copos</th>
                    <th>Ville</th>
                </tr>
            </thead>
            <tbody>
                    <%
                        for (Caserne c : lesCasernes)
                        {              
                            out.println("<tr>");
                            out.println("<td>");
                            out.println(c.getId());
                            out.println("</td>");

                            out.println("<td><a href ='../ServletCaserne/consulter?idCaserne="+ c.getId()+ "'>");
                            out.println(c.getNom());
                            out.println("</a></td>");;

                            out.println("<td>");
                            out.println(c.getRue());
                            out.println("</td>");
                           
                            out.println("<td>");
                            out.println(c.getCopos());
                            out.println("</td>");
                            
                            out.println("<td>");                            
                            out.println(c.getVille());
                            out.println("</td>");
                            out.println("</tr>");
                        }
                    %>
            </tbody>
        </table>

<%@ include file="/vues/components/footer.jspf"%>
