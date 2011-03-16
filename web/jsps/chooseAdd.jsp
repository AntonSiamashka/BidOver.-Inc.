<%-- 
    Document   : chooseAdd
    Created on : 24.08.2010, 15:21:06
    Author     : Jedai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <center>
            <h2>What do You want to add?</h2><hr/>
           <p> <a href="Controller?command=ADD_MAKE_TO_ADD">add autos</a></p>
           <a href="DetailController?command=ADD_DETAIL">add details</a>
           <p> <a href="TiresController?command=ADD_TIRES">add tires</a></p>
           <p> <a href="TiresController?command=ADD_WHEELS">add wheels</a></p>
        </center>
    </body>
</html>
