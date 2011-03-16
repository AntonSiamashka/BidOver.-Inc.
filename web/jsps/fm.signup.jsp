<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="css/bo.main.css" type="text/css">
    <fmt:setLocale value="${sessionScope.LNG}" />
    <fmt:setBundle basename="Messages"/>
    <script type="text/javascript" src="js/http.request.js"></script>
    <script type="text/javascript" src="js/user.signup.js"></script>
</head>
<body>
<p>&nbsp;</p>

<div>
<table width="100%" align="center" cellpadding="3" cellspacing="2" >
<tr><td>
    <table>
    <tr>
    <td valign="top"><p class="title"><fmt:message key="lbl.registration" /></p></td>
    <td>&nbsp;&nbsp;&nbsp;</td>
	<td valign="top">
		<table style="border-top: 1px solid #444">
		<tr><td><img src="css/bo.home.024x024.png" border="0" alt=""></td><td valign="middle"><a href="./" class="small"><fmt:message key="btn.home" /></a></td></tr>
		</table>
	</td>
    </tr>
    </table>
</td></tr>
</table>
</div>

<div class="small">
<c:if test="${sessionScope.LNG=='en'}"><%@include file="../res/reg_en.inc"%></c:if>
<c:if test="${sessionScope.LNG=='ru'}"><%@include file="../res/reg_ru.inc"%></c:if>
</div>
<br>
<div>
<b><fmt:message key="mrk24" /></b>
<form name="usersignup" onSubmit="getUserRegInfo();return false;" action="">
<table width="100%" cellpadding="5" cellspacing="0">
<tr><td width="20%"></td><td><span id="msg" style="color:#f00"></span></td></tr>
<tr><td width="20%" align="right" valign="top"><fmt:message key="lbl.youremail" />:</td><td><input id="email" name="email" type="text" size="40" maxlength="40" value=""></td></tr>
<tr><td width="20%" align="right"><span id="progress_indicator" style="visibility:hidden"><img src="css/bo.progress_bar.gif" alt=""></span></td><td><input type="button" name="signup" onClick="getUserRegInfo();" value='<fmt:message key="btn.register" />'></td></tr>
</table>
</form>
</div>

<%@include file="footer.jspf" %>

</body>
</html>
