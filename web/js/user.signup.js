
function getUserEmail() {
	return document.getElementById("email").value;
}


function getUserRegInfo() {
	document.getElementById("progress_indicator").style.visibility = 'visible';
	createRequest();
	var url = "Controller";
	request.open("POST", url, true);
	request.onreadystatechange = updateRegPage;
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.send( "command=SIGNUP&email="+encodeURIComponent(getUserEmail()) );
}

function updateRegPage() {
	if (request.readyState == 4) {
            var jsonData = eval("("+request.responseText+")");
            document.getElementById("progress_indicator").style.visibility = 'hidden';
            if (jsonData.status == 1){
                window.location = "cp.jsp";
            } else if (jsonData.status == 0) {
                window.location = "Controller?command=REGISTRATION";
            } else {
                replaceText(document.getElementById("msg"), jsonData.message);
            }
	}
}