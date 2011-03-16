var request = null;
function createRequest() {
	try {
		request = new XMLHttpRequest();
	} 
	catch (trymicrosoft) {
		try {
			request = ActiveXObject("Msxml2.XMLHTTP");
		} 
		catch (othermicrosoft) {
			try {
				request = new ActiveXObject("Microsoft.XMLHTTP");
			} 
			catch (failed) {
				request = null;
			}
		}
	}


	if (request == null) alert ("Error creating request object!");
}

function replaceText(el, text) {
  if (el != null) el.innerHTML = text;
}