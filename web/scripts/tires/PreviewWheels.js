function createPreview(){
    var errorFlag = false;
    cleanPreview("preview");
    cleanPreview("message-div");
    var previewLot = showPreviewLot();
    if(previewLot==null){
        errorFlag = true;
    }
    if(!errorFlag){
        cleanPreview();
        hidePreview();
        var width = getSelectedOptionChild("width-select");
        var diameter = getSelectedOptionChild("diameter-select");
        var bcd = getSelectedOptionChild("bcd-select");
        var et = getSelectedOptionChild("ed-select");
        var previewDiv = document.getElementById("preview");
        var table = document.createElement("table");
        inserTr(table,"Width",document.createTextNode(width));
        inserTr(table,"Diameter",document.createTextNode(diameter));
        inserTr(table,"PCT",document.createTextNode(bcd));
        inserTr(table,"ET",document.createTextNode(et));
        var lotTable = showPreviewLot();
        inserTr(table,"Lot Details",lotTable);
        previewDiv.appendChild(table);
        showPreview();
        hideAddFields();
    }
}

function inserTr(table,first,second){
    var num = table.rows.length;
    var newRow = table.insertRow(num);
    var newCell = newRow.insertCell(0);
    newCell.innerHTML=first;
    newCell = newRow.insertCell(1);
    newCell.appendChild(second);
}
 
function getSelectedOptionChild(selectName){
    var child;
    var select = document.getElementById(selectName);
    if(select!=null){
        var id = select.value;
        var items = select.getElementsByTagName("option");
        for (var I = 0 ; I < items.length ; I++) {
            var item = items[I];
            var value = item.getAttribute("value");
            if(value==id){
                child = item.firstChild.nodeValue;
            }
        }
    }
    return child;
}

function cleanPreview(name){
    var previewTable = document.getElementById(name);
    var items = previewTable.childNodes;
    for (var I = items.length ; 0 < I ; I--) {
        previewTable.removeChild(items[I-1]);
    }
}

function hideAddFields(){
    previewButton = document.getElementById("add-fields");
    previewButton.style.visibility = "hidden";
    previewButton.style.display = "none";
}
function showAddFields(){
    previewButton = document.getElementById("add-fields");
    previewButton.style.visibility = "visible";
    previewButton.style.display = "block";
    hidePreview();
}

function showPreview(){
    var preview = document.getElementById("preview");
    preview.style.visibility = "visible";
    preview.style.display = "block";
}

function hidePreview(){
    var preview = document.getElementById("preview");
    preview.style.visibility = "hidden";
    preview.style.display = "none";
}