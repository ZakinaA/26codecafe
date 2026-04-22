<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, bts.sio.codecafe.model.Caserne"%>

<%@ include file="/vues/components/header.jspf"%>
        <h1>Modifier un pompier</h1>

        <form method="post" action="/26CodeCafe/ServletPompier/ajouterPompier">
            <label>Nom</label>
            <input type="text" name="nom">
            <br>
            <label>Prénom</label>
            <input type="text" name="prenom">
            <br>
            <label>Caserne</label>
            <select name="idCaserne">
                <option value="">-- Sélectionner une caserne --</option>
                <%
                    ArrayList<Caserne> lesCasernes = (ArrayList<Caserne>) request.getAttribute("pLesCasernes");
                    for (Caserne c : lesCasernes) {
                %>
                <option value="<%= c.getId() %>"><%= c.getNom() %></option>
                <% } %>
            </select>
            <br>
            <input type="submit" value="Ajouter le pompier">
        </form>

        <a href="/26CodeCafe/ServletPompier/listerPompiers">← Retour à la liste</a>

<%@ include file="/vues/components/footer.jspf"%>
