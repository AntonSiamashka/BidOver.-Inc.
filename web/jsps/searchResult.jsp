
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/JavaScript" src="scripts/viewInPopUp.js"></script>
        <link rel="stylesheet" href="css/bo.main.css" type="text/css">
        <script type="text/javascript" src="js/jquery/jquery.js"></script>
        <script type="text/JavaScript">
            $(document).ready(function(){
                $(".stripeT tr:nth-child(odd)").addClass("odd");
            });
        </script>
        <fmt:setLocale value="${sessionScope.LNG}" />
        <fmt:setBundle basename="Messages"/>
        <title></title>
    </head>
    <body>
    <!-- HEADER -->
    <table width="100%">
    <tr>
    <td width="38%" valign="top">
        <span class="logo">bid<span style="color: #600">Over</span></span>
        <p class="logounderline">on-line auctions</p>
        <c:if test="${status==2}"><p class="small"><fmt:message key="lbl.goto" /> <a href="./cp.jsp"><fmt:message key="btn.ctrlpanel" /></a> <fmt:message key="lbl.or" /> <a type="submit" href="Controller.do?command=LOG_OUT"><fmt:message key="btn.logout" /></a></p></c:if>
        <c:if test="${status!=2}"><p class="small"><fmt:message key="lbl.welcome" /> <a href="./login.jsp"><fmt:message key="btn.signin" /></a> <fmt:message key="lbl.or" /> <a href="registration.jsp"><fmt:message key="btn.register" /></a></p></c:if>
    </td>
    <td valign="top">
        <table style="border-top: 1px solid #444" align="right"><tr><td><a href="run.jsp?alt=4" class="small"><fmt:message key="btn.contact" /></a> | <a href="run.jsp?alt=9" class="small"><fmt:message key="btn.help" /></a></td></tr></table>
    </td>
    </tr>
    </table>
    <p>&nbsp;</p>
    <!-- END OF HEADER -->
    <!-- TITLE -->
    <table>
	<tr>
        <td valign="top"><p class="title"><fmt:message key="lbl.category" /></p></td>
        <td>&nbsp;&nbsp;&nbsp;</td>
		<td valign="top">
			<table style="border-top: 1px solid #444">
			<tr><td><img src="css/bo.home.024x024.png" border="0" alt=""></td><td valign="middle"><a href="index.jsp" class="small"><fmt:message key="btn.home" /></a></td></tr>
			<tr><td><img src="css/bo.search.024x024.png" border="0" alt=""></td><td valign="middle"><a href="Controller.do?command=SHOW_SEARCH" class="small"><fmt:message key="btn.newsearch" /></a></td></tr>
			</table>
		</td>
        </tr>
	</table>
    <!-- END OF TITLE -->
        <table id="main-table" cellspacing = "0">
            <tr class="alt">
                <td class="left-bar">
                    <div id="left-sidebar">
                        <c:if test="${not empty makes}">
                            <c:forEach var="make" items="${makes}">
                                <ul>
                                    <li><a href="Controller.do?command=SHOW_AUTO_BY_MAKE&make_id=${make.id}">${make.title}</a></li>
                                </ul>
                            </c:forEach>
                        </c:if>
                    </div>
                </td>
                <td class="content">
                    <table width="100%" cellpadding="5" cellspacing="1" class="stripeT">
                    <thead><tr align="center">
                        <td width="10%"><fmt:message key="lbl.photo" /></td>
                        <td width="50%"><fmt:message key="lbl.lot" /></td>
                        <td width="20%"><fmt:message key="lbl.currenthighbid" /></td>
                        <td width="20%"><fmt:message key="lbl.timeleft" /></td>
                     </tr></thead>
                     <%@include file="lotPreview.jspf" %>
                    </table>
                </td>
            </tr>
        </table>
<!-- FOOTER -->
<%@include file="footer.jspf" %>
<!-- END OF FOOTER -->
</body>
</html>
