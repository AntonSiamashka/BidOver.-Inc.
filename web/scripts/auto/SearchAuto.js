function getModels(){
    var make = document.getElementById("make");
    var makeStr = "";
    if(make!=null){
        makeStr = make.value;
    }
    createRequest();
    var url = "Controller.do?command=ADD_MODEL_TO_SEARCH";
    request.open("POST", url, true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = updateModelList;
    request.send("make="+makeStr);
}

function updateModelList(){

    if(request.readyState == 4){
        if(request.status == 200){
            var xml = request.responseXML;
            var divId="select_model";
            var tagName = "model";
            var onchangeMethod = "getModifications()";
            var select = createSelect(xml,tagName,onchangeMethod);
            select.setAttribute("size", 10);
            var div = document.getElementById(divId);
            cleanElement(divId);
            div.appendChild(select);
            getModifications();
        }
    }
}

function getModifications(){
        
    var model = document.getElementById("model");
    var modelStr = "";
    if(model!=null){
        modelStr = model.value;
    }
    createRequest();
    var url = "Controller.do?command=ADD_MODIFICATION_TO_ADD";
    request.open("POST", url, true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = updateModificationList;
    request.send("model="+modelStr);
}

function updateModificationList(){

    if(request.readyState == 4){
        if(request.status == 200){
            var xml = request.responseXML;
            var divId="select_modification";
            var tagName = "modification";
            var onchangeMethod = "";
            var select = "";
            if(xml!=null){
                select = createSelect(xml,tagName,onchangeMethod);
                select.setAttribute("size", 10);
            }
            var div = document.getElementById(divId);
            cleanElement(divId);
            div.appendChild(select);
        }
    }
}


function getYears(){
    var model = document.getElementById("model");
    var modelStr = "";
    if(model!=null){
        modelStr = model.value;
    }
    createRequest();
    var url = "Controller.do?command=ADD_YEAR_TO_SEARCH";
    request.open("POST", url, true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = updateYearsLists;
    request.send("model="+modelStr);

}

function updateYearsLists(){

    if(request.readyState == 4){
        if(request.status == 200){
            var xml = request.responseXML;
            var divId="select_year";
            var tagName = "year";
            var onchangeMethod = "";
            cleanElement(divId);
            var select = createSelectWithId(xml, tagName, "years_beg", onchangeMethod);
            var div = document.getElementById(divId);
            div.appendChild(select);
            select = createSelectWithId(xml, tagName, "years_end", onchangeMethod);
            div.appendChild(select);
        }
    }
}

function createSelect(xml,tagName,onchangeMethod){
    var list = xml.getElementsByTagName(tagName+"-list")[0];
    var items = list.getElementsByTagName(tagName);
    var select;
    if(items.length!=0){
        select = document.createElement("select");
        select.setAttribute("id",tagName);
        select.setAttribute("name",tagName);
        select.setAttribute("onchange",onchangeMethod);
        var id = "";
        var title = "All";
        var option = document.createElement("option");
        option.setAttribute("value",id);
        option.appendChild(document.createTextNode(title));
        select.appendChild(option);
        for (var I = 0 ; I < items.length ; I++) {
            var item = items[I];
            id = item.getElementsByTagName("id")[0].firstChild.nodeValue;
            title = item.getElementsByTagName("title")[0].firstChild.nodeValue;
            option = document.createElement("option");
            option.setAttribute("value",title);
            option.appendChild(document.createTextNode(title));
            select.appendChild(option);
        }

    }
    return select;
}

function createSelectWithId(xml,tagName,selectId,onchangeMethod){
    var list = xml.getElementsByTagName(tagName+"-list")[0];
    var items = list.getElementsByTagName(tagName);
    var select;
    if(items.length!=0){
        select = document.createElement("select");
        select.setAttribute("id",selectId);
        select.setAttribute("name",selectId);
        select.setAttribute("onchange",onchangeMethod);

        var id = "";
        var title = "All";
        var option = document.createElement("option");
        for (var I = 0 ; I < items.length ; I++) {
            var item = items[I];
            id = item.getElementsByTagName("id")[0].firstChild.nodeValue;
            title = item.getElementsByTagName("title")[0].firstChild.nodeValue;
            option = document.createElement("option");
            option.setAttribute("value",id);
            option.appendChild(document.createTextNode(title));
            select.appendChild(option);
        }

    }
    return select;
}


function cleanElement(elementId){
    var modelOptionDiv = document.getElementById(elementId);
    var removeItems=modelOptionDiv.childNodes;
    for (var J = removeItems.length ; 0 < J ; J--) {
        modelOptionDiv.removeChild(removeItems[J-1]);
    }
}

function showAdvancedSearch(){
    var button = document.getElementById("advanced-search-button");
    button.style.visibility = "hidden";
    button.style.display = "none";
    var table = document.getElementById("advanced-search");
    table.style.visibility = "visible";
    table.style.display = "block";
    var hideButton = document.getElementById("hide-advanced-search-button");
    hideButton.style.visibility = "visible";
    hideButton.style.display = "block";
}

function hideAdvancedSearch(){
    var button = document.getElementById("advanced-search-button");
    button.style.visibility = "visible";
    button.style.display = "block";
    var table = document.getElementById("advanced-search");
    table.style.visibility = "hidden";
    table.style.display = "none";
    var hideButton = document.getElementById("hide-advanced-search-button");
    hideButton.style.visibility = "hidden";
    hideButton.style.display = "none";
}