package com.bidover.util;

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 

public class SetCookies extends HttpServlet {

	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpServletResponse response;
	
	public SetCookies (HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	public void setCookie (String name, String value, int age) throws ServletException, IOException { 
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(age);
		response.addCookie(cookie);
	}
	
	public String getCookie (String name) throws ServletException, IOException {
		String value = null;
		Cookie cookies [] = request.getCookies();
		Cookie myCookie = null;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies [i].getName().equals(name)) {
					myCookie = cookies[i];
					value = myCookie.getValue();
					break;
				}
			}
		}
		return value;
	}
	
}
