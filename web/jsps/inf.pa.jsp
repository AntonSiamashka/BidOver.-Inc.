<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<link rel="stylesheet" href="css/bo.main.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<fmt:setLocale value="${sessionScope.LNG}" />
<fmt:setBundle basename="auxi.Messages"/>
<table style="border-top: 1px solid #444">
    <tr><td><img src="css/bo.home.024x024.png" border="0" alt=""></td><td valign="middle"><a href="index.jsp" class="small"><fmt:message key="btn.home" /></a></td></tr>
</table>
<div>
<c:if test="${sessionScope.LNG=='en'}"><%@include file="../res/pa_en.inc"%></c:if>
<c:if test="${sessionScope.LNG=='ru'}"><%@include file="../res/pa_ru.inc"%></c:if>
</div>
</body>
</html>