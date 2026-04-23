<%@ include file="/vues/components/header.jspf"%>

<!-- Formulaire -->
<div class="card border-0">
    <div class="card-header">
        <h1 class="h3 mt-2 mb-0 fw-semibold">Ajout d'un type d'engin</h1>
        <span class="text-secondary small mt-1">Remplissez les informations de la nouvelle type d'engin.</span>
    </div>
    <div class="card-body p-4">
        <%
            String alertSessionKey = "pAjoutStatut";
            String alertMessage = "L'ajout a échoué";
        %>
        <%@ include file="/vues/components/alertFail.jspf"%>
        <form method="post" action="/26CodeCafe/ServletTypeEngin/ajouter">
            <input type="hidden" name="action" value="ajouter">

            <!-- Libelle -->
            <h6 class="text-uppercase text-secondary small fw-semibold pb-1 border-bottom mb-3">
                Libelle
            </h6>
            <div class="row g-3 mb-4">
                <div class="col-12">
                    <label class="form-label fw-medium">Libelle</label>
                    <input type="text" name="libelle" class="form-control"
                           placeholder="VPI">
                </div>
            </div>

            <!-- Actions -->
            <div class="d-flex gap-2 pt-3 border-top">
                <button type="submit" class="btn btn-primary px-4">
                    Ajouter
                </button>
                <a href="/26CodeCafe/ServletTypeEngin/lister"
                   class="btn btn-outline-secondary px-4">
                    Annuler
                </a>
            </div>

        </form>
    </div>
</div>

<%@ include file="/vues/components/footer.jspf"%>