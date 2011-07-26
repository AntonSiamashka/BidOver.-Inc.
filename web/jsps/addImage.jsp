<%--
    Document   : index
    Created on : 23.04.2010, 0:25:49
    Author     : Nomad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Upload</title>
    </head>
    <body>
        <%String id = request.getParameter("id");%>
        <form action="Controller.do?command=ADD_IMAGE&id=<%=id%>" method="post" enctype="multipart/form-data">
            <input name="data" type="file"><br>
            <input type="submit"><br>
        </form>
        <a href="Controller.do?command=SHOW_AUTO_BY_USER">Show My Autos</a>
    </body>
</html>