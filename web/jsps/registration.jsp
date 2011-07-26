<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <link rel="stylesheet" href="css/bo.main.css" type="text/css">
        <script src = "scripts/validateReg.js" type = "text/javascript"> </script>
        <script src = "scripts/isMailExist.js" type = "text/javascript"> </script>
    </head>
    <body>
        <form action="Controller.do?command=registrator" method=POST>
            <center>
                Enter information about You, please
            </center>
            <table id = "t_registration" cellpadding = "5" >
                <tr>
                    <td>Ваш E-mail:</td>
                    <td><input type="text" name="e-mail" id="e-mail" style = "width:100px" onchange="isExists()"/></td>
                    <td> <div id="remind"></div> </td>
                </tr>
                <tr>
                    <td colspan = "2"><input type="submit" value="Submit" OnClick = "return validateFormRegistration()"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>