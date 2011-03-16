function getModels(){
    var make = document.getElementById("make");
    var makeStr = "";
    if(make!=null){
        makeStr = make.value;
    }
    createRequest();
    var url = "Controller?command=ADD_MODEL_TO_SEARCH";
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
            var onchangeMethod = "getYears()";
            var select = createSelectWithId(xml,tagName,tagName,onchangeMethod);
            select.setAttribute("size", 10);
            var div = document.getElementById(divId);
            cleanElement(divId);
            div.appendChild(select);
            getYears();
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
    var url = "Controller?command=ADD_YEAR_TO_SEARCH";
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

function createSelectWithId(xml,tagName,selectId,onchangeMethod){
    var list = xml.getElementsByTagName(tagName+"-list")[0];
    var items = list.getElementsByTagName(tagName);
    var select;
    if(items.length!=0){
        select = document.createElement("select");
        select.setAttribute("id",selectId);
        select.setAttribute("name",selectId);
        select.setAttribute("onchange",onchangeMethod);
        for (var I = 0 ; I < items.length ; I++) {
            var item = items[I];
            var id = item.getElementsByTagName("id")[0].firstChild.nodeValue;
            var title = item.getElementsByTagName("title")[0].firstChild.nodeValue;
            var option = document.createElement("option");
            option.setAttribute("value",title);
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