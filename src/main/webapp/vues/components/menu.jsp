<%@ page import="java.util.Map" %>
<%@ page import="bts.sio.codecafe.utils.MenuBuilder" %>

<a href="${pageContext.request.contextPath}">Accueil</a>
<%
    Map<String, Map<String, String>> menu = MenuBuilder.getMenu();

    for (Map.Entry<String, Map<String, String>> categorie : menu.entrySet()) {
%>
<strong><%= categorie.getKey().replace("Servlet", "") %></strong>
<ul>
    <%
        for (Map.Entry<String, String> lien : categorie.getValue().entrySet()) {
    %>
    <li>
        <a href="/26CodeCafe/<%= categorie.getKey() %>/<%= lien.getValue() %>">
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