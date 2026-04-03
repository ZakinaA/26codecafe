<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, bts.sio.codecafe.model.Pompier"%>
<%@page import="bts.sio.codecafe.model.Pompier"%>
<%@page import="bts.sio.codecafe.model.Caserne"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste des pompiers</title>
    </head>
    <body>
        <h1>Liste des pompiers</h1>

        <jsp:include page="/vues/components/menu.jsp" />

        <a class="btn" href="/26CodeCafe/ServletPompier/ajouterPompier">+ Ajouter un pompier</a>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Caserne</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    ArrayList<Pompier> lesPompiers = (ArrayList<Pompier>) request.getAttribute("pLesPompiers");
                    if (lesPompiers != null) {
                        for (Pompier p : lesPompiers) {
                %>
                <tr>
                    <td><%= p.getId() %></td>
                    <td><%= p.getNom() %></td>
                    <td><%= p.getPrenom() %></td>
                    <td><%= p.getUneCaserne().getNom() %></td>
                    <td>
                        <a href="/26CodeCafe/ServletPompier/consulterPompier?idPompier=<%= p.getId() %>">Consulter</a>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr><td colspan="5">Aucun pompier trouvé.</td></tr>
                <% } %>
            </tbody>
        </table>
    </body>
</html>