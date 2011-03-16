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
            var divId="specification";
            var tagName = "year";
            var onchangeMethod = "";
            var xmlElement = xml.getElementsByTagName("other-list")[0];
            var select = createSelectWithId(xmlElement,tagName,tagName,onchangeMethod);
            var div = document.getElementById(divId);
            var table =document.createElement("table");
            cleanElement(divId);
            inserTr(table, "Year", select);
            tagName = "trim";
            onchangeMethod = "";
            select = createSelectWithId(xmlElement,tagName,tagName,onchangeMethod);
            inserTr(table, lbl_trim, select);
            tagName = "body-style";
            onchangeMethod = "";
            select = createSelectWithId(xmlElement,tagName,tagName,onchangeMethod);
            inserTr(table, lbl_body_style, select);
            tagName = "drive-train";
            onchangeMethod = "";
            select = createSelectWithId(xmlElement,tagName,tagName,onchangeMethod);
            inserTr(table, lbl_drive_train, select);
            tagName = "exterior-color";
            onchangeMethod = "";
            select = createSelectWithId(xmlElement,tagName,tagName,onchangeMethod);
            inserTr(table, lbl_exterior_color, select);
            tagName = "interior-color";
            onchangeMethod = "";
            select = createSelectWithId(xmlElement,tagName,tagName,onchangeMethod);
            inserTr(table, lbl_interior_color, select);
            tagName = "fuel";
            onchangeMethod = "";
            select = createSelectWithId(xmlElement,tagName,tagName,onchangeMethod);
            inserTr(table, lbl_fuel, select);
            tagName = "tires";
            onchangeMethod = "";
            select = createSelectWithId(xmlElement,tagName,tagName,onchangeMethod);
            inserTr(table, lbl_tires, select);
            tagName = "top-type";
            onchangeMethod = "";
            select = createSelectWithId(xmlElement,tagName,tagName,onchangeMethod);
            inserTr(table, lbl_top_type, select);
            tagName = "wheels";
            onchangeMethod = "";
            select = createSelectWithId(xmlElement,tagName,tagName,onchangeMethod);
            inserTr(table, lbl_wheels, select);
            tagName = "door";
            onchangeMethod = "";
            select = createSelectWithId(xmlElement,tagName,tagName,onchangeMethod);
            inserTr(table, lbl_door, select);
            tagName = "engine";
            onchangeMethod = "";
            select = createSelectWithId(xmlElement,tagName,tagName,onchangeMethod);
            inserTr(table, lbl_engine, select);
            tagName = "interior-type";
            onchangeMethod = "";
            select = createSelectWithId(xmlElement,tagName,tagName,onchangeMethod);
            inserTr(table, lbl_interior_type, select);
            tagName = "transmission";
            onchangeMethod = "";
            select = createSelectWithId(xmlElement,tagName,tagName,onchangeMethod);
            inserTr(table, lbl_transmission, select);
            var editVin = createEdit("vin",12345678912345678);
            var editOdometr = createEdit("odometer",1000);
            inserTr(table, lbl_vin, editVin);
            inserTr(table, lbl_odometer, editOdometr);
            div.appendChild(table);

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
    var status = document.getElementById("status");
    status.style.visibility = "visible";
    createRequest();
    var url = "Controller?command=ADD_DAMAGE_TO_ADD";
    request.open("POST", url, true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = updateDamageList;
    request.send("damage=1");
}

function updateDamageList(){

    if(request.readyState == 4){
        if(request.status == 200){
            var xml = request.responseXML;
            var divId="select_damage";
            var tagName = "damage-condition";
            var onchangeMethod = "";
            var select = createSelectWithId(xml,tagName,"hood",onchangeMethod);
            cleanElement(divId);
            var table =document.createElement("table");
            var div = document.getElementById(divId);
            inserTr(table, lbl_hood, select);
            select = createSelectWithId(xml,tagName,"roof",onchangeMethod);
            inserTr(table, lbl_roof, select);
            select = createSelectWithId(xml,tagName,"windshield",onchangeMethod);
            inserTr(table, lbl_windshield, select);
            select = createSelectWithId(xml,tagName,"lf_door",onchangeMethod);
            inserTr(table, lbl_lf_door, select);
            select = createSelectWithId(xml,tagName,"lr_door",onchangeMethod);
            inserTr(table, lbl_lr_door, select);
            select = createSelectWithId(xml,tagName,"rf_door",onchangeMethod);
            inserTr(table, lbl_rf_door, select);
            select = createSelectWithId(xml,tagName,"rr_door",onchangeMethod);
            inserTr(table, lbl_rr_door, select);
            select = createSelectWithId(xml,tagName,"l_qtr_panel",onchangeMethod);
            inserTr(table, lbl_l_qtr_panel, select);
            select = createSelectWithId(xml,tagName,"r_qtr_panel",onchangeMethod);
            inserTr(table, lbl_r_qtr_panel, select);
            select = createSelectWithId(xml,tagName,"front_bumper",onchangeMethod);
            inserTr(table, lbl_front_bumper, select);
            select = createSelectWithId(xml,tagName,"rear_bumper",onchangeMethod);
            inserTr(table, lbl_rear_bumper, select);
            select = createSelectWithId(xml,tagName,"lf_fender",onchangeMethod);
            inserTr(table, lbl_lf_fender, select);
            select = createSelectWithId(xml,tagName,"rf_fender",onchangeMethod);
            inserTr(table, lbl_rf_fender, select);
            select = createSelectWithId(xml,tagName,"deck_lid",onchangeMethod);
            inserTr(table, lbl_deck_lid, select);
            select = createSelectWithId(xml,tagName,"seats",onchangeMethod);
            inserTr(table, lbl_seats, select);
            select = createSelectWithId(xml,tagName,"overall_vehicle",onchangeMethod);
            inserTr(table, lbl_overall_vehicle, select);
            var status = document.getElementById("status");
            status.style.visibility = "hidden";
            var damage_div = document.getElementById("damage_div");
            damage_div.style.visibility = "visible";
            div.appendChild(table);
            getSalesDetails();
        }
    }
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