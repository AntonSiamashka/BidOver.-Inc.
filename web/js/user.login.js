
function getUserEmail() {
	return document.getElementById("email").value;
}

function getUserPass() {
	return document.getElementById("pass").value;
}

function getUserLoginInfo() {
	document.getElementById("progress_indicator").style.visibility = 'visible';
	createRequest();
	var url = "Controller";
	request.open("POST", url, true);
	request.onreadystatechange = updateLoginPage;
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.send( "command=LOGIN&email="+encodeURIComponent(getUserEmail())+"&pass="+encodeURIComponent(getUserPass()) );
}

function updateLoginPage() {
	if (request.readyState == 4) {
//      alert(request.responseText);
        	var jsonData = eval("("+request.responseText+")");

		document.getElementById("progress_indicator").style.visibility = 'hidden';
		if (jsonData.status == 1) window.location = "cp.jsp";
		else replaceText(document.getElementById("msg"), jsonData.message);
	}
}