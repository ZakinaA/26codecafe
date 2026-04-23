<%@ page import="bts.sio.codecafe.model.TypeEngin" %>

<%@ include file="/vues/components/header.jspf"%>

<% TypeEngin s = (TypeEngin) request.getAttribute("pTypeEngin"); %>

<!-- Formulaire -->
<div class="card border-0">
    <div class="card-header">
        <h1 class="h3 mt-2 mb-0 fw-semibold">Modification du type d'engin</h1>
        <span class="text-secondary small mt-1">Modifier les informations du type d'engin.</span>
    </div>
    <div class="card-body p-4">
        <%
            String alertSessionKey = "pModifStatut";
            String alertMessage = "La modification a échoué";
        %>
        <%@ include file="/vues/components/alertFail.jspf"%>
        <% if (s != null) { %>
        <form method="post" action="/26CodeCafe/ServletTypeEngin/modifier">
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
                           placeholder="Ex: VPI"
                           value="<%= s.getLibelle() %>">
                </div>
            </div>

            <!-- Actions -->
            <div class="d-flex gap-2 pt-3 border-top">
                <button type="submit" class="btn btn-primary px-4">
                    Valider
                </button>
                <a href="/26CodeCafe/ServletTypeEngin/lister"
                   class="btn btn-outline-secondary px-4">
                    Annuler
                </a>
            </div>

        </form>        <!-- TypeEngin introuvable -->
        <% } else { %>
        <div class="alert alert-danger d-flex align-items-center gap-2" role="alert">
            <i class="bi bi-exclamation-triangle-fill"></i>
            <span>Type d'engin introuvable.</span>
        </div>
        <!-- Actions -->
        <div class="d-flex gap-2 pt-3 border-top">
            <a href=""
               class="btn btn-primary px-4 disabled">Valider</a>
            <a href="/26CodeCafe/ServletTypeEngin/lister"
               class="btn btn-outline-secondary px-4">Annuler</a>
            <% } %>
        </div>
    </div>

    <%@ include file="/vues/components/footer.jspf"%>
