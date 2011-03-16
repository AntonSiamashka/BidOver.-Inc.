<%@page  contentType="text/html" pageEncoding="UTF-8"
        import = "bidoverdb.common.SessionSnoop"
        import = "bidoverdb.common.SetCookies"
%>
<%
	String lng = request.getParameter("lng");

	SessionSnoop ss = new SessionSnoop(request, response);
	ss.setLanguage(lng);
	
	final int SECONDS_PER_YEAR = 60*60*24*365;
	SetCookies cookie = new SetCookies (request, response);
	cookie.setCookie("lng", lng, SECONDS_PER_YEAR);
%>