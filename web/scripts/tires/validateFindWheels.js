
function validateFindWheels(){
    
    var width = document.getElementById("width-select");
    var diameter = document.getElementById("diameter-select");
    var bcd = document.getElementById("bcd-select");
    var ed = document.getElementById("ed-select");
    var message = "";
    if(width.value=='none' && diameter.value=='none' && bcd.value=='none' && ed.value=='none')
    {
        message = message + "(!) You must check at least one parameter for searching! \n ";
    }
    if(message!="")
    {
        alert(message);
        return false;
    }
    return true;
   
}