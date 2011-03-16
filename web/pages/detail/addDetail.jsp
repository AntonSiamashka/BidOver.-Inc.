<%-- 
    Document   : addDevice
    Created on : 26.06.2010, 23:02:43
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
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/bo.main.css" type="text/css">
        <link rel="stylesheet" href="css/jquery.fancybox.css" type="text/css" media="screen">
        <script type="text/JavaScript" src="scripts/common/variables.jsp" ></script>
        <script src="scripts/detail/Add.js" type="text/javascript" ></script>
        <script src="scripts/detail/ShowPreview.js" type="text/javascript" ></script>
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
    <body>
        <center>
            <h2>AddDetail!</h2>
        </center>
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

        <form action="DetailController?command=ADDING_DETAIL" method="post">
            <div id="add-fields">
                <table cellpadding="30" cellspacing="20">
                    <tr>
                        <td valign ="top">
                            <table>
                                <tr>
                                    <td>
                                        Category
                                    </td>
                                    <td>
                                        <select name="category-select" id="category-select" onchange="getSubCategory()" size="10">
                                            <c:forEach var="category" items="${categoryList}">
                                                <option value="${category.id}">${category.name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Subcategory
                                    </td>
                                    <td>
                                        <div id="sub-category-div" style="visibility:hidden;display:none;">
                                            <select name="sub-category-select" id="sub-category-select" onchange="getItem()" size="10"></select>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Item
                                    </td>
                                    <td>
                                        <div id="item-div" style="visibility:hidden;display:none;">
                                            <select name="item-select" id="item-select" onchange="showPreviewButton()" size="10"></select>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td valign="top">
                            <table>
                                <tr>
                                    <td>
                                        Make
                                    </td>
                                    <td>
                                        <select id="make-select" name="make-select" onchange="getModel()" size="10">
                                            <c:forEach var="make" items="${makeList}">
                                                <option value="${make.id}">${make.title}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Model
                                    </td>
                                    <td>
                                        <div id="model-div" style="visibility:hidden;display:none;">
                                            <select name="model-select" id="model-select" onchange="getYear()" size="10"></select>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Year
                                    </td>
                                    <td>
                                        <div id="year-div" style="visibility:hidden;display:none;">
                                            <select name="year-begin-select" id="year-begin-select"></select>
                                            <select name="year-end-select" id="year-end-select"></select>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
                <div id="sales_details">
                    <%@include file="/pages/common/salesDetails.jspf" %>
                </div>
                <div  id="preview_div" >
                </div>
                <input style="visibility:hidden;display:none" id="preview-button" type="button" value="Preview" OnClick="createPreview()"/>
            </div>
            <div id="preview_form">
            </div>
            <div id="preview" style="visibility:hidden;display:none">
                <table id="preview-table"></table>
                <input id="change-button" type="button" value="Change" OnClick="showAddFields()"/>
                <input id="add-button" type="submit" value="Add" name="add" />
            </div>
        </form>
    </body>
</html>
