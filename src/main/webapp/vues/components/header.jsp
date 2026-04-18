<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Code Cafe</title>

    <!-- Bootstrap -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/webjars/bootstrap-icons/1.11.3/font/bootstrap-icons.min.css">

    <script src="${pageContext.request.contextPath}/resources/js/color-modes.js"></script>
</head>
<body>

<!-- Layout principal -->
<div class="d-flex min-vh-100 align-items-stretch">

    <!-- Sidebar -->
    <jsp:include page="/vues/components/menu.jsp" />

    <!-- Contenu -->
    <main class="flex-grow-1 p-4">