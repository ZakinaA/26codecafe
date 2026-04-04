<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="bts.sio.codecafe.model.Situation" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modifier un situation</title>
    </head>
    <body>
        <h1>Modifier un situation</h1>

        <jsp:include page="/vues/components/menu.jsp" />

        <% Situation s = (Situation) request.getAttribute("pSituation"); %>

        <form method="post" action="/26CodeCafe/ServletSituation/modifier">
            <input type="hidden" name="action" value="modifier">
            <input type="hidden" name="id" value="<% out.println(s.getId()); %>">

            <label>Rue</label>
            <input type="text" name="libelle" value="<%= s.getLibelle() %>">
            <br>
            <input type="checkbox" name="archive" <% if (s.getArchive() == 1) { %> <%= "checked" %><% } %>>
            <br>
            <input type="submit" value="Modifier l'situation">
        </form>

        <a href="/26CodeCafe/ServletSituation/lister">← Retour à la liste</a>
    </body>
</html>
