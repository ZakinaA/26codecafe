<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="bts.sio.codecafe.model.Intervention"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fiche intervention</title>
    </head>
    <body>
        <h1>Fiche intervention</h1>

        <jsp:include page="/vues/components/menu.jsp" />

        <%
            Intervention i = (Intervention) request.getAttribute("pIntervention");
            if (i != null) {
        %>
        <div class="fiche">
            <p><span class="label">ID :</span> <%= i.getId()%></p>
            <p><span class="label">Rue :</span> <%= i.getRue()%></p>
            <p><span class="label">Code postal :</span> <%= i.getCopos()%></p>
            <p><span class="label">Ville :</span> <%= i.getVille()%></p>
            <p><span class="label">Heure d'appel :</span> <%= i.getHeureAppel()%></p>
            <p><span class="label">Heure arrivé :</span> <%= i.getHeureArrivee()%></p>
            <p><span class="label">Durée :</span> <%= (i.getDuree() / 60 < 1)
                    ? i.getDuree() + (i.getDuree() > 1 ? " minutes" : " minute")
                    : (i.getDuree() / 60) + ((i.getDuree() / 60) > 1 ? " heures" : " heure")%></p>
            <p><span class="label">Situation</span> <%= i.getUneSituation().getLibelle()%></p>
            <button><a href="${pageContext.request.contextPath}/ServletIntervention/modifier?idIntervention=<%= i.getId() %>">Modifier</a></button>
        </div>
        <% } else { %>
        <p style="color:red;">Intervention introuvable.</p>
        <% }%>

        <a href="${pageContext.request.contextPath}/ServletIntervention/lister">← Retour à la liste</a>
    </body>
</html>