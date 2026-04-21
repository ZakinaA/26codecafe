<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="bts.sio.codecafe.model.Situation" %>
<%@ page import="bts.sio.codecafe.model.Situation" %>
<%@ page import="java.util.ArrayList" %>

<jsp:include page="/vues/components/header.jsp"/>

<% Situation s = (Situation) request.getAttribute("pSituation"); %>

<!-- Formulaire -->
<div class="card border-0">
    <div class="card-header">
        <h1 class="h3 mt-2 mb-0 fw-semibold">Modification de la situation</h1>
        <span class="text-secondary small mt-1">Modifier les informations de la situation.</span>
    </div>
    <div class="card-body p-4">>
        <%  if (session.getAttribute("pModifStatut") != null) { %>
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            <i class="bi bi-check-circle-fill me-2"></i>La modification a échoué.
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
        <% } %>
        <% if (s != null) { %>
        <form method="post" action="/26CodeCafe/ServletSituation/modifier">
            <input type="hidden" name="action" value="modifier">
            <input type="hidden" name="id" value="<%= s.getId() %>">

            <!-- Libelle -->
            <h6 class="text-uppercase text-secondary small fw-semibold pb-1 border-bottom mb-3">
                Libelle
            </h6>
            <div class="row g-3 mb-4">
                <div class="col-12">
                    <label class="form-label fw-medium">Libelle</label>
                    <input type="text" name="libelle" class="form-control"
                           placeholder="Ex: Feu d'appartement"
                           value="<%= s.getLibelle() %>">
                </div>
            </div>

            <!-- Actions -->
            <div class="d-flex gap-2 pt-3 border-top">
                <button type="submit" class="btn btn-primary px-4">
                    Valider
                </button>
                <a href="/26CodeCafe/ServletSituation/lister"
                   class="btn btn-outline-secondary px-4">
                    Annuler
                </a>
            </div>

        </form>        <!-- Situation introuvable -->
        <% } else { %>
        <div class="alert alert-danger d-flex align-items-center gap-2" role="alert">
            <i class="bi bi-exclamation-triangle-fill"></i>
            <span>Situation introuvable.</span>
        </div>
        <!-- Actions -->
        <div class="d-flex gap-2 pt-3 border-top">
            <a href=""
               class="btn btn-primary px-4 disabled">Valider</a>
            <a href="/26CodeCafe/ServletSituation/lister"
               class="btn btn-outline-secondary px-4">Annuler</a>
            <% } %>
        </div>
    </div>

    <jsp:include page="/vues/components/footer.jsp"/>
