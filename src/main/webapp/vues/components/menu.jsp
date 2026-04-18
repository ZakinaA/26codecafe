<%@ page import="java.util.Map" %>
<%@ page import="bts.sio.codecafe.utils.MenuBuilder" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="d-flex flex-column bg-body-secondary border-end" style="width: 240px; min-width: 200px;">

    <!-- Logo / Accueil + Bouton thème -->
    <div class="p-3 border-bottom d-flex align-items-center justify-content-between">
        <a href="${pageContext.request.contextPath}"
           class="d-flex align-items-center gap-2 text-decoration-none text-body">
            <i class="bi bi-cup-hot"></i>
            <span class="fs-5 fw-semibold">CodeCafe</span>
        </a>

        <!-- Bouton thème -->
        <div class="dropdown">
            <button class="btn btn-link nav-link p-1 dropdown-toggle d-flex align-items-center"
                    id="bd-theme" type="button" aria-expanded="false" data-bs-toggle="dropdown"
                    data-bs-display="static" aria-label="Toggle theme">
                <i class="bi bi-sun-fill theme-icon-active"></i>
            </button>
            <ul class="dropdown-menu dropdown-menu-end shadow-sm" aria-labelledby="bd-theme">
                <li>
                    <button type="button" class="dropdown-item d-flex align-items-center active"
                            data-bs-theme-value="light" aria-pressed="true">
                        <i class="bi bi-sun-fill me-2 opacity-50"></i>
                        Clair
                        <i class="bi bi-check2 ms-auto d-none"></i>
                    </button>
                </li>
                <li>
                    <button type="button" class="dropdown-item d-flex align-items-center"
                            data-bs-theme-value="dark" aria-pressed="false">
                        <i class="bi bi-moon-stars-fill me-2 opacity-50"></i>
                        Sombre
                        <i class="bi bi-check2 ms-auto d-none"></i>
                    </button>
                </li>
                <li>
                    <button type="button" class="dropdown-item d-flex align-items-center"
                            data-bs-theme-value="auto" aria-pressed="false">
                        <i class="bi bi-circle-half me-2 opacity-50"></i>
                        Système
                        <i class="bi bi-check2 ms-auto d-none"></i>
                    </button>
                </li>
            </ul>
        </div>
    </div>

    <!-- Menu -->
    <div class="p-3 flex-grow-1 overflow-y-auto">
        <%
            Map<String, Map<String, String>> menu = MenuBuilder.getMenu();

            for (Map.Entry<String, Map<String, String>> categorie : menu.entrySet()) {
        %>

        <p class="text-uppercase text-secondary fw-semibold mb-1 mt-3"
           style="font-size: 0.7rem; letter-spacing: 0.08em;">
            <%= categorie.getKey().replace("Servlet", "") %>
        </p>

        <ul class="nav flex-column mb-1 nav-underline">
            <%
                for (Map.Entry<String, String> lien : categorie.getValue().entrySet()) {
            %>
            <li class="nav-item">
                <a href="/26CodeCafe/<%= categorie.getKey() %>/<%= lien.getValue() %>"
                   class="nav-link text-body py-1 px-2 rounded">
                    <%= lien.getKey() %>
                </a>
            </li>
            <%
                }
            %>
        </ul>

        <%
            }
        %>
    </div>

</nav>