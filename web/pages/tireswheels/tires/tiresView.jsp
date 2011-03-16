<%-- 
    Document   : tiresView
    Created on : 03.08.2010, 18:27:22
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
        <table>
            <tr>
                <td>
                    Width
                </td>
                <td>
                    ${tires.tiresWidth.value}
                </td>
            </tr>
            <tr>
                <td>
                    Contour
                </td>
                <td>
                    ${tires.tiresContour.value}
                </td>
            </tr>
            <tr>
                <td>
                    Diameter
                </td>
                <td>
                    ${tires.tiresDiameter.value}
                </td>
            </tr>
            <tr>
                <td>
                    Season
                </td>
                <td>
                    ${tires.tiresSeason.value}
                </td>
            </tr>
            <tr>
                <td>
                    Make
                </td>
                <td>
                    ${tires.tiresMake.title}
                </td>
            </tr>
        </table>
    </body>
</html>
