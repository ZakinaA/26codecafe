<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="bts.sio.codecafe.model.Intervention"%>

<%@ include file="/vues/components/header.jspf"%>

<% Intervention i = (Intervention) request.getAttribute("pIntervention"); %>
<div class="card border-0">
    <div class="card-header">
        <h1 class="h3 mt-2 mb-0 fw-semibold">Consultation de l'intervention</h1>
        <span class="text-secondary small mt-1">Consultation des informations de l'intervention.</span>
    </div>
    <div class="card-body p-4">
        <%  if (i != null) {
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
            Adresse
        </h6>
        <div class="row g-3 mb-4">
            <div class="col-12 col-lg-4 input-group">
                <span class="input-group-text fw-medium">Rue</span>
                <input type="text" class="form-control" value="<%= i.getRue() %>" readonly>
            </div>
            <div class="col-12 col-md-4 col-lg-4 input-group">
                <span class="input-group-text fw-medium">Code postal</span>
                <input type="text" class="form-control" value="<%= i.getCopos() %>" readonly>
            </div>
            <div class="col-12 col-md-8 col-lg-4 input-group">
                <span class="input-group-text fw-medium">Ville</span>
                <input type="text" class="form-control" value="<%= i.getVille() %>" readonly>
            </div>
        </div>

        <!-- Horaires -->
        <h6 class="text-uppercase text-secondary small fw-semibold pb-1 border-bottom mb-3">
            Horaires
        </h6>
        <div class="row g-3 mb-4">
            <div class="col-12 col-md-4 input-group">
                <span class="input-group-text fw-medium">Heure d'appel</span>
                <input type="time" class="form-control" value="<%= i.getHeureAppel() %>" readonly>
            </div>
            <div class="col-12 col-md-4 input-group">
                <span class="input-group-text fw-medium">Heure d'arrivée</span>
                <input type="time" class="form-control" value="<%= i.getHeureArrivee() %>" readonly>
            </div>
            <div class="col-12 col-md-4 input-group">
                <span class="input-group-text fw-medium">Durée (min)</span>
                <input type="number" class="form-control" value="<%= i.getDuree() %>" readonly>
            </div>
        </div>

        <!-- Situation -->
        <h6 class="text-uppercase text-secondary small fw-semibold pb-1 border-bottom mb-3">
            Situation
        </h6>
        <div class="row g-3 mb-4">
            <div class="col-12 input-group">
                <span class="input-group-text fw-medium">Situation</span>
                <input type="text" class="form-control" value="<%= i.getUneSituation().getLibelle() %>" readonly>
            </div>
        </div>

        <!-- Actions -->
        <div class="d-flex gap-2 pt-3 border-top">
            <a href="${pageContext.request.contextPath}/ServletIntervention/modifier?idIntervention=<%= i.getId() %>"
               class="btn btn-primary px-4">Modifier</a>
            <a href="/26CodeCafe/ServletIntervention/lister"
               class="btn btn-outline-secondary px-4">Retour</a>
        </div>

        <!-- Intervention introuvable -->
        <% } else { %>
        <div class="alert alert-danger d-flex align-items-center gap-2" role="alert">
            <i class="bi bi-exclamation-triangle-fill"></i>
            <span>Intervention introuvable.</span>
        </div>
        <!-- Actions -->
        <div class="d-flex gap-2 pt-3 border-top">
            <a href=""
               class="btn btn-primary px-4 disabled">Modifier</a>
            <a href="/26CodeCafe/ServletIntervention/lister"
               class="btn btn-outline-secondary px-4">Retour</a>
        <% } %>
    </div>
</div>

<%@ include file="/vues/components/footer.jspf"%>