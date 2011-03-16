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


function getSubCategory(){
    var category = document.getElementById("category-select");
    var categoryID = "";
    if(category!=null){
        categoryID = category.value;
    }
    createRequest();
    var url = "DetailController?command=SHOW_SUBCATEGORY";
    request.open("POST", url, true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = updateSubCategoryList;
    request.send("categoryID="+categoryID);
}

function updateSubCategoryList(){

    if(request.readyState == 4){
        if(request.status == 200){

            var subCategoryXML = request.responseXML;
            var subCategory = subCategoryXML.getElementsByTagName("sub-category-list")[0];
            var items = subCategory.getElementsByTagName("sub-category");

            var subcategoryOption = document.getElementById("sub-category-select");
            var removeItems=subcategoryOption.childNodes;
            for (var J = removeItems.length ; 0 < J ; J--) {
                subcategoryOption.removeChild(removeItems[J-1]);
            }
            for (var I = 0 ; I < items.length ; I++) {
                var item = items[I];
                var id = item.getElementsByTagName("id")[0].firstChild.nodeValue;
                var name = item.getElementsByTagName("name")[0].firstChild.nodeValue;
                var option = document.createElement("option");
                option.setAttribute("value",id);
                option.appendChild(document.createTextNode(name));
                subcategoryOption.appendChild(option);
            }
            cleanItemOption();
            hideElement("item-div");
            showElement("sub-category-div");
        }
    }
}


function getItem(){

    var subcategory = document.getElementById("sub-category-select");
    var subcategoryID = "";
    if(subcategory!=null){
        subcategoryID = subcategory.value;
    }
    createRequest();
    var url = "DetailController?command=SHOW_ITEM";
    request.open("POST", url, true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = updateItemList;
    request.send("subcategoryID="+subcategoryID);
}

function updateItemList(){

    if(request.readyState == 4){
        if(request.status == 200){
            var subCategoryXML = request.responseXML;
            var subCategory = subCategoryXML.getElementsByTagName("item-list")[0];
            var items = subCategory.getElementsByTagName("item");
            var itemOption = document.getElementById("item-select");
            cleanItemOption();
            if(items.length!=0){
                for (var I = 0 ; I < items.length ; I++) {
                    var item = items[I];
                    var id = item.getElementsByTagName("id")[0].firstChild.nodeValue;
                    var name = item.getElementsByTagName("name")[0].firstChild.nodeValue;
                    var option = document.createElement("option");
                    option.setAttribute("value",id);
                    option.appendChild(document.createTextNode(name));
                    itemOption.appendChild(option);
                }
                showElement("item-div");
            }else{
                hideElement("item-div");
            }
        }
    }
}

function cleanItemOption(){
    var modelOptionDiv = document.getElementById("item-select");
    var removeItems=modelOptionDiv.childNodes;
    for (var J = removeItems.length ; 0 < J ; J--) {
        modelOptionDiv.removeChild(removeItems[J-1]);
    }
}


function getModel(){
    var make = document.getElementById("make-select");
    var makeID = "";
    if(make!=null){
        makeID = make.value;
    }
    createRequest();
    var url = "DetailController?command=SHOW_MODEL";
    request.open("POST", url, true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = updateModelList;
    request.send("makeID="+makeID);
}

function updateModelList(){

    if(request.readyState == 4){
        if(request.status == 200){
            var subCategoryXML = request.responseXML;
            var subCategory = subCategoryXML.getElementsByTagName("model-list")[0];
            var items = subCategory.getElementsByTagName("model");
            var modelOption = document.getElementById("model-select");
            cleanModelOption();
            if(items.length!=0){
                for (var I = 0 ; I < items.length ; I++) {
                    var item = items[I];
                    var id = item.getElementsByTagName("id")[0].firstChild.nodeValue;
                    var name = item.getElementsByTagName("name")[0].firstChild.nodeValue;
                    var option = document.createElement("option");
                    option.setAttribute("value",id);
                    option.appendChild(document.createTextNode(name));
                    modelOption.appendChild(option);
                }
                cleanYearOption();
                hideElement("year-div");
                showElement("model-div");
            }
        }
    }
}

function cleanModelOption(){
    var modelOptionDiv = document.getElementById("model-select");
    var removeItems=modelOptionDiv.childNodes;
    for (var J = removeItems.length ; 0 < J ; J--) {
        modelOptionDiv.removeChild(removeItems[J-1]);
    }
}

function getYear(){
    var model = document.getElementById("model-select");
    var modelID = "";
    if(model!=null){
        modelID = model.value;
    }
    createRequest();
    var url = "DetailController?command=SHOW_YEAR";
    request.open("POST", url, true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = updateYearList;
    request.send("modelID="+modelID);
}

function updateYearList(){

    if(request.readyState == 4){
        if(request.status == 200){
            var subCategoryXML = request.responseXML;
            var subCategory = subCategoryXML.getElementsByTagName("year-list")[0];
            cleanYearOption();
            var select = document.getElementById("year-begin-select");
            var yearBegin = subCategory.getElementsByTagName("year-begin")[0].firstChild.nodeValue;
            var yearEnd = subCategory.getElementsByTagName("year-end")[0].firstChild.nodeValue;
            var option;
            for (var I = yearBegin ; I <= yearEnd ; I++) {
                option = document.createElement("option");
                option.setAttribute("value",I);
                option.appendChild(document.createTextNode(I));
                select.appendChild(option);
            }
            select = document.getElementById("year-end-select");
            for (var J = yearEnd ; J >= yearBegin ; J--) {
                option = document.createElement("option");
                option.setAttribute("value",J);
                option.appendChild(document.createTextNode(J));
                select.appendChild(option);
            }
            showElement("year-div");
        }
    }
}

function cleanYearOption(){
    var modelOptionDiv = document.getElementById("year-begin-select");
    var removeItems=modelOptionDiv.childNodes;
    for (var J = removeItems.length ; 0 < J ; J--) {
        modelOptionDiv.removeChild(removeItems[J-1]);
    }
    modelOptionDiv = document.getElementById("year-end-select");
    removeItems=modelOptionDiv.childNodes;
    for (var I = removeItems.length ; 0 < I ; I--) {
        modelOptionDiv.removeChild(removeItems[I-1]);
    }
}

function hideElement(id){
    var div = document.getElementById(id);
    div.style.visibility = "hidden";
    div.style.display = "none";
}

function showElement(id){
    var div = document.getElementById(id);
    div.style.visibility = "visible";
    div.style.display = "block";
}