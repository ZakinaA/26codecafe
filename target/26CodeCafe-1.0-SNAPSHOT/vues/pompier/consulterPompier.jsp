<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="bts.sio.codecafe.model.Pompier"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fiche pompier</title>
    </head>
    <body>
        <h1>Fiche pompier</h1>
        
        <%
            Pompier p = (Pompier) request.getAttribute("pPompier");
            if (p != null) {
        %>
        <div class="fiche">
            <p><span class="label">ID :</span> <%= p.getId() %></p>
            <p><span class="label">Nom :</span> <%= p.getNom() %></p>
            <p><span class="label">Prénom :</span> <%= p.getPrenom() %></p>
            <p><span class="label">Caserne :</span> <%= p.getUneCaserne().getNom() %></p>
        </div>
        <% } else { %>
            <p style="color:red;">Pompier introuvable.</p>
        <% } %>
        
        <a href="${pageContext.request.contextPath}/ServletPompier/listerPompiers">← Retour à la liste</a>
    </body>
</html>
