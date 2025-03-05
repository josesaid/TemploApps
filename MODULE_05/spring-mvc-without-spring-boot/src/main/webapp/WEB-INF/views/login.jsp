<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/login-register.css"/>">
</head>
<body>
<spring:message var="nick" code="account.user"/>
<spring:message var="password" code="account.pass"/>
<%@ include file="/WEB-INF/views/template/navbar.jsp" %>
<div class="row">
    <div class="col-md-6 col-md-offset-3">
        <div class="panel panel-login">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-xs-6">
                        <a href="" class="active" id="login-form-link"><spring:message code="account.singUpIn.in"></spring:message></a>
                    </div>
                    <div class="col-xs-6">
                        <a href="" id="register-form-link"><spring:message code="account.singUpIn.up"/></a>
                    </div>
                </div>
                <hr>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-12">
                        <form:form id="login-form" modelAttribute="form" method="post" action="/springmvc/j_spring_security_check">
                            <c:if test="${loginFailed}">
                                <div class="alert alert-danger"><spring:message code="account.login.failed"/></div>
                            </c:if>
                            <div class="form-group">
                                <form:input path="userSignIn.login" cssClass="form-control" placeholder="${nick}" />
                                <form:errors path="userSignIn.login" cssClass="alert alert-danger" element="div" />
                            </div>
                            <div class="form-group">
                                <form:password path="userSignIn.passwordLogin" cssClass="form-control" placeholder="${password}" />
                                <form:errors path="userSignIn.passwordLogin" cssClass="alert alert-danger" element="div" />
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-sm-6 col-sm-offset-3">
                                        <input type="submit" name="login-submit" id="login-submit" class="form-control btn btn-login" value="<spring:message code="logIn"></spring:message>">
                                    </div>
                                </div>
                            </div>

                        </form:form>
                        <form:form id="register-form" action="register"  method="post" modelAttribute="form">
                            <c:if test="${registerSucced}">
                                <div class="alert alert-success"><spring:message code="account.register.succeed"/></div>
                            </c:if>
                            <div class="form-group">
                                <form:input path="user.nick" cssClass="form-control" placeholder="${nick}" />
                                <form:errors path="user.nick" cssClass="alert alert-danger" element="div" />
                            </div>
                            <div class="form-group">
                                <spring:message var="email" code="account.email"/>
                                <form:input path="user.email" cssClass="form-control" placeholder="${email}" />
                                <form:errors path="user.email" cssClass="alert alert-danger" element="div" />
                            </div>
                            <div class="form-group">
                                <form:password path="user.password" cssClass="form-control" placeholder="${password}" />
                                <form:errors path="user.password" cssClass="alert alert-danger" element="div" />
                            </div>
                            <div class="form-group">
                                <spring:message var="passwconf" code="account.passconf"/>
                                <form:password path="user.confirmPassword" cssClass="form-control" placeholder="${passwconf}" />
                                <form:errors path="user.confirmPassword" cssClass="alert alert-danger" element="div" />
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-sm-6 col-sm-offset-3">
                                        <input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="<spring:message code="singUp"></spring:message>">
                                    </div>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/views/template/footer.jsp" %>
<div id="languages">
    <a href="?language=pl"><img src="<c:url value="/resources/images/pl.png" />" alt="PL" /></a>
    <a href="?language=en"><img src="<c:url value="/resources/images/en.png" />" alt="EN" /></a>
</div>
<script src="<c:url value="/resources/js/jquery.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
<script src="<c:url value="/resources/js/login-register.js"/>"></script>
</body>
</html>
