<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bo.main.css" type="text/css">
        <link rel="stylesheet" href="styles/lightbox.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="css/jquery.fancybox.css" type="text/css" media="screen">
        <script type="text/JavaScript" src="scripts/viewInPopUp.js"></script>
        <script type="text/javascript" src="scripts/lightbox/prototype.js"></script>
        <script type="text/javascript" src="scripts/lightbox/scriptaculous.js?load=effects,builder"></script>
        <script type="text/javascript" src="scripts/lightbox/lightbox.js"></script>
        <script type="text/javascript" src="js/lot.bidding.js"></script>
        <script type="text/javascript" src="js/jquery/jquery.js"></script>
        <script type="text/javascript" src="js/jquery/jquery.fancybox.js"></script>
        <script type="text/javascript">
                $(document).ready(function(){
                        $("#cond a").fancybox({
                            frameWidth: 600,
                            frameHeight: 400,
                            overlayShow: false,
                            hideOnContentClick: false
                        });
                        $("#bidhist a").fancybox({
                            frameWidth: 600,
                            frameHeight: 400,
                            overlayShow: false,
                            hideOnContentClick: false
                        });
                });
        </script>
        <title>Auto</title>
    </head>
    <body>
        <fmt:setLocale value="${sessionScope.LNG}" />
        <fmt:setBundle basename="Messages"/>
        <%@include file="header.jspf" %>
        <%@include file="lotInfo.jspf" %>
        <%@include file="footer.jspf" %>
    </body>
</html>
