<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajouter un situation</title>
    </head>
    <body>
        <h1>Ajouter un situation</h1>

        <jsp:include page="/vues/components/menu.jsp" />

        <form method="post" action="/26CodeCafe/ServletSituation/ajouter">
            <input type="hidden" name="action" value="ajouter">

            <label>Libelle</label>
            <input type="text" name="libelle">
            <br>
            <input type="submit" value="Ajouter l'situation">
        </form>

        <a href="/26CodeCafe/ServletSituation/lister">← Retour à la liste</a>
    </body>
</html>
