<%-- 
    Document   : userAuto
    Created on : 07.04.2010, 2:45:36
    Author     : Jedai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"
        import = "java.util.List"
        import = "java.util.Date"
        %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" href= "./css/bo.main.css" type= "text/css"/>
        <script type="text/JavaScript" src="scripts/viewInPopUp.js"> </script>
        <script type="text/javascript" src="scripts/lightbox/prototype.js"></script>
        <script type="text/javascript" src="scripts/lightbox/scriptaculous.js?load=effects,builder"></script>
        <script type="text/javascript" src="scripts/lightbox/lightbox.js"></script>
        <script type="text/javascript" src="js/jquery/jquery.js"></script>
        <script type="text/JavaScript">
            $(document).ready(function(){
                $(".stripeT tr:nth-child(odd)").addClass("odd");
            });
        </script>
        <link rel="stylesheet" href="styles/lightbox.css" type="text/css" media="screen" />
        <title>My Auto</title>
        <fmt:setLocale value="${sessionScope.LNG}" />
        <fmt:setBundle basename="Motors"/>
    </head>
    <body>
    <%@include file="header.jspf" %>
    
    <c:if test="${not empty autos}">
        <table id="auto-table" cellpadding="5" cellspacing="1" class="stripeT">
        <thead><tr align="center">
            <td width="10%"><fmt:message key="lbl.photo" /></td>
            <td width="50%"><fmt:message key="lbl.lot" /></td>
            <td width="20%"><fmt:message key="lbl.currenthighbid" /></td>
            <td width="20%"><fmt:message key="lbl.timeleft" /></td>
        </tr></thead>
        <c:forEach var="auto" items="${autos}">
            <tr>
            <td align="center"><a href="images/pics/${auto.lot.id}/${auto.lot.id}_1_.jpg" rel="lightbox" title=""><img src="images/pics/${auto.lot.id}/${auto.lot.id}_1_small.jpg" width="100" height="100" alt="" border="0"/></a></td>
            <td valign="top">
                <a href="Controller?command=SHOW_AUTO_VIEW&auto_id=${auto.id}"><c:out value="${auto.year} ${auto.characteristics.make} ${auto.characteristics.model}"></c:out></a>
                <div><c:out value="Door: ${auto.doors.title}, Engine: ${auto.engine.title}, Transmission: ${auto.transmission.title}, Interior type: ${auto.interiorType.title}, Tires: ${auto.idTires.title}, Odometer: ${auto.odometer}"></c:out></div>
            </td>
            <td valign="top" align="center"> x </td>
            <td valign="top" align="center">
                <c:if test="${auto.lot.isBiddingClosed}"><span class="err">Sales Closed!</span></c:if>
                <c:if test="${!auto.lot.isBiddingClosed}"><span id="TimeLeft">${auto.lot.formattedTimeLeft}</span></c:if>
            </td>
            </tr>
        </c:forEach>
        </table>
    </c:if>

    <%@include file="footer.jspf" %>
    </body>
</html>
