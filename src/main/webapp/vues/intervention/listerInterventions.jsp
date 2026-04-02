<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, bts.sio.codecafe.model.Intervention"%>
<%@page import="bts.sio.codecafe.model.Intervention"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste des interventions</title>
    </head>
    <body>
        <h1>Liste des interventions</h1>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Adresse</th>
                    <th>Ville</th>
                </tr>
            </thead>
            <tbody>
                <%
                    ArrayList<Intervention> lesInterventions = (ArrayList<Intervention>) request.getAttribute("pLesInterventions");
                    if (lesInterventions != null) {
                        for (Intervention i : lesInterventions) {
                %>
                <tr>
                    <td><%= i.getId() %></td>
                    <td><%= i.getRue() %><br><%= i.getCopos()%></td>
                    <td><%= i.getVille() %></td>
                    <td>
                        <a href="/26CodeCafe/ServletIntervention/consulter?idIntervention=<%= i.getId() %>">Consulter</a>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr><td colspan="5">Aucune intervention trouvé.</td></tr>
                <% } %>
            </tbody>
        </table>
    </body>
</html>