<%-- 
    Document   : detailView
    Created on : 28.06.2010, 11:35:23
    Author     : Jedai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Detail view!</h1>
        <table>
            <tr>
                <td>
                    Make
                </td>
                <td>
                    ${detail.make.title}
                </td>
            </tr>
            <tr>
                <td>
                    Model
                </td>
                <td>
                    ${detail.model.title}
                </td>
            </tr>
            <tr>
                <td>
                    Category
                </td>
                <td>
                    ${detail.category.name}
                </td>
            </tr>
            <tr>
                <td>
                    Subcategory
                </td>
                <td>
                    ${detail.subCategory.name}
                </td>
            </tr>
            <c:if test="${not empty detail.item}">
            <tr>
                <td>
                    Item
                </td>
                <td>
                    ${detail.item.name}
                </td>
            </tr>
            </c:if>
            <tr>
                <td>
                    Begin Year
                </td>
                <td>
                    ${detail.beginYear}
                </td>
            </tr>
            <tr>
                <td>
                    End Year
                </td>
                <td>
                    ${detail.endYear}
                </td>
            </tr>
        </table>
    </body>
</html>
