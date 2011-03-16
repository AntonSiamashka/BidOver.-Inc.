
function validateFindTires(){
   
    var width = document.getElementById("width-select");
    var contour = document.getElementById("contour-select");
    var diameter = document.getElementById("diameter-select");
    var season = document.getElementById("season-select");
    var make = document.getElementById("make-select");
    var message = "";
    if(width.value=='none' && diameter.value=='none' && contour.value=='none' && season.value=='none' && make.value=='none')
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