<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajouter un intervention</title>
    </head>
    <body>
        <h1>Ajouter un intervention</h1>

        <jsp:include page="/vues/components/menu.jsp" />

        <form method="post" action="/26CodeCafe/ServletIntervention/ajouter">
            <input type="hidden" name="action" value="ajouter">

            <label>Rue</label>
            <input type="text" name="rue">
            <br>
            <label>Code postal</label>
            <input type="text" name="copos">
            <br>
            <label>Ville</label>
            <input type="text" name="ville">
            <br>
            <label>Heure d'appel</label>
            <input type="time" name="heureAppel">
            <br>
            <label>Heure d'arrivée</label>
            <input type="time" name="heureArrivee">
            <br>
            <label>Durée (minute)</label>
            <input type="text" name="duree">
            <br>
            <input type="submit" value="Ajouter l'intervention">
        </form>

        <a href="/26CodeCafe/ServletIntervention/lister">← Retour à la liste</a>
    </body>
</html>
