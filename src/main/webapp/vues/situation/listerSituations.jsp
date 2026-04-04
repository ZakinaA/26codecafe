<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, bts.sio.codecafe.model.Situation"%>
<%@page import="bts.sio.codecafe.model.Situation"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste des situations</title>
    </head>
    <body>
        <h1>Liste des situations</h1>

        <jsp:include page="/vues/components/menu.jsp" />

        <a href="/26CodeCafe/ServletSituation/lister">Toutes</a> |
        <a href="/26CodeCafe/ServletSituation/lister?archive=0">Non archivées</a> |
        <a href="/26CodeCafe/ServletSituation/lister?archive=1">Archivées</a>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Libelle</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    ArrayList<Situation> lesSituations = (ArrayList<Situation>) request.getAttribute("pLesSituations");
                    if (lesSituations != null) {
                        for (Situation s : lesSituations) {
                %>
                <tr>
                    <td><%= s.getId() %></td>
                    <td><%= s.getLibelle() %></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/ServletSituation/consulter?idSituation=<%= s.getId() %>">Consulter</a>
                        <a href="${pageContext.request.contextPath}/ServletSituation/modifier?idSituation=<%= s.getId() %>">Modifier</a>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr><td colspan="5">Aucune situation trouvé.</td></tr>
                <% } %>
            </tbody>
        </table>
    </body>
</html>