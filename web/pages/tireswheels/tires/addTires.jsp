<%-- 
    Document   : addTires
    Created on : 03.08.2010, 18:26:55
    Author     : Jedai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/JavaScript" src="scripts/common/variables.jsp" ></script>
        <script src="scripts/tires/validateAddTires.js" type="text/javascript" ></script>
        <script src="scripts/tires/PreviewTires.js" type="text/javascript" ></script>
        <script type="text/JavaScript" src="scripts/common/PreviewLot.js" > </script>
        <script type="text/JavaScript" src="scripts/common/LotDetails.js" > </script>
        <script type="text/javascript" src="js/http.request.js"></script>
        <script type="text/javascript" src="js/lot.cond.js"></script>
        <script type="text/javascript" src="js/lot.payment.js"></script>
        <script type="text/javascript" src="js/lot.shipping.js"></script>
        <script type="text/javascript" src="js/lot.add.js"></script>
        <script type="text/javascript" src="js/lot.location.js"></script>
        <script type="text/javascript" src="js/jquery/jquery.js"></script>
        <script type="text/javascript" src="js/jquery/jquery.fancybox.js"></script>
        <link rel="stylesheet" href="css/bo.main.css" type="text/css">
        <link rel="stylesheet" href="css/jquery.fancybox.css" type="text/css" media="screen">
        <script type="text/javascript">
            $(document).ready(function(){
                $("#cond a").fancybox({
                    frameWidth: 600,
                    frameHeight: 400,
                    overlayShow: false,
                    hideOnContentClick: false
                });
            });
        </script>
        <fmt:setLocale value="${sessionScope.LNG}" />
        <fmt:setBundle basename="Messages"/>
    </head>
    <body onload="getSalesDetails()">
        <center>
            <h2>Add Tires!</h2>
        </center><br>
        <table width="100%">
            <tr><td>
                    <table>
                        <tr>
                            <td valign="top"><p class="title"><fmt:message key="lbl.account" />: <%//=userName%> (<fmt:message key="lbl.memberid" /> #<%//=UID%>)</p></td>
                            <td>&nbsp;&nbsp;&nbsp;</td>
                            <td valign="top">
                                <table style="border-top: 1px solid #444">
                                    <tr><td><img src="css/bo.home.024x024.png" border="0" alt=""></td><td valign="middle"><a href="./" class="small"><fmt:message key="btn.home" /></a></td></tr>
                                    <tr><td><img src="css/bo.arrow-left.024x024.png" border="0" alt=""></td><td valign="middle"><a href="./cp.jsp" class="small"><fmt:message key="btn.back" /></a></td></tr>
                                    <tr><td><img src="css/bo.arrow-left.024x024.png" border="0" alt=""></td><td valign="middle"><a href="#" class="small"><fmt:message key="btn.tolotslist" /></a></td></tr>
                                </table>
                            </td>
                        </tr>
                    </table>
            </td></tr>
        </table>
        <br><br>
        <center>
            <form action="TiresController?command=ADDING_TIRES" method="post">
                <div id="add-fields">
                    Ширина
                    <select name="width-select" id="width-select" size="1" >
                        <c:forEach var="width" items="${widthList}">
                            <option value="${width.id}">${width.value}</option>
                        </c:forEach>
                    </select>
                    Профиль
                    <select name="contour-select" id="contour-select" size="1">
                        <c:forEach var="contour" items="${contourList}">
                            <option value="${contour.id}">${contour.value}</option>
                        </c:forEach>
                    </select>
                    Диаметр
                    <select name="diameter-select" id="diameter-select" size="1">
                        <c:forEach var="diameter" items="${diameterList}">
                            <option value="${diameter.id}">${diameter.value}</option>
                        </c:forEach>
                    </select>
                    Сезонность
                    <select name="season-select" id="season-select" size="1">
                        <c:forEach var="season" items="${seasonList}">
                            <option value="${season.id}">${season.value}</option>
                        </c:forEach>
                    </select>
                    Производитель
                    <select name="make-select" id="make-select" size="1">
                        <c:forEach var="make" items="${makeList}">
                            <option value="${make.id}">${make.title}</option>
                        </c:forEach>
                    </select>
                    <hr/>
                    <div id="sales_details">
                        <%@include file="/pages/common/salesDetails.jspf" %>
                    </div>
                    <div  id="preview_div" >
                    </div>
                    <input style="visibility:visible;display:block" id="preview-button" type="button" value="Preview" OnClick="createPreview()"/>
                </div>

                <div id="preview" style="visibility:hidden;display:none">
                    <table id="preview-table"></table>
                    <input id="change-button" type="button" value="Change" OnClick="showAddFields()"/>
                    <input id="add-button" type="submit" value="Add" name="add" OnClick="return validateAddTires()"/>
                </div>
            </form>
        </center>
    </body>
</html>
