<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/bo.main.css" type="text/css">
<title></title>
</head>
<body topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0">
<fmt:setLocale value="${sessionScope.LNG}" />
<fmt:setBundle basename="Messages"/>
<table style="border-top: 1px solid #444">
<tr><td><img src="../css/bo.home.024x024.png" border="0"></td><td valign="middle"><a href="../" class="small"><fmt:message key="btn.home" /></a></td></tr>
</table>
<div>
<c:if test="${sessionScope.LNG=='en'}"><%@ include file="../res/ua_en.inc"%></c:if>
<c:if test="${sessionScope.LNG=='ru'}"><%@ include file="../res/ua_ru.inc"%></c:if>
</div>
</body>
</html>