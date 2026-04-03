<%@ page import="java.util.Map" %>
<%@ page import="bts.sio.codecafe.utils.MenuBuilder" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Accueil</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Page d'accueil</h1>

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
    </body>
</html>
