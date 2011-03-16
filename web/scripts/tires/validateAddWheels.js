
function validateAddWheels(){
    
    var width = document.getElementById("width-select");
    var diameter = document.getElementById("diameter-select");
    var bcd = document.getElementById("bcd-select");
    var ed = document.getElementById("ed-select");
    var message = "";
    if(width.value=='')
    {
        message = message + "(!) width field is empty! \n ";
    }
    if(diameter.value=='')
    {
        message = message + "(!) diameter field is empty! \n ";
    }
    if(bcd.value=='')
    {
        message = message + "(!) BCD field is empty! \n ";
    }
    if(ed.value=='')
    {
        message = message + "(!) ED field is empty! \n ";
    }
    if(message!="")
    {
        alert(message);
        return false;
    }
    return true;
   
}