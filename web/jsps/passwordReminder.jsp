<%-- 
    Document   : passwordReminder
    Created on : 22.04.2010, 23:36:14
    Author     : Jedai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <link rel = "stylesheet" href= "styles/registration.css" type= "text/css"/>
        <script src = "scripts/validateReg.js" type = "text/javascript"> </script>
    </head>
    <body>
        <center>
            <form action="Controller?command=remind_password" method=POST>
                <center>
                    Enter your e-mail
                </center>
                <table id = "t_registration" cellpadding = "5" >
                    <tr>
                        <td>Ваш E-mail:</td>
                        <td><input type="text" name="e-mail" id="e-mail" style = "width:100px"/></td>
                    </tr>
                    <tr>
                        <td colspan = "2"><input type="submit" value="Submit" OnClick = "return validateFormRegistration()"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan = "2"><input type="reset" value="Cancel"/></td>
                    </tr>
                </table>
            </form>
            <form action = "./">
                <table id = "t_back" cellpadding = "5" >
                    <td colspan = "2"><input type="submit" value="Back to Login"/></td>
                </table>
            </form>
        </center>
    </body>
</html>
