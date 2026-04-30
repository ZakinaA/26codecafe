<%@ page import="bts.sio.codecafe.model.Caserne" %>
<%@ page import="java.util.ArrayList" %>

<%@ include file="/vues/components/header.jspf"%>

        <!-- Formulaire -->
        <div class="card border-0">
            <div class="card-header">
                <h1 class="h3 mt-2 mb-0 fw-semibold">Ajout d'un pompier </h1>
                <span class="text-secondary small mt-1">Remplissez les informations du nouveau pompier .</span>
            </div>
            <div class="card-body p-4">
                <%
                    String alertSessionKey = "pAjoutStatut";
                    String alertMessage = "L'ajout a échoué";
                %>
                <%@ include file="/vues/components/alertFail.jspf"%>
                <form method="post" action="/26CodeCafe/ServletPompier/ajouter">
                    <input type="hidden" name="action" value="ajouter">

                    <!-- Identité -->
                    <h6 class="text-uppercase text-secondary small fw-semibold pb-1 border-bottom mb-3">
                        Identité
                    </h6>
                    <div class="row g-3 mb-4">
                        <div class="col-12 col-md-12 col-lg-4">
                            <label class="form-label fw-medium">Nom</label>
                            <input type="text" name="nom" class="form-control"
                                   placeholder="">
                        </div>
                    </div>
                    
                    <div class="row g-3 mb-4">
                        <div class="col-12 col-md-12 col-lg-4">
                            <label class="form-label fw-medium">Prenom</label>
                            <input type="text" name="prenom" class="form-control"
                                   placeholder="">
                        </div>
                    </div>
                    
                    <div class="row g-3 mb-4">
                        <div class="col-12 col-md-12 col-lg-4">
                            <label class="form-label fw-medium">Numero BIP</label>
                            <input type="text" name="numeroBip" class="form-control"
                                   placeholder="">
                        </div>
                    </div>
                    
                    <div class="row g-3 mb-4">
                        <div class="col-12 col-md-12 col-lg-4">
                            <label class="form-label fw-medium">Date naissance</label>
                            <input type="date" name="dateNaissance" class="form-control"
                                   placeholder="">
                        </div>
                    </div>
                    
                    <div class="row g-3 mb-4">
                        <div class="col-12 col-md-12 col-lg-4">
                            <label class="form-label fw-medium">Indice traitement</label>
                            <input type="text" name="indiceTraitement" class="form-control"
                                   placeholder="">
                        </div>
                    </div>
                    
                    <div class="row g-3 mb-4">
                        <div class="col-12 col-md-12 col-lg-4">
                            <label class="form-label fw-medium">Date obtention indice</label>
                            <input type="date" name="dateObtentionIndice" class="form-control"
                                   placeholder="">
                        </div>
                    </div>
                    
                    <div class="row g-3 mb-4">
                        <div class="col-12 col-md-12 col-lg-4">
                            <label class="form-label fw-medium">Status</label>
                            <input type="text" name="status" class="form-control"
                                   placeholder="">
                        </div>
                    </div>
                    
                    
                    <!-- Caserne -->
                    <h6 class="text-uppercase text-secondary small fw-semibold pb-1 border-bottom mb-3">
                        Caserbe
                    </h6>
                    <div class="row g-3 mb-4">
                        <div class="col-12">
                            <label class="form-label fw-medium">Caserne</label>
                            <select name="idCaserne" class="form-select">
                                <option value="">-- Sélectionner une caserne --</option>
                                <%
                                    ArrayList<Caserne> lesCasernes = (ArrayList<Caserne>) request.getAttribute("pLesCasernes");
                                    for (Caserne s : lesCasernes) {
                                %>
                                <option value="<%= s.getId() %>"><%= s.getNom() %></option>
                                <% } %>
                            </select>
                        </div>
                    </div>

                    <!-- Actions -->
                    <div class="d-flex gap-2 pt-3 border-top">
                        <button type="submit" class="btn btn-primary px-4">
                            Ajouter
                        </button>
                        <a href="/26CodeCafe/ServletPompier/lister"
                           class="btn btn-outline-secondary px-4">
                            Annuler
                        </a>
                    </div>

                </form>
            </div>
        </div>

<%@ include file="/vues/components/footer.jspf"%>