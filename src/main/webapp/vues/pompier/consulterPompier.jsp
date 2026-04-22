
<%@page import="bts.sio.codecafe.model.Pompier"%>

<%@ include file="/vues/components/header.jspf"%>
        <h1>Fiche pompier</h1>

        <%
            Pompier p = (Pompier) request.getAttribute("pPompier");
            if (p != null) {
        %>
        <div class="fiche">
            <p><span class="label">ID :</span> <%= p.getId()%></p>
            <p><span class="label">Nom :</span> <%= p.getNom()%></p>
            <p><span class="label">Prénom :</span> <%= p.getPrenom()%></p>
            <p><span class="label">Numéro de bip :</span> <%= p.getNumeroBip() != null ? p.getNumeroBip() : "-"%></p>
            <p><span class="label">Date de naissance :</span> <%= p.getDateNaissance() != null ? p.getDateNaissance() : "-"%></p>
            <p><span class="label">Indice de traitement :</span> <%= p.getIndiceTraitement()%></p>
            <p><span class="label">Date d'obtention de l'indice :</span> <%= p.getDateObtentionIndice() != null ? p.getDateObtentionIndice() : "-"%></p>
            <p><span class="label">Statut :</span> <%= p.getStatut() != null ? p.getStatut() : "-"%></p>
            <p><span class="label">Caserne :</span> <%= p.getUneCaserne().getNom()%></p>
            <%-- <p><span class="label">Grade :</span> <%= p.getUnGrade().getLibelle() %></p> --%>
            <%-- <p><span class="label">Fonction :</span> <%= p.getUneFonction().getLibelle() %></p> --%>
        </div>
        <% } else { %>
        <p style="color:red;">Pompier introuvable.</p>
        <% }%>

        <a href="${pageContext.request.contextPath}/ServletPompier/listerPompiers">← Retour à la liste</a>

<%@ include file="/vues/components/footer.jspf"%>