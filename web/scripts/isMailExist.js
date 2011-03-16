var request = null;

function createRequest(){

    try{
        request = new XMLHttpRequest();
    } catch(trymicrosoft){
        try{
            request = new ActiveXObject("Msxm2.XMLHTTP");
        } catch(othermicrosoft){
            try{
                request = new ActiveXObject("Microsoft.XMLHttp");
            } catch(failed){
                request = null;
            }
        }
    }
    if(request == null)
        alert("Error creating request object!");
}

function isExists(){
    var email = document.getElementById("e-mail");
    var emailStr = email.value;
    createRequest();
    var url = "Controller?command=IS_MAIL_EXISTS";
    request.open("POST", url, true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = updateRemind;
    var req = "e-mail=" + emailStr;
    request.send(req);

}

function updateRemind(){
    if(request.readyState == 4){
        if(request.status == 200){
            var respTxt = request.responseText;
            var remind = document.getElementById("remind");
            if(respTxt == "true"){
                remind.innerHTML = "in use!";
            } else remind.innerHTML = "";
        }
    }
}