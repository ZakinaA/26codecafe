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

        <jsp:include page="/vues/components/menu.jsp" />

        <a href="/26CodeCafe/ServletIntervention/lister">Toutes</a> |
        <a href="/26CodeCafe/ServletIntervention/lister?archive=0">Non archivées</a> |
        <a href="/26CodeCafe/ServletIntervention/lister?archive=1">Archivées</a>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Adresse</th>
                    <th>Ville</th>
                    <th>Action</th>
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
                        <a href="${pageContext.request.contextPath}/ServletIntervention/consulter?idIntervention=<%= i.getId() %>">Consulter</a>
                        <a href="${pageContext.request.contextPath}/ServletIntervention/modifier?idIntervention=<%= i.getId() %>">Modifier</a>
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