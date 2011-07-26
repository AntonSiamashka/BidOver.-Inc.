
function setLang(lng) {
	createRequest();
	var url = "Controller.do";
	request.open("POST", url, true);
	request.onreadystatechange = updateLang;
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.send( "command=CHG_LNG&lng="+encodeURI(lng) );
}

function updateLang() {
	if (request.readyState == 4) {
//		alert(request.responseText);
		window.location = location.href;
	}
}