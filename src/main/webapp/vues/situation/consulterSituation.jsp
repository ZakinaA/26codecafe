<%@page import="bts.sio.codecafe.model.Situation"%>

<%@ include file="/vues/components/header.jspf"%>

<% Situation s = (Situation) request.getAttribute("pSituation"); %>
<div class="card border-0">
    <div class="card-header">
        <h1 class="h3 mt-2 mb-0 fw-semibold">Consultation de la situation</h1>
        <span class="text-secondary small mt-1">Consultation des informations de la situation.</span>
    </div>
    <div class="card-body p-4">
        <% if (s != null) {
            if ( (session.getAttribute("pAjoutStatut") != null) || (session.getAttribute("pModifStatut") != null) ) {
                String action = (session.getAttribute("pAjoutStatut") == "success") ? "Ajout" : "Modification";
                if ((action.equals("ajout"))) {
                    session.removeAttribute("pAjoutStatut");
                } else {
                    session.removeAttribute("pModifStatut");
                }
        %>
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            <i class="bi bi-check-circle-fill me-2"></i><%= action %> effectué avec succès.
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
        <% } %>

        <!-- Adresse -->
        <h6 class="text-uppercase text-secondary small fw-semibold pb-1 border-bottom mb-3">
            Libelle
        </h6>
        <div class="row g-3 mb-4">
            <div class="col-12 input-group">
                <span class="input-group-text fw-medium">Libelle</span>
                <input type="text" class="form-control" value="<%= s.getLibelle() %>" readonly>
            </div>
        </div>

        <!-- Actions -->
        <div class="d-flex gap-2 pt-3 border-top">
            <a href="${pageContext.request.contextPath}/ServletSituation/modifier?idSituation=<%= s.getId() %>"
               class="btn btn-primary px-4">Modifier</a>
            <a href="/26CodeCafe/ServletSituation/lister"
               class="btn btn-outline-secondary px-4">Retour</a>
        </div>

        <!-- Situation introuvable -->
        <% } else { %>
        <%
            String alertMessage = "Situation introuvable";
            Boolean alertMb = true;
        %>
        <%@ include file="/vues/components/alertDanger.jspf"%>
        <!-- Actions -->
        <div class="d-flex gap-2 pt-3 border-top">
            <a href=""
               class="btn btn-primary px-4 disabled">Modifier</a>
            <a href="/26CodeCafe/ServletSituation/lister"
               class="btn btn-outline-secondary px-4">Retour</a>
            <% } %>
        </div>
    </div>

<%@ include file="/vues/components/footer.jspf"%>