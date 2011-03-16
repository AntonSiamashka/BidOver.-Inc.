<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="css/bo.main.css" type="text/css">
    <fmt:setLocale value="${sessionScope.LNG}" />
    <fmt:setBundle basename="Messages"/>
    <script type="text/javascript" src="js/http.request.js"></script>
    <script type="text/javascript" src="js/user.login.js"></script>
</head>

<body>

<p>&nbsp;</p>

<div>
<table width="100%" align="center" cellpadding="3" cellspacing="2" >
<tr><td>
    <table>
    <tr>
    <td valign="top"><p class="title"><fmt:message key="lbl.signin" /></p></td>
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

<div>
<p class="max"><fmt:message key="mrk26" /></p>
<form name="userlogin" onSubmit="getUserLoginInfo();return false;" action="">
<table cellpadding="5" cellspacing="0">
<tr><td align="center" colspan="2"><span id="msg" style="color:#f00"></span></td></tr>
<tr><td width="150" align="right" valign="top"><fmt:message key="lbl.login" />:<div class="small">(<fmt:message key="lbl.youremail" />)</div></td><td valign="top"><input id="email" name="email" type="text" size="40" maxlength="40" value=""></td></tr>
<tr><td width="150" align="right" valign="top"><fmt:message key="lbl.password" />:</td><td valign="top"><input id="pass" name="pass" type="password" size="40" maxlength="40" value=""></td></tr>
<tr><td width="150" align="right"><span id="progress_indicator" style="visibility:hidden"><img src="css/bo.progress_bar.gif" alt=""></span></td><td><input type="button" name="login" value="<fmt:message key="btn.signin" />" onClick="getUserLoginInfo();"></td></tr>
<tr><td align="center" colspan="2"><br><br></td></tr>
<tr><td align="right"><img src="css/bo.about.032x032.png" border="0" alt=""></td><td><a href="passwordReminder.jsp"><fmt:message key="btn.fogotpassword" /></a></td></tr>
<tr><td align="right"><img src="css/bo.user.032x032.png" border="0" alt=""></td><td><a href="fm.signup.jsp"><fmt:message key="btn.regnewuser" /></a></td></tr>
</table>
</form>
</div>

<%@include file="footer.jspf" %>

</body>
</html>