<%@page contentType="text/html" pageEncoding="UTF-8"
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="css/bo.main.css" type="text/css">
    <fmt:setLocale value="${sessionScope.LNG}" />
    <fmt:setBundle basename="Messages"/>
</head>
<body>
    <%@include file="jsps/header.jspf" %>
    
    <div>
        <!-- SEARCH -->
        <table width="100%">
          <tr>
          <td valign="top">
            <div>
            <form name="srch_by_keyword" method="post" action="lot.jsp">
              <input type="hidden" name="alt" value="8">
              <table cellpadding="3" cellspacing="0" align="right"><tr>
              <td><input tabindex="1" type="text" name="keyword" id="KeyWord" size="30"></td>
              <td><%//=selectRN%></td>
              <td><input type="submit" name="srch_by_keyword" value="<fmt:message key="btn.find" />"></td>
              <td valign="bottom"><a href="#" class="small"><fmt:message key="btn.advsearch" /></a></td>
              </tr></table>
            </form>
            </div>
          </td>
          </tr>
        </table>
    </div>
<!-- BODY -->
<table width="100%">
  <tr>
    <td width="38%" valign="top"><p class="smtitle"><fmt:message key="lbl.bidovernews" /></p><%//=printNews%></td>
    <td valign="top">
    <c:if test="${status==2}">
    <p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>
    </c:if>
    <c:if test="${status!=2}">
        <div>
            <table>
                <tr>
                    <td><p class="greeting"><fmt:message key="lbl.welcometo" />&nbsp;bidOver!</p></td>
                </tr>
                <tr>
                    <td align="right">
                        <%@include file="jsps/logout.jspf" %>
                    </td>
                </tr>
            </table>
        </div>
    </c:if>
    <table width="100%" style="border-top: 1px solid #444">
    <tr><td>
	    <table><tr><td><div class="title"><fmt:message key="lbl.categories" /></div></td><td valign="top">&nbsp;&nbsp;|&nbsp;<a href="#" class="small"><fmt:message key="btn.allcategories" /></a></td></tr></table>
        <br>
        <table cellpadding="10" cellspacing="10">
            <tr>
                <td valign="top" width="50%"><div><a href="Controller.do?command=SHOW_SEARCH">Vehicles</a></div><div><a href="DetailController.do?command=SEARCH_DETAIL">Spare Parts</a></div><div><a href="tiresIndex.jsp">Wheels & Tyres</a></div></td>
                <td valign="top" width="50%"><div>Batteries</div><div>Automotive fluids and greases</div><div>Car care products</div></td>
            </tr>
        </table>
    </td></tr>
    </table>
    </td>
  </tr>
</table>
<!-- FOOTER -->
<%@include file="jsps/footer.jspf" %>
<!-- END OF FOOTER -->
</body>
</html>
