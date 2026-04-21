<%@ page import="bts.sio.codecafe.model.Situation" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/components/header.jsp"/>

        <!-- Formulaire -->
        <div class="card border-0">
            <div class="card-header">
                <h1 class="h3 mt-2 mb-0 fw-semibold">Ajout d'une intervention</h1>
                <span class="text-secondary small mt-1">Remplissez les informations de la nouvelle intervention.</span>
            </div>
            <div class="card-body p-4">
                <%  if (session.getAttribute("pAjoutStatut") != null) { %>
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <i class="bi bi-check-circle-fill me-2"></i>L'ajout a échoué.
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>
                <% } %>
                <form method="post" action="/26CodeCafe/ServletIntervention/ajouter">
                    <input type="hidden" name="action" value="ajouter">

                    <!-- Adresse -->
                    <h6 class="text-uppercase text-secondary small fw-semibold pb-1 border-bottom mb-3">
                        Adresse
                    </h6>
                    <div class="row g-3 mb-4">
                        <div class="col-12 col-md-12 col-lg-4">
                            <label class="form-label fw-medium">Rue</label>
                            <input type="text" name="rue" class="form-control"
                                   placeholder="Ex: 12 rue de la Paix">
                        </div>
                        <div class="col-12 col-md-4 col-lg-4">
                            <label class="form-label fw-medium">Code postal</label>
                            <input type="text" name="copos" class="form-control"
                                   placeholder="Ex: 14000">
                        </div>
                        <div class="col-12 col-md-8 col-lg-4">
                            <label class="form-label fw-medium">Ville</label>
                            <input type="text" name="ville" class="form-control"
                                   placeholder="Ex: Caen">
                        </div>
                    </div>

                    <!-- Horaires -->
                    <h6 class="text-uppercase text-secondary small fw-semibold pb-1 border-bottom mb-3">
                        Horaires
                    </h6>
                    <div class="row g-3 mb-4">
                        <div class="col-12 col-md-4 col-lg-4">
                            <label class="form-label fw-medium">Heure d'appel</label>
                            <input type="time" name="heureAppel" class="form-control">
                        </div>
                        <div class="col-12 col-md-4 col-lg-4">
                            <label class="form-label fw-medium">Heure d'arrivée</label>
                            <input type="time" name="heureArrivee" class="form-control">
                        </div>
                        <div class="col-12 col-md-4 col-lg-4">
                            <label class="form-label fw-medium">Durée (minutes)</label>
                            <input type="number" name="duree" class="form-control"
                                   placeholder="Ex: 45" min="0">
                        </div>
                    </div>

                    <!-- Situation -->
                    <h6 class="text-uppercase text-secondary small fw-semibold pb-1 border-bottom mb-3">
                        Situation
                    </h6>
                    <div class="row g-3 mb-4">
                        <div class="col-12">
                            <label class="form-label fw-medium">Situation</label>
                            <select name="idSituation" class="form-select">
                                <option value="">-- Sélectionner une situation --</option>
                                <%
                                    ArrayList<Situation> lesSituations = (ArrayList<Situation>) request.getAttribute("pLesSituations");
                                    for (Situation s : lesSituations) {
                                %>
                                <option value="<%= s.getId() %>"><%= s.getLibelle() %></option>
                                <% } %>
                            </select>
                        </div>
                    </div>

                    <!-- Actions -->
                    <div class="d-flex gap-2 pt-3 border-top">
                        <button type="submit" class="btn btn-primary px-4">
                            Ajouter
                        </button>
                        <a href="/26CodeCafe/ServletIntervention/lister"
                           class="btn btn-outline-secondary px-4">
                            Annuler
                        </a>
                    </div>

                </form>
            </div>
        </div>

<jsp:include page="/vues/components/footer.jsp"/>