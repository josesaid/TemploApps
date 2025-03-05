<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en-GB">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords" content="events">
    <title>Spring MVC</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/global.css"/>">
</head>
<body>
<%@ include file="/WEB-INF/views/template/navbar.jsp" %>
<div class="container">
    <div class="row text-center">
        <h1>Account view</h1></div>
</div>
<%@ include file="/WEB-INF/views/template/footer.jsp" %>
<div id="languages">
    <a href="?language=pl"><img src="<c:url value="/resources/images/pl.png" />" alt="PL" /></a>
    <a href="?language=en"><img src="<c:url value="/resources/images/en.png" />" alt="EN" /></a>
</div>
<script src="<c:url value="/resources/js/jquery.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
</body>
</html>
