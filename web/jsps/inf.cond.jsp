<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/bo.main.css" type="text/css">
<title></title>
</head>
<body>
<fmt:setLocale value="${sessionScope.LNG}" />
<fmt:setBundle basename="Messages"/>
<div>
<c:if test="${sessionScope.LNG=='en'}"><%@include file="../res/cond_en.inc"%></c:if>
<c:if test="${sessionScope.LNG=='ru'}"><%@include file="../res/cond_ru.inc"%></c:if>
</div>
</body>
</html>