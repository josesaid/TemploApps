<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${not empty UserName}">
        <nav class="navbar" role="navigation">
            <div class="container">
                <div>
                    <a class="navbar-brand pull-left" href="<c:url value="/"/>">Spring MVC</a>
                </div>
                <div>
                    <ul class="nav navbar-nav pull-right">
                        <li>
                            <a href="<c:url value="/user"/>"> ${UserName} (${Role})<span class="glyphicon glyphicon-user" style="margin-left: 3px;"> </span></a>
                        </li>
                        <li>
                            <a href="<c:url value="/user/logout"/>"><span class="glyphicon glyphicon-off"></span></a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </c:when>
    <c:otherwise>
        <nav class="navbar" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="<c:url value="/"/>">Spring MVC</a>
                </div>
                <ul class="nav navbar-nav">
                    <li id="about_link" class="page-scroll"><a href="#about"><spring:message code="about"></spring:message></a></li>
                    <li><a id="categories_link" href="#categories"><spring:message code="categories"></spring:message></a></li>
                    <li><a id="portfolio_link" href="#portfolio"><spring:message code="events"></spring:message></a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="<c:url value="/account/register"/>"><span class="glyphicon glyphicon-user"></span> <spring:message code="singUp"></spring:message></a></li>
                    <li><a href="<c:url value="/account/login"/>"><span class="glyphicon glyphicon-log-in"></span> <spring:message code="logIn"></spring:message></a></li>
                </ul>
            </div>
        </nav>
    </c:otherwise>
</c:choose>
