<%-- 
    Document   : passwordChanger
    Created on : 23.04.2010, 0:48:18
    Author     : Jedai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%
        if (request.getSession() != null && request.getSession().getAttribute("status") != null) {
            int status = (Integer) request.getSession().getAttribute("status");
            if (status != 2) {
                response.sendRedirect("./");
                return;
            }
        } else {
            response.sendRedirect("./");
            return;
        }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" href= "styles/registration.css" type= "text/css"/>
        <title>Chang password</title>
    </head>
    <body>
        <center>
            <form action="Controller.do?command=CHANGE_PASSWORD" method=POST>
                <table id = "t_registration">
                    <tr>
                        <td>Ваш password:</td>
                        <td><input type="password" name="password" id="password" style = "width:100px"/></td>
                    </tr>
                    <tr>
                        <td>Повторите password:</td>
                        <td><input type="password" name="repeat" id="repeat" style = "width:100px"/></td>
                    </tr>
                </table>
                <tr>
                    <td colspan = "2">
                        <input type="submit" value="Submit"/>
                    </td>
                </tr>
            </form>
            <form action = "profile.jsp">
                <table id = "t_back" cellpadding = "5" >
                    <td colspan = "2"><input type="submit" value="Back"/></td>
                </table>
            </form>
        </center>
    </body>
</html>
