<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, bts.sio.codecafe.model.Situation"%>

<jsp:include page="/vues/components/header.jsp"/>

<div class="card border-0">
    <div class="card-header d-flex align-items-center justify-content-between flex-wrap gap-2">
        <div>
            <h1 class="h3 mt-2 mb-0 fw-semibold">Liste des situations</h1>
            <span class="text-secondary small mt-1">Liste des situations avec action possible.</span>
        </div>

        <div class="btn-group" role="group">
            <a href="/26CodeCafe/ServletSituation/lister"
               class="btn btn-outline-primary <%= request.getParameter("archive") == null ? "active" : "" %>">
                Toutes
            </a>
            <a href="/26CodeCafe/ServletSituation/lister?archive=0"
               class="btn btn-outline-primary <%= "0".equals(request.getParameter("archive")) ? "active" : "" %>">
                Non archivées
            </a>
            <a href="/26CodeCafe/ServletSituation/lister?archive=1"
               class="btn btn-outline-primary <%= "1".equals(request.getParameter("archive")) ? "active" : "" %>">
                Archivées
            </a>
        </div>
    </div>
    <div class="card-body p-4">

        <table class="table table-striped table-hover" id="table">
            <thead>
            <tr>
                <th scope="col" class="sortable" data-col="0" style="cursor:pointer;">
                    # <i class="bi bi-arrow-down-up text-secondary ms-1"></i>
                </th>
                <th scope="col" class="sortable" data-col="1" style="cursor:pointer;">Libelle
                    <i class="bi bi-arrow-down-up text-secondary ms-1"></i>
                </th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
            <%
                ArrayList<Situation> lesSituations = (ArrayList<Situation>) request.getAttribute("pLesSituations");
                if (lesSituations != null) {
                    for (Situation s : lesSituations) {
            %>
            <tr>
                <th scope="row"><%= s.getId() %></th>
                <td><%= s.getLibelle() %></td>
                <td>
                    <a href="${pageContext.request.contextPath}/ServletSituation/consulter?idSituation=<%= s.getId() %>"
                       data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Consulter" class="mx-1 text-decoration-none">
                        <i class="bi bi-eye"></i>
                    </a>
                    <a href="${pageContext.request.contextPath}/ServletSituation/modifier?idSituation=<%= s.getId() %>"
                       data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Modifier" class="mx-1 text-decoration-none">
                        <i class="bi bi-pencil-square"></i>
                    </a>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr><td colspan="4">Aucune situation trouvée.</td></tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="/vues/components/footer.jsp"/>