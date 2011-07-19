function getModels(){
    var status = document.getElementById("status");
    status.style.visibility = "visible";
    var make = document.getElementById("make");
    var makeStr = "";
    if(make!=null){
        makeStr = make.value;
    }
    createRequest();
    var url = "Controller?command=ADD_MODEL_TO_ADD";
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
            var status = document.getElementById("status");
            status.style.visibility = "hidden";
        }
    }

}

function getModifications(){
    var status = document.getElementById("status");
    status.style.visibility = "visible";
    var model = document.getElementById("model");
    var modelStr = "";
    if(model!=null){
        modelStr = model.value;
    }
    createRequest();
    var url = "Controller?command=ADD_MODIFICATION_TO_ADD";
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
            var onchangeMethod = "getOthers()";
            var select = createSelect(xml,tagName,onchangeMethod);
            select.setAttribute("size", 10);
            var div = document.getElementById(divId);
            cleanElement(divId);
            div.appendChild(select);
            getOthers();
            var status = document.getElementById("status");
            status.style.visibility = "hidden";
        }
    }
}

function getOthers(){
    var status = document.getElementById("status");
    status.style.visibility = "visible";
    var modification = document.getElementById("modification");
    var modificationStr = "";
    if(modification!=null){
        modificationStr = modification.value;
    }
    createRequest();
    var url = "Controller?command=ADD_OTHER_TO_ADD";
    request.open("POST", url, true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = updateOthersList;
    request.send("modification="+modificationStr);

}

function updateOthersList(){

    if(request.readyState == 4){
        if(request.status == 200){
            var xml = request.responseXML;
            var yearSelect = document.getElementById("year");
            
            var list = xml.getElementsByTagName("year-list")[0];
            
            var items = list.getElementsByTagName("year");
            if(items.length!=0){
                for (var I = 0 ; I < items.length ; I++) {
                    var item = items[I];
                    var id = item.getElementsByTagName("id")[0].firstChild.nodeValue;
                    var title = item.getElementsByTagName("title")[0].firstChild.nodeValue;
                    var option = document.createElement("option");
                    option.setAttribute("value",id);
                    option.appendChild(document.createTextNode(title));
                    yearSelect.appendChild(option);
                }

            }
            var specification = document.getElementById("specification");
            var previewButton = document.getElementById("preview_div");
            var optionButton = document.getElementById("option_div");
            var damageButton = document.getElementById("damage_div");
            var hideOptionButton = document.getElementById("hide_option_div");
            var hideDamageButton = document.getElementById("hide_damage_div");
            var selectDamage = document.getElementById("select_damage");
            var selectOption = document.getElementById("select_option");

            if(xml!=null && xml.length!=0){
                previewButton.style.visibility = "visible";
                optionButton.style.visibility = "visible";
                damageButton.style.visibility = "visible";
                specification.style.visibility = "visible";
                specification.style.display="block";
                //                getSalesDetails();
                document.getElementById("sales_details").style.display="block";
                getOptions();
            }else{
                previewButton.style.visibility = "hidden";
                optionButton.style.visibility = "hidden";
                damageButton.style.visibility = "hidden";
                hideOptionButton.style.visibility = "hidden";
                hideDamageButton.style.visibility = "hidden";
                selectDamage.innerHTML = "";
                selectDamage.style.visibility = "hidden";
                selectDamage.style.display = "none";
                selectOption.innerHTML = "";
                selectOption.style.visibility = "hidden";
                selectOption.style.display = "none";
                specification.style.visibility = "hidden";
                specification.style.display="none";
            }
            var status = document.getElementById("status");
            status.style.visibility = "hidden";

        }
    }

}

function hideOptions(){
    var selectOption = document.getElementById("select_option");
    selectOption.style.visibility = "hidden";
    selectOption.style.display = "none";
    var hide_option_div = document.getElementById("hide_option_div");
    hide_option_div.style.visibility = "hidden";
    var option_div = document.getElementById("option_div");
    option_div.style.visibility = "visible";
}

function showOptions(){
    var selectOption = document.getElementById("select_option");
    selectOption.style.visibility = "visible";
    selectOption.style.display = "block";
    var optionButton = document.getElementById("option_div");
    optionButton.style.visibility = "hidden";
    var hide_option_div = document.getElementById("hide_option_div");
    hide_option_div.style.visibility = "visible";
}

function getOptions(){ 
    var option_div = document.getElementById("option_div");
    option_div.style.visibility = "visible";
    getDamages();
}

function updateOptionList(){

    if(request.readyState == 4){
        if(request.status == 200){
            var make = request.responseText;
            var selectMake = document.getElementById("select_option");
            selectMake.innerHTML = make;
            var status = document.getElementById("status");
            status.style.visibility = "hidden";
            var option_div = document.getElementById("option_div");
            option_div.style.visibility = "visible";
            getDamages();
        }
    }
}

function hideDamages(){
    var selectDamage = document.getElementById("select_damage");
    selectDamage.style.visibility = "hidden";
    selectDamage.style.display = "none";
    var hide_damage_div = document.getElementById("hide_damage_div");
    hide_damage_div.style.visibility = "hidden";
    var damage_div = document.getElementById("damage_div");
    damage_div.style.visibility = "visible";
}

function showDamages(){
    var selectDamage = document.getElementById("select_damage");
    selectDamage.style.visibility = "visible";
    selectDamage.style.display = "block";
    var damageButton = document.getElementById("damage_div");
    damageButton.style.visibility = "hidden";
    var hide_damage_div = document.getElementById("hide_damage_div");
    hide_damage_div.style.visibility = "visible";
}

function getDamages(){
    var damage_div = document.getElementById("damage_div");
    damage_div.style.visibility = "visible";
    getSalesDetails();
}

function showAddButton(){
    createRequest();
    var url = "Controller?command=ADD_BUTTON_TO_ADD";
    request.open("POST", url, true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = updateAddButton;
    request.send("add_button=1");
}

function updateAddButton(){

    if(request.readyState == 4){
        if(request.status == 200){
            var addButton = request.responseText;
            var selectMake = document.getElementById("add_div");
            selectMake.innerHTML = addButton;
        }
    }
}

function cleanElement(elementId){
    var modelOptionDiv = document.getElementById(elementId);
    var removeItems=modelOptionDiv.childNodes;
    for (var J = removeItems.length ; 0 < J ; J--) {
        modelOptionDiv.removeChild(removeItems[J-1]);
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
            option.setAttribute("value",id);
            option.appendChild(document.createTextNode(title));
            select.appendChild(option);
        }

    }
    return select;
}

function createEdit(name,value){
    var input = document.createElement("input");
    input.setAttribute("type","text");
    input.setAttribute("id",name);
    input.setAttribute("name",name);
    input.setAttribute("value",value);
    return input;
}

function inserTr(table,first,second){
    var num = table.rows.length;
    var newRow = table.insertRow(num);
    var newCell = newRow.insertCell(0);
    newCell.innerHTML=first;
    newCell = newRow.insertCell(1);
    newCell.appendChild(second);
}