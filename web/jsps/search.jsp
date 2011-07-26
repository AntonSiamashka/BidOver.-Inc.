<%@page contentType="text/html" pageEncoding="UTF-8"
        %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bo.main.css" type="text/css">
        <script type="text/JavaScript" src="scripts/auto/SearchAuto.js" > </script>
        <title>Search</title>
        <fmt:setLocale value="${sessionScope.LNG}" />
        <fmt:setBundle basename="Motors"/>
    </head>
    <body>
        <!-- HEADER -->
        <table width="100%">
            <tr>
                <td width="38%" valign="top">
                    <span class="logo">bid<span style="color: #600">Over</span></span>
                    <p class="logounderline">on-line auctions</p>
                    <c:if test="${status==2}"><p class="small"><fmt:message key="lbl.goto" /> <a href="./cp.jsp"><fmt:message key="btn.ctrlpanel" /></a> <fmt:message key="lbl.or" /> <a type="submit" href="Controller.do?command=LOG_OUT"><fmt:message key="btn.logout" /></a></p></c:if>
                    <c:if test="${status!=2}"><p class="small"><fmt:message key="lbl.welcome" /> <a href="./login.jsp"><fmt:message key="btn.signin" /></a> <fmt:message key="lbl.or" /> <a href="registration.jsp"><fmt:message key="btn.register" /></a></p></c:if>
                </td>
                <td valign="top">
                    <table style="border-top: 1px solid #444" align="right"><tr><td><a href="run.jsp?alt=4" class="small"><fmt:message key="btn.contact" /></a> | <a href="run.jsp?alt=9" class="small"><fmt:message key="btn.help" /></a></td></tr></table>
                </td>
            </tr>
        </table>
        <p>&nbsp;</p>
        <!-- END OF HEADER -->
        <!-- TITLE -->
        <table width="100%" align="center" cellpadding="3" cellspacing="2" >
            <tr><td>
                    <table>
                        <tr>
                            <td valign="top"><p class="title"><fmt:message key="lbl.searchlot" /></p></td>
                            <td>&nbsp;&nbsp;&nbsp;</td>
                            <td valign="top">
                                <table style="border-top: 1px solid #444">
                                    <tr><td><img src="css/bo.home.024x024.png" border="0" alt=""></td><td valign="middle"><a href="./" class="small"><fmt:message key="btn.home" /></a></td></tr>
                                </table>
                            </td>
                        </tr>
                    </table>
            </td></tr>
        </table>
        <p>&nbsp;</p>
        <!-- END OF TITLE -->
        <form action="Controller.do?command=SEARCH" method="POST">
            <div id="search_bar">
                <table id="auto-table" cellpadding="5">
                    <tr>
                        <td valign="top" align="right"><h4>Basic Search</h4></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td valign="top" align="right"><h4><fmt:message key="lbl.make" />:</h4></td>
                        <td>
                            <select name="make" id="make" size="10" onchange="getModels()">
                                <option value="">All</option>
                                <c:forEach var="make" items="${makes}">
                                    <option value="${make}"> ${make} </option>
                                </c:forEach>
                            </select>
                        </td>

                        <td valign="top" align="right"><h4><fmt:message key="lbl.model" />:</h4></td>
                        <td><div id="select_model"></div></td>
                        <td valign="top" align="right"><h4><fmt:message key="lbl.modification" />:</h4></td>
                        <td><div id="select_modification"></div></td>
                    </tr>
                    <tr>
                        <td valign="top" align="right"><h4><fmt:message key="lbl.years" />:</h4></td>
                        <td>
                            <select name="years_beg" id="years_beg">
                                <option value="">All</option>
                                <c:forEach items="${years}" var="year">
                                    <option value="${year}">${year}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select name="years_end" id="years_end">
                                <option value="">All</option>
                                <c:forEach items="${years}" var="year">
                                    <option value="${year}">${year}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>
                            <input id="advanced-search-button" type="button" onclick="showAdvancedSearch()" value="Advanced Search"/>
                            <input id="hide-advanced-search-button" type="button" onclick="hideAdvancedSearch()" value="Hide Advanced Search" style="visibility:hidden;display:none;"/>
                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </table>
                <table id="advanced-search" cellpadding="5" style="visibility:hidden;display:none;">
                    <tr>
                        <td valign="top" align="right"><h4>Advanced Search</h4></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td align="right"><h4><fmt:message key="lbl.odometer" />:</h4></td>
                        <td>
                            <select name="odometer" id="odometer">
                                <option id="-1" value="-1">All</option>
                                <option id="10000" value="10000"> 10.000</option>
                                <option id="20000" value="20000">20.000</option>
                                <option id="30000" value="30000">30.000</option>
                                <option id="40000" value="40000">40.000</option>
                                <option id="50000" value="50000">50.000</option>
                                <option id="60000" value="60000">60.000</option>
                                <option id="70000" value="70000">70.000</option>
                                <option id="80000" value="80000">80.000</option>
                                <option id="90000" value="90000">90.000</option>
                                <option id="100000" value="100000">100.000</option>
                            </select>
                        </td>
                        <td></td><td></td>
                    </tr>
                    <tr>
                        <td align="right"><h4><fmt:message key="lbl.doors" />:</h4></td>
                        <td>
                            <div id="box">
                                <select name="doors" id="doors">
                                    <option value="-1">All</option>
                                    <c:forEach var="door" items="${doors}">
                                        <option value="${door.id}"> ${door.title} </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </td>
                        <td></td><td></td>
                    </tr>
                    <tr>
                        <td align="right"><h4><fmt:message key="lbl.top_type" />:</h4></td>
                        <td>
                            <select name="top_type" id="top_type" onchange="">
                                <option value="-1">All</option>
                                <c:forEach var="top_type" items="${top_types}">
                                    <option value="${top_type.id}"> ${top_type.title} </option>
                                </c:forEach>
                            </select>
                        </td>
                        <td></td><td></td>
                    </tr>
                    <tr style="visibility: hidden; display: none;">
                        <td align="right"><h4><fmt:message key="lbl.body_style" />:</h4></td>
                        <td>
                            <select name="body_style" id="body_style" onchange="">
                                <option value="-1"> All </option>
                                <c:forEach var="body_styles" items="${body_style}">
                                    <option value="${body_styles.id}"> ${body_styles.title} </option>
                                </c:forEach>
                            </select>
                        </td>
                        <td></td><td></td>
                    </tr>
                    <tr>
                        <td align="right"><h4><fmt:message key="lbl.transmission" />:</h4></td>
                        <td>
                            <select name="transmission" id="transmission" onchange="">
                                <option value="-1">All</option>
                                <c:forEach var="transmissions" items="${transmission}">
                                    <option value="${transmissions.id}"> ${transmissions.title} </option>
                                </c:forEach>
                            </select>
                        </td>
                        <td></td><td></td>
                    </tr>
                    <tr>
                        <td align="right"><h4><fmt:message key="lbl.drive_train" />:</h4></td>
                        <td>
                            <select name="drive_train" id="drive_train" onchange="">
                                <option value="-1">All</option>
                                <c:forEach var="drive_trains" items="${drive_train}">
                                    <option value="${drive_trains.id}"> ${drive_trains.title} </option>
                                </c:forEach>
                            </select>
                        </td>
                        <td></td><td></td>
                    </tr>
                    <tr>
                        <td align="right"><h4><fmt:message key="lbl.engine" />:</h4></td>
                        <td>
                            <select name="engine" id="engine" onchange="">
                                <option value="-1">All</option>
                                <c:forEach var="engines" items="${engine}">
                                    <option value="${engines.id}"> ${engines.title} </option>
                                </c:forEach>
                            </select>
                        </td>
                        <td></td><td></td>
                    </tr>
                    <tr>
                        <td align="right"><h4><fmt:message key="lbl.fuel" />:</h4></td>
                        <td>
                            <select name="fuel" id="fuel" onchange="">
                                <option value="-1">All</option>
                                <c:forEach var="fuels" items="${fuel}">
                                    <option value="${fuels.id}"> ${fuels.title} </option>
                                </c:forEach>
                            </select>
                        </td>
                        <td></td><td></td>
                    </tr>
                    <tr>
                        <td align="right"><h4><fmt:message key="lbl.exterior_color" />:</h4></td>
                        <td>
                            <select name="exterior_color" id="exterior_color" onchange="">
                                <option value="-1">All</option>
                                <c:forEach var="exterior_colors" items="${exterior_color}">
                                    <option value="${exterior_colors.id}"> ${exterior_colors.title} </option>
                                </c:forEach>
                            </select>
                        </td>
                        <td></td><td></td>
                    </tr>
                    <tr>
                        <td align="right"><h4><fmt:message key="lbl.interior_color" />:</h4></td>
                        <td>
                            <select name="interior_color" id="interior_color" onchange="">
                                <option value="-1">All</option>
                                <c:forEach var="interior_colors" items="${interior_color}">
                                    <option value="${interior_colors.id}"> ${interior_colors.title} </option>
                                </c:forEach>
                            </select>
                        </td>
                        <td></td><td></td>
                    </tr>
                    <tr>
                        <td align="right"><h4><fmt:message key="lbl.interior_type" />:</h4></td>
                        <td>
                            <select id="int_type" name="int_type">
                                <option value="-1">All</option>
                                <option value="1">Cloth</option>
                                <option value="2">Leather</option>
                                <option value="3">Vinyl</option>
                            </select>
                        </td>
                        <td></td><td></td>
                    </tr>
                    <tr style="visibility: hidden; display: none;">
                        <td align="right"><h4><fmt:message key="lbl.wheels" />:</h4></td>
                        <td>
                            <select name="wheels" id="wheels" onchange="">
                                <option value="-1">All</option>
                                <c:forEach var="wheel" items="${wheels}">
                                    <option value="${wheel.id}"> R${wheel.title} </option>
                                </c:forEach>
                            </select>
                        </td>
                        <td></td><td></td>
                    </tr>
                    <tr>
                        <td align="right"><h4><fmt:message key="lbl.country.assembly" />:</h4></td>
                        <td>
                            <select name="country_assembly" id="country_assembly" onchange="">
                                <option value="-1">All</option>
                                <c:forEach items="${countryAssemblys}" var="countryAssembly">
                                    <option value="${countryAssembly.id}"> ${countryAssembly.title} </option>
                                </c:forEach>
                            </select>
                        </td>
                        <td></td><td></td>
                    </tr>
                    <tr>
                        <td align="right"><h4><fmt:message key="lbl.salvage" />:</h4></td>
                        <td>
                            <input name="salvage" id="salvage" type="checkbox"/>
                        </td>
                        <td></td><td></td>
                    </tr>
                </table>
                <input type="submit" value="Search"/>
            </div>
        </form>
        <%@include file="footer.jspf" %>
    </body>
</html>
