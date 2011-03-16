<%--
    Document   : addTires
    Created on : 03.08.2010, 18:26:55
    Author     : Jedai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="scripts/tires/validateFindTires.js" type="text/javascript" ></script>
        <title>JSP Page</title>
    </head>
    <body>
        <center>
            <form action="TiresController?command=SEARCHING_TIRES" method="post">
                Ширина
                <select name="width-select" id="width-select" size="1" >
                    <option value="none" selected="true">---</option>
                    <c:forEach var="width" items="${widthList}">
                        <option value="${width.id}">${width.value}</option>
                    </c:forEach>
                </select>
                Профиль
                <select name="contour-select" id="contour-select" size="1">
                    <option value="none" selected="true">---</option>
                    <c:forEach var="contour" items="${contourList}">
                        <option value="${contour.id}">${contour.value}</option>
                    </c:forEach>
                </select>
                Диаметр
                <select name="diameter-select" id="diameter-select" size="1">
                    <option value="none" selected="true">---</option>
                    <c:forEach var="diameter" items="${diameterList}">
                        <option value="${diameter.id}">${diameter.value}</option>
                    </c:forEach>
                </select>
                Сезонность
                <select name="season-select" id="season-select" size="1">
                    <option value="none" selected="true">---</option>
                    <c:forEach var="season" items="${seasonList}">
                        <option value="${season.id}">${season.value}</option>
                    </c:forEach>
                </select>
                Производитель
                <select name="make-select" id="make-select" size="1">
                    <option value="none" selected="true">---</option>
                    <c:forEach var="make" items="${makeList}">
                        <option value="${make.id}">${make.title}</option>
                    </c:forEach>
                </select>

                <hr/>
                <input id="add-button" type="submit" value="Search" name="add" OnClick="return validateFindTires()"/>
            </form>
        </center>
    </body>
</html>
