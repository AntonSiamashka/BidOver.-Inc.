function createPreview(){
    var errorFlag = false;
    cleanPreview("preview-table");
    cleanPreview("message-div");
    var previewLot = showPreviewLot();
    if(previewLot==null){
        errorFlag = true;
    }
    if(!errorFlag){
        cleanPreview();
        hideAddFields();
        showPreview();
        var categoryName = getSelectedOptionChild("category-select");
        var subCategoryName = getSelectedOptionChild("sub-category-select");
        var itemName = getSelectedOptionChild("item-select");
        var makeTitle = getSelectedOptionChild("make-select");
        var modelTitle = getSelectedOptionChild("model-select");
        var yearBegin = getSelectedOptionChild("year-begin-select");
        var yearEnd = getSelectedOptionChild("year-end-select");
        var table = document.getElementById("preview-table");
        inserTr(table,"Category",document.createTextNode(categoryName));
        inserTr(table,"SubCategory",document.createTextNode(subCategoryName));
        inserTr(table,"Item",document.createTextNode(itemName));
        inserTr(table,"Make",document.createTextNode(makeTitle));
        inserTr(table,"Model",document.createTextNode(modelTitle));
        inserTr(table,"Year Begin",document.createTextNode(yearBegin));
        inserTr(table,"Year End",document.createTextNode(yearEnd));
        var lotTable = showPreviewLot();
        inserTr(table,"Lot Details",lotTable);
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

