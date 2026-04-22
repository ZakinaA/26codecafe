
<%@page import="java.util.ArrayList, bts.sio.codecafe.model.Intervention"%>

<%@ include file="/vues/components/header.jspf"%>

<div class="card border-0">
    <div class="card-header d-flex align-items-center justify-content-between flex-wrap gap-2">
        <div>
            <h1 class="h3 mt-2 mb-0 fw-semibold">Liste des interventions</h1>
            <span class="text-secondary small mt-1">Liste des interventions avec action possible.</span>
        </div>

        <div class="btn-group" role="group">
            <a href="/26CodeCafe/ServletIntervention/lister"
               class="btn btn-outline-primary <%= request.getParameter("archive") == null ? "active" : "" %>">
                Toutes
            </a>
            <a href="/26CodeCafe/ServletIntervention/lister?archive=0"
               class="btn btn-outline-primary <%= "0".equals(request.getParameter("archive")) ? "active" : "" %>">
                Non archivées
            </a>
            <a href="/26CodeCafe/ServletIntervention/lister?archive=1"
               class="btn btn-outline-primary <%= "1".equals(request.getParameter("archive")) ? "active" : "" %>">
                Archivées
            </a>
        </div>
    </div>
    <div class="card-body p-4">
        <%  String statut = (String) session.getAttribute("pArchiveStatut");
            String action = (String) session.getAttribute("pArchiveAction");
            session.removeAttribute("pArchiveStatut");
            session.removeAttribute("pArchiveAction");
            if ("success".equals(statut)) {
        %>
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            <i class="bi bi-check-circle-fill me-2"></i><%= action %> effectué avec succès.
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
        <% } else if ("fail".equals(statut)) { %>
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            <i class="bi bi-exclamation-triangle-fill me-2"></i><%= action %> a échoué.
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
        <% } %>
        <table class="table table-striped table-hover" id="table">
            <thead>
            <tr>
                <th scope="col" class="sortable" data-col="0" style="cursor:pointer;">
                    # <i class="bi bi-arrow-down-up text-secondary ms-1"></i>
                </th>
                <th scope="col">Adresse</th>
                <th scope="col" class="sortable" data-col="2" style="cursor:pointer;">
                    Ville <i class="bi bi-arrow-down-up text-secondary ms-1"></i>
                </th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
            <%
                ArrayList<Intervention> lesInterventions = (ArrayList<Intervention>) request.getAttribute("pLesInterventions");
                if (!lesInterventions.isEmpty()) {
                    for (Intervention i : lesInterventions) {
            %>
            <tr>
                <th scope="row"><%= i.getId() %></th>
                <td><%= i.getRue() %> <%= i.getCopos() %></td>
                <td><%= i.getVille() %></td>
                <td>
                    <!-- Action -->
                    <a href="${pageContext.request.contextPath}/ServletIntervention/consulter?idIntervention=<%= i.getId() %>"
                       data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Consulter" class="mx-1 text-decoration-none">
                        <i class="bi bi-eye"></i>
                    </a>
                    <a href="${pageContext.request.contextPath}/ServletIntervention/modifier?idIntervention=<%= i.getId() %>"
                       data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Modifier" class="mx-1 text-decoration-none">
                        <i class="bi bi-pencil-square"></i>
                    </a>
                    <% int nouvelEtat = (i.getArchive() == 0) ? 1 : 0;
                        String filtreActuel = request.getParameter("archive") != null ? request.getParameter("archive") : ""; %>

                    <a href="${pageContext.request.contextPath}/ServletIntervention/archiver?idIntervention=<%= i.getId() %>&archive=<%= nouvelEtat %>&retour=<%= filtreActuel %>"
                       data-bs-toggle="tooltip" data-bs-placement="top"
                       data-bs-title="<%= i.getArchive() == 0 ? "Archiver" : "Désarchiver" %>"
                       class="mx-1 text-decoration-none <%= i.getArchive() == 0 ? "text-warning" : "text-success" %>">
                        <i class="bi bi-<%= i.getArchive() == 0 ? "archive" : "arrow-counterclockwise" %>"></i>
                    </a>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="4">
                    <%
                        String alertMessage = "Aucune intervention trouvée";
                        Boolean alertMb = false;
                    %>
                    <%@ include file="/vues/components/alertDanger.jspf"%>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="/vues/components/footer.jspf"%>