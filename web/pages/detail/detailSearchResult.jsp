<%-- 
    Document   : searchResult
    Created on : 27.06.2010, 15:12:42
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
        <h1>Search result!</h1>
        <table>
            <c:forEach var="detail" items="${detailList}">
                <tr>
                    <td>
                        <a href="DetailController?command=SHOW_DETAIL_VIEW&detailID=${detail.id}">Make: ${detail.make.title}, Model: ${detail.model.title}, Begin Year: ${detail.beginYear}, End Year: ${detail.endYear}</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
