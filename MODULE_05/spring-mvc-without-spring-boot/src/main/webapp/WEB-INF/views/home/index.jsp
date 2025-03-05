<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader ("Expires", -1);
%>
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
<!-- Header -->
<header id="top" class="header">
    <div class="text-vertical-center">
        <h1>Spring MVC - Social Network</h1>
        <h3><spring:message code="home.header"></spring:message></h3>
        <br>
        <c:if test="${empty UserName}">
            <a href="<c:url value="/account/register"/>" class="btn btn-light btn-lg">
                <spring:message code="singUp"></spring:message>
            </a>
        </c:if>
    </div>
</header>
<!-- About -->
<section id="about" class="about">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h2><spring:message code="home.about_summary"></spring:message></h2>
                <p class="lead"><spring:message code="home.about_det"></spring:message>
                </p>
            </div>
        </div>
    </div>
</section>
<section id="services" class="services bg-primary">
    <div class="container">
        <div class="row text-center">
            <div class="col-lg-10 col-lg-offset-1">
                <h2><spring:message code="home.profits"></spring:message></h2>
                <hr class="small">
                <div class="row">
                    <div class="col-md-3 col-sm-6">
                        <div class="service-item">
                            <span class="fa-stack fa-4x">
                                <i class="fa fa-circle fa-stack-2x"></i>
                                <i class="fa fa-smile-o fa-stack-1x text-primary"></i>
                            </span>
                            <h4>
                                <strong><spring:message code="home.happiness"></spring:message></strong>
                            </h4>
                            <p><spring:message code="home.happines_det"></spring:message></p>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6">
                        <div class="service-item">
                                <span class="fa-stack fa-4x">
                                <i class="fa fa-circle fa-stack-2x"></i>
                                <i class="fa fa-bicycle fa-stack-1x text-primary"></i>
                            </span>
                            <h4>
                                <strong><spring:message code="home.entertainment"></spring:message></strong>
                            </h4>
                            <p><spring:message code="home.entertainment_det"></spring:message></p>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6">
                        <div class="service-item">
                            <span class="fa-stack fa-4x">
                                <i class="fa fa-circle fa-stack-2x"></i>
                                <i class="fa fa-users fa-stack-1x text-primary"></i>
                            </span>
                            <h4>
                                <strong><spring:message code="home.friendship"></spring:message></strong>
                            </h4>
                            <p><spring:message code="home.friendship_det"></spring:message></p>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6">
                        <div class="service-item">
                            <span class="fa-stack fa-4x">
                                <i class="fa fa-circle fa-stack-2x"></i>
                                <i class="fa fa-heart fa-stack-1x text-primary"></i>
                            </span>
                            <h4>
                                <strong><spring:message code="home.evenmore"></spring:message></strong>
                            </h4>
                            <p><spring:message code="home.evenmore_det"></spring:message></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!--categories-->
<section id="categories" class="bg-primary">
    <div class="container">
        <div class="row">
            <div class="col-lg-10 col-lg-offset-1 text-center">
                <h2 class="text-center"><spring:message code="categories"></spring:message></h2>
                <hr class="small">
                <div class="row">
                    <c:forEach var="category" items="${categories}">
                        <div class="col-md-4">
                            <div class="portfolio-item">
                                <div class="thumbnail">
                                    <img class="img img-thumbnail"
                                         style="width: 200px;height: 200px;"
                                         src="<c:url value="${category.imagePath}" />"
                                         alt="${category.name}"/>
                                    <div class="caption">
                                        <h4>${category.name}</h4>
                                        <p>${category.description}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</section>
<section id="portfolio" class="portfolio bg-primary">
    <div class="container">
        <div class="row">
            <div class="col-lg-10 col-lg-offset-1 text-center">
                <h2><spring:message code="events"></spring:message></h2>
                <hr class="small">
                <div class="row">
                    <c:forEach var="event" items="${events}">
                        <div class="col-md-4">
                            <div class="portfolio-item">
                                <div class="thumbnail">
                                    <img class="img img-thumbnail"
                                         style="width: 200px;height: 200px;"
                                         src="<c:url value="${event.imagePath}" />"
                                         alt="Upcoming event photo"/>
                                    <div class="caption">
                                        <h4>${event.name}</h4>
                                        <p>${description}</p>
                                    </div>
                                    <div class="ratings">
                                        <p class="label label-info pull-right">
                                                ${event.participants} <span class="glyphicon glyphicon-user"></span>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</section>

<%@ include file="/WEB-INF/views/template/footer.jsp" %>
<div id="languages">
    <a href="?language=pl"><img src="<c:url value="/resources/images/pl.png" />" alt="PL" /></a>
    <a href="?language=en"><img src="<c:url value="/resources/images/en.png" />" alt="EN" /></a>
</div>
<script src="<c:url value="/resources/js/jquery.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
</body>
</html>
