
function validateAddTires(){
    
    var width = document.getElementById("width-select");
    var contour = document.getElementById("contour-select");
    var diameter = document.getElementById("diameter-select");
    var season = document.getElementById("season-select");
    var make = document.getElementById("make-select");
    var message = "";
    if(width.value=='')
    {
        message = message + "(!) width field is empty! \n ";
    }
    if(contour.value=='')
    {
        message = message + "(!) contour field is empty! \n ";
    }
    if(diameter.value=='')
    {
        message = message + "(!) diameter field is empty! \n ";
    }
    if(season.value=='')
    {
        message = message + "(!) season field is empty! \n ";
    }
    if(make.value=='')
    {
        message = message + "(!) make field is empty! \n ";
    }
    if(message!="")
    {
        alert(message);
        return false;
    }
    return true;

    function hidePreviewButton(){
        var previewButton = document.getElementById("preview-button");
        previewButton.style.visibility = "hidden";
        previewButton.style.display = "none";
    }

    function showPreviewButton(){
        var previewButton;
        var yearSelect = document.getElementById("year-end-select");
        var itemSelect = document.getElementById("item-select");
        var itemOption = document.getElementById("item-option");
        var subcategorySelect = document.getElementById("sub-category-select");
        var year;
        var item;
        var subcategory;
        if(yearSelect!=null){
            year = yearSelect.value;
        }
        if(itemSelect!=null){
            item = itemSelect.value;
        }
        if(subcategorySelect!=null){
            subcategory = subcategorySelect.value;
        }
        if(itemOption.firstChild==null){
            if(subcategory!="" && subcategory!=null && year!=null){
                previewButton = document.getElementById("preview-button");
                previewButton.style.visibility = "visible";
                previewButton.style.display = "block";
            }
        }else{
            if(item!="" && item!=null && year!=null){
                previewButton = document.getElementById("preview-button");
                previewButton.style.visibility = "visible";
                previewButton.style.display = "block";
            }
        }
    }
   
}