<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="bts.sio.codecafe.model.Intervention" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modifier un intervention</title>
    </head>
    <body>
        <h1>Modifier un intervention</h1>

        <jsp:include page="/vues/components/menu.jsp" />

        <% Intervention i = (Intervention) request.getAttribute("pIntervention"); %>

        <form method="post" action="/26CodeCafe/ServletIntervention/modifier">
            <input type="hidden" name="action" value="modifier">
            <input type="hidden" name="id" value="<% out.println(i.getId()); %>">

            <label>Rue</label>
            <input type="text" name="rue" value="<%= i.getRue() %>">
            <br>
            <label>Code postal</label>
            <input type="text" name="copos" value="<%= i.getCopos() %>">
            <br>
            <label>Ville</label>
            <input type="text" name="ville" value="<%= i.getVille() %>">
            <br>
            <label>Heure d'appel</label>
            <input type="time" name="heureAppel" value="<%= i.getHeureAppel() %>">
            <br>
            <label>Heure d'arrivée</label>
            <input type="time" name="heureArrivee" value="<%= i.getHeureArrivee() %>">
            <br>
            <label>Durée (minute)</label>
            <input type="text" name="duree" value="<%= i.getDuree() %>">
            <br>
            <input type="checkbox" name="archive" <% if (i.getArchive() == 1) { %> <%= "checked" %><% } %>>
            <br>
            <input type="submit" value="Modifier l'intervention">
        </form>

        <a href="/26CodeCafe/ServletIntervention/lister">← Retour à la liste</a>
    </body>
</html>
