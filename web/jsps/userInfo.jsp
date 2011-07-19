<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src = "scripts/ValidateUserInfo.js" type = "text/javascript"> </script>
        <link rel="stylesheet" href="css/bo.main.css" type="text/css">
        <title>User Information</title>
    </head>
    <body>
        <center>
            <br><a href="passwordChanger.jsp">Change password</a>
            <form action="Controller?command=CHANGE_USER_INFO" method=POST>
                <table id = "t_registration">
                    <tr>
                        <td>???? ???????:</td>
                        <td><input type=text name="lastName" id="lastName" style = "width:100px" value="${profile.lastName}"/></td>
                    </tr>
                    <tr>
                        <td>???? ???:</td>
                        <td><input type="text" name="firstName" id="firstName" style = "width:100px" value="${profile.firstName}"/></td>
                    </tr>
                    <tr>
                        <td>??? nickname:</td>
                        <td><input type="text" name="nickName" id="nickName" style = "width:100px" value="${profile.nickName}"/></td>
                    </tr>
                    <tr>
                        <td>User Data:</td>
                        <td><input type="text" name="user_data" id="user_data" style = "width:100px"  value="${profile.userData}" /></td>
                    </tr>
                    <tr>
                        <td>User Country:</td>
                        <td><input type="text" name="user_country" id="user_country" style = "width:100px"  value="${profile.userCountry}"/></td>
                    </tr>
                    <tr>
                        <td>User Address:</td>
                        <td><input type="text" name="user_address" id="user_address" style = "width:100px"  value="${profile.userAddress}"/></td>
                    </tr>
                    <tr>
                        <td>User Phone:</td>
                        <td><input type="text" name="user_phone" id="user_phone" style = "width:100px"  value="${profile.userPhone}"/></td>
                    </tr>
                    <tr>
                        <td>??? ??????? ????:</td>
                        <td>
                            <select  name="timezone" id="timezone">
                                <c:forEach var="timezoneElement" items="${timezones}">
                                    <c:if test="${empty profile.timezone.id}">
                                        <option width="100%" value="${timezoneElement.id}"><c:out value="${timezoneElement.timezone} - ${timezoneElement.location}"/></option>
                                    </c:if>
                                    <c:if test="${not empty profile.timezone.id}">
                                        <c:if test="${timezoneElement.id==profile.timezone.id}">
                                            <option width="100%" value="${timezoneElement.id}" selected="true"> ${timezoneElement.timezone} - ${timezoneElement.location} </option>
                                        </c:if>
                                        <c:if test="${timezoneElement.id!=profile.timezone.id}">
                                            <option width="100%" value="${timezoneElement.id}"> ${timezoneElement.timezone} - ${timezoneElement.location} </option>
                                        </c:if>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td colspan = "2">
                            <input type="submit" value="Submit" OnClick = "return validateUserInfoForm()"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan = "2">
                        <input type="reset" value="Cancel"/></td>
                    </tr>
                </table>
            </form>
            <c:if test="${status==1}">
                <form action = "./">
                    <table id = "t_back" cellpadding = "5" >
                        <td colspan = "2"><input type="submit" value="Back"/></td>
                    </table>
                </form>
            </c:if>
            <c:if test="${status!=1}">
                <form action = "cp.jsp">
                    <table id = "t_back" cellpadding = "5" >
                        <td colspan = "2"><input type="submit" value="Back"/></td>
                    </table>
                </form>
            </c:if>
        </center>
    </body>
</html>
