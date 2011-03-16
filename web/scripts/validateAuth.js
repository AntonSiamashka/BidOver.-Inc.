function validateFormAuth()
{
	var email = document.getElementById("e-mail");
	var emailStr = email.value;
	var pass = document.getElementById("password");
	var passStr = pass.value;
	var message = "";
	if(emailStr.length == 0 ){
		message = message + "e-mail   ";
	}
	if(passStr.length == 0 ){
		message = message + "password   ";
	}

	if(message.length != 0){
		message = "HATENESS!!!\n (!) Fields   " + message + "are EMPTY!";
		alert(message);
		return false;
	}
	else if(message.length == 0){
		return true;
	}
    return false;
   


}