<%-- 
    Document   : searchResult
    Created on : 03.08.2010, 18:28:29
    Author     : Jedai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${empty tiresList}">
            <h1>No tires found!</h1>
        </c:if>
        <table>
            <c:forEach var="tires" items="${tiresList}">
                <tr>
                    <td>
                        <a href="TiresController?command=SHOW_TIRES_VIEW&tiresID=${tires.id}">${tires.id}</a>
                        Width ${tires.tiresWidth.value} Contour ${tires.tiresContour.value}  Diameter ${tires.tiresDiameter.value} Season ${tires.tiresSeason.value}
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
