<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="bts.sio.codecafe.model.Situation"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fiche situation</title>
    </head>
    <body>
        <h1>Fiche situation</h1>

        <jsp:include page="/vues/components/menu.jsp" />

        <%
            Situation i = (Situation) request.getAttribute("pSituation");
            if (i != null) {
        %>
        <div class="fiche">
            <p><span class="label">ID :</span> <%= i.getId()%></p>
            <p><span class="label">Libelle :</span> <%= i.getLibelle()%></p>
            <button><a href="${pageContext.request.contextPath}/ServletSituation/modifier?idSituation=<%= i.getId() %>">Modifier</a></button>
        </div>
        <% } else { %>
        <p style="color:red;">Situation introuvable.</p>
        <% }%>

        <a href="${pageContext.request.contextPath}/ServletSituation/lister">← Retour à la liste</a>
    </body>
</html>