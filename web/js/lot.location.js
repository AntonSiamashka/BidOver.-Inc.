function getCountryCode() {
	return document.getElementById("country_code").value;
}
function getATDCode() {
	return document.getElementById("atd_code").value;
}
function processATD() {
	document.getElementById("progress_indicator_country").style.visibility = 'visible';
	createRequest();
	var url = "Controller.do";
	
	request.open("POST", url, true);
	request.onreadystatechange = updateATD;
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.send( "command=CHANGE_ATD&country_code="+encodeURI(getCountryCode()) );

}
function updateATD() {
	if (request.readyState == 4) {
//alert(request.responseText);
//		var jsonData = eval("("+request.responseText+")");
//		replaceText(document.getElementById("atd_list"), jsonData.atd_list);
                replaceText(document.getElementById("atd_list"), request.responseText);
		document.getElementById("progress_indicator_country").style.visibility = 'hidden';
		document.getElementById("atd_list").style.disabled = 'false';
	}
}

function processLocation() {
	document.getElementById("progress_indicator_atd").style.visibility = 'visible';
	createRequest();
	var url = "Controller.do";
	
	request.open("POST", url, true);
	request.onreadystatechange = updateLocation;
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.send( "command=CHANGE_LOCATION&country_code="+encodeURI(getCountryCode())+"&atd_code="+encodeURI(getATDCode()) );
}
function updateLocation() {
	if (request.readyState == 4) {
//alert(request.responseText);
//		var jsonData = eval("("+request.responseText+")");

//		replaceText(document.getElementById("location_list"), jsonData.location_list);
                replaceText(document.getElementById("location_list"), request.responseText);
//		replaceText(document.getElementById("msg_lot"), "");
		document.getElementById("progress_indicator_atd").style.visibility = 'hidden';
		document.getElementById("location_list").style.disabled = 'false';
	}
}