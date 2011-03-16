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
    <c:if test="${empty wheelsList}">
        <h1>No wheels found!</h1>
    </c:if>

    <body>
        <table>
            <c:forEach var="wheels" items="${wheelsList}">
                <tr>
                    <td>
                        <a href="TiresController?command=SHOW_WHEELS_VIEW&wheelsID=${wheels.id}">${wheels.id}</a>
                        Width ${wheels.wheelsWidth.value} Diameter ${wheels.wheelsDiameter.value} BCD${wheels.wheelsBCD.value} ED ${wheels.wheelsED.value}
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
