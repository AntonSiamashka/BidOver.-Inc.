
function setLang(lng) {
	createRequest();
	var url = "Controller.do?command=CHG_LNG";
	request.open("POST", url, true);
	request.onreadystatechange = updateLang;
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.send( "lng="+encodeURI(lng) );
}

function updateLang() {
	if (request.readyState == 4) {
		alert(request.responseText);
		window.location = location.href;
	}
}