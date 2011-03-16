<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<html>
    <head>
        <link rel = "stylesheet" href= "styles/login.css" type= "text/css"/>
        <meta http-equiv="content-type" content="text/html; charset=UTF8" />
    </head>
    <body>
        <center>
            <%
        String nickName = request.getParameter("nickName");
        String password = request.getParameter("password");
            %>
            Your login or password is wrong. Please, try to enter them again...
        </center>
        <form action = "./">
            <table id = "t_back" cellpadding = "5">
                <td colspan = "2"><input type="submit" value="Back to Login"/></td>
            </table>
        </form>
    </body>
</html>