
function getUserEmail() {
	return document.getElementById("email").value;
}

function getUserPass() {
	return document.getElementById("pass").value;
}

function getUserTZOffset() {
	return new Date().getTimezoneOffset();
}

// -------------------------------
// --------- Authorization (Login)
// -------------------------------
function getUserLoginInfo() {
	document.getElementById("progress_indicator").style.visibility = 'visible';
	createRequest();
	var url = "ck.jsp";
	request.open("POST", url, true);
	request.onreadystatechange = updateLoginPage;
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.send( "alt=1&email="+encodeURIComponent(getUserEmail())+"&pass="+encodeURIComponent(getUserPass())+"&tzoffset="+encodeURIComponent(getUserTZOffset()) );
}

function updateLoginPage() {
	if (request.readyState == 4) {
		var jsonData = eval("("+request.responseText+")");

		document.getElementById("progress_indicator").style.visibility = 'hidden';
		if (jsonData.status == 1) window.location = "cp.jsp";
		else replaceText(document.getElementById("msg"), jsonData.message);
	}
}

// --------------------------------
// ---------- Registration (Signup)
// --------------------------------
function getUserRegInfo() {
	document.getElementById("progress_indicator").style.visibility = 'visible';
	createRequest();
	var url = "ck.jsp";
	request.open("POST", url, true);
	request.onreadystatechange = updateRegPage;
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.send( "alt=2&email="+encodeURIComponent(getUserEmail()) );
}

function updateRegPage() {
	if (request.readyState == 4) {
//	alert(request.responseText);
		var jsonData = eval("("+request.responseText+")");

		document.getElementById("progress_indicator").style.visibility = 'hidden';
		if (jsonData.status == 1) window.location = "run.jsp?alt=3&rg=1";
		else replaceText(document.getElementById("msg"), jsonData.message);
	}
}

// --------------------------------
// ---------- Request Password
// --------------------------------
function getUserPassInfo() {
	document.getElementById("progress_indicator").style.visibility = 'visible';
	createRequest();
	var url = "ck.jsp";
	request.open("POST", url, true);
	request.onreadystatechange = updateReqPassPage;
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.send( "alt=3&email="+encodeURIComponent(getUserEmail()) );
}

function updateReqPassPage() {
	if (request.readyState == 4) {
		var jsonData = eval("("+request.responseText+")");

		document.getElementById("progress_indicator").style.visibility = 'hidden';
		if (jsonData.status == 1) window.location = "run.jsp?alt=3";
		else replaceText(document.getElementById("msg"), jsonData.message);
	}

}