<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"
         import = "com.bidover.common.model.bean.User"
         %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <link rel="stylesheet" href="css/bo.main.css" type="text/css">
        <fmt:setLocale value="${sessionScope.LNG}" />
        <fmt:setBundle basename="Messages"/>
    </head>
    <body>
        <%
        User user = (User) session.getAttribute("profile");
        int UID = user.getId();
        String email = user.getEmail();
        String fam = user.getLastName();
        String name = user.getFirstName();
        boolean accActive = true;
        int userBalance = 2;
        int userSubscr = 0;
        String expSubscr = "";
        int lots_00 = 0;
        int lots_01 = 0;
        int lots_02 = 0;
        int lots_03 = 0;
        int lots_04 = 0;
        int lots_05 = 0;
        int fvrs_00 = 0;

        int deals_01 = 0;
        int deals_03 = 0;
        int deals_05 = 0;
        final int FEE = 1;
        String userPosts = "";
        %>
        <!-- HEADER -->
        <%@include file="header.jspf" %>
        <!-- CP -->
        <br>
        <table width="100%" align="center" cellpadding="3" cellspacing="2">
            <tr><td>
                    <table>
                        <tr>
                            <td><p class="title"><fmt:message key="lbl.account" />: <%=name%> (<fmt:message key="lbl.memberid" /> #<%=UID%>)</p></td>
                            <td>&nbsp;&nbsp;&nbsp;</td>
                            <td valign="top">
                                <table style="border-top: 1px solid #444">
                                    <tr><td><a href="./" class="small"><fmt:message key="btn.home" /></a>&nbsp;|&nbsp;<a href="Controller?command=LOG_OUT" class="small"><fmt:message key="btn.logout" /></a></td></tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                    <p>&nbsp;</p>
                    <table>
                        <tr><td><fmt:message key="lbl.status" />: <%if (accActive) {%><span style='color:#060'><fmt:message key="lbl.accountactive" /><%} else {%><span style='color:#f00'><fmt:message key="lbl.accountnotactive" /></span>&nbsp;&nbsp;&nbsp;<a href='cp.jsp?alt=67'><fmt:message key="btn.activate" /></a><%}%></span></td></tr>
                        <tr><td><fmt:message key="lbl.balance" />: <b><%if (userBalance <= 0) {%><span style="color:#f00"><%=userBalance%></span><%} else {%><%=userBalance%><%}%>&nbsp;USD</b><%if (accActive) {%> &nbsp;&nbsp;|&nbsp;<a href="?alt=61"><fmt:message key="btn.incbalance" /><%}%></a></td></tr>
                        <tr><td><fmt:message key="lbl.sbn" />: <b><%=userSubscr%></b> <fmt:message key="lbl.sbn.deals" />
                                <%if (userSubscr > 0) {%>(<fmt:message key="lbl.sbn.expire" />: <%=expSubscr%>)<%}%>
                                <%if (accActive) {%> &nbsp;&nbsp;|&nbsp;<a href="?alt=62"><fmt:message key="btn.subscribe" /><%}%></a>
                        </td></tr>
                    </table>
                </td>
            </tr>
        </table>
        <table width="100%" align="center" cellpadding="10" cellspacing="2">
            <tr>
                <td width="33%" valign="top">
                    <table width='100%'><tr><td valign='middle'><span class='smtitle'><fmt:message key="lbl.lotsforsales" /></span>
                                <table width='100%'>
                                    <tr><td colspan='3'>&nbsp;</td></tr>
                                    <tr><td width='40'></td><td><img src='css/bo.info.024x024.png' border='0' alt=""></td><td valign='middle'><a href='Controller?command=SHOW_AUTO_BY_USER'><fmt:message key="lbl.waitsales" /></a> <sup><%=lots_00%></sup></td></tr>
                                    <tr><td width='40'></td><td><img src='css/bo.alert.024x024.png' border='0' alt=""></td><td valign='middle'><a href='?alt=11'><fmt:message key="lbl.waitdecision" /></a> <sup><%=lots_01%></sup></td></tr>
                                    <tr><td width='40'></td><td><img src='css/bo.cancel.024x024.png' border='0' alt=""></td><td valign='middle'><a href='?alt=12'><fmt:message key="lbl.notsold" /></a> <sup><%=lots_02%></sup></td></tr>
                                    <tr><td width='40'></td><td><img src='css/bo.ok.024x024.png' border='0' alt=""></td><td valign='middle'><a href='?alt=13'><fmt:message key="lbl.sold" /></a> <sup><%=lots_03%></sup></td></tr>
                                    <tr><td width='40'></td><td><img src='css/bo.file.024x024.png' border='0' alt=""></td><td valign='middle'><a href='?alt=14'><fmt:message key="lbl.archives" /></a> <sup><%=lots_05%></sup></td></tr>
                                </table>
                    </td></tr></table>
                    <br>
                    <% if (!accActive || userBalance < FEE) {%><table width='100%'><tr><td width='40'><img src='css/bo.add-disabled.032x032.png' border='0' alt=""></td><td valign='middle'><fmt:message key="lbl.placelot" /></td></tr></table>
                    <% } else {%><table width='100%'><tr><td width='40'><img src='css/bo.add.032x032.png' border='0' alt=""></td><td valign='middle'><a href="chooseAdd.jsp"><fmt:message key="lbl.placelot" /></a></td></tr></table>
                    <% }%>
                </td>
                <td width="33%" rowspan="4" valign="top">
                    <table width='100%'><tr><td valign='middle'><span class='smtitle'><fmt:message key="lbl.lotsbuyed" /></span>
                                <table width='100%'>
                                    <tr><td colspan='3'>&nbsp;</td></tr>
                                    <tr><td width='40'></td><td><img src='css/bo.alert.024x024.png' border='0' alt=""></td><td valign='middle'><a href='?alt=21'><fmt:message key="lbl.waitconfirm" /></a> <sup><%=deals_01%></sup></td></tr>
                                    <tr><td width='40'></td><td><img src='css/bo.ok.024x024.png' border='0' alt=""></td><td valign='middle'><a href='?alt=23'><fmt:message key="lbl.confirmed" /></a> <sup><%=deals_03%></sup></td></tr>
                                    <tr><td width='40'></td><td><img src='css/bo.file.024x024.png' border='0' alt=""></td><td valign='middle'><a href='?alt=24'><fmt:message key="lbl.archives" /></a> <sup><%=deals_05%></sup></td></tr>
                                </table>
                    </td></tr></table>
                </td>
                <td width="33%" style='border-left: 1px solid #444'>
                    <table width='100%'><tr><td width='40'><img src='css/bo.favorites.032x032.png' border='0' alt=""></td><td valign='middle'><a href='?alt=66'><fmt:message key="lbl.favorites" /></a> <sup><%=fvrs_00%></sup></td></tr></table>
                    <table width='100%'><tr><td width='40'><img src='css/bo.search.032x032.png' border='0' alt=""></td><td valign='middle'><a href='lot.jsp'><fmt:message key="lbl.searchlot" /></a></td></tr></table>
                    <table width='100%'><tr><td width='40'><img src='css/bo.contact.032x032.png' border='0' alt=""></td><td valign='middle'><a href='?alt=69'><fmt:message key="lbl.postbox" /></a> <sup><%=userPosts%></sup></td></tr></table>
                    <table width='100%'><tr><td width='40'><img src='css/bo.user.032x032.png' border='0' alt=""></td><td valign='middle'><a href='Controller?command=registration'><fmt:message key="lbl.regpack" /></a></td></tr></table>
                </td>
            </tr>
        </table>
        <!-- FOOTER -->
        <%@include file="footer.jspf" %>
    </body>
</html>