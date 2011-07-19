<%-- 
    Document   : searchDetail
    Created on : 27.06.2010, 14:46:50
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
        <script src="scripts/detail/Search.js" type="text/javascript" ></script>
    </head>
    <body>
        <h1>Search!</h1>
        <form action="DetailController?command=SEARCHING_DETAIL" method="post">
                  <table cellpadding="30" cellspacing="20">
                    <tr>
                        <td valign="top">
                            <table>
                                <tr>
                                    <td>
                                        Make
                                    </td>
                                    <td>
                                        <select id="make-select" name="make-select" onchange="getModel()" size="10">
                                            <c:forEach var="make" items="${makeList}">
                                                <option value="${make.id}">${make.title}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        Model
                                    </td>
                                    <td>
                                        <div id="model-div" style="visibility:hidden;display:none;">
                                            <select name="model-select" id="model-select" onchange="getYear()" size="10"></select>
                                        </div>
                                    </td>
                                    <td>
                                        Year
                                    </td>
                                    <td>
                                        <div id="year-div" style="visibility:hidden;display:none;">
                                            <select name="year-begin-select" id="year-begin-select"></select>
                                            <select name="year-end-select" id="year-end-select"></select>
                                        </div>
                                    </td>
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
                          </td>
                      </tr>
                      <tr>
                        <td valign ="top">
                            <table id="advanced-search" cellpadding="5" style="visibility:hidden;display:none;">
                                <tr>
                                    <td valign="top" align="right"><h4>Advanced Search</h4></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>
                                        Category
                                    </td>
                                    <td>
                                        <select name="category-select" id="category-select" onchange="getSubCategory()" size="10">
                                            <c:forEach var="category" items="${categoryList}">
                                                <option value="${category.id}">${category.name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        Subcategory
                                    </td>
                                    <td>
                                        <div id="sub-category-div" style="visibility:hidden;display:none;">
                                            <select name="sub-category-select" id="sub-category-select" onchange="getItem()" size="10"></select>
                                        </div>
                                    </td>
                                    <td>
                                        Item
                                    </td>
                                    <td>
                                        <div id="item-div" style="visibility:hidden;display:none;">
                                            <select name="item-select" id="item-select" size="10"></select>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
                <input id="search-button" type="submit" value="Search" name="search" />
        </form>
    </body>
</html>
