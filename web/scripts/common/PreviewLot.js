function showPreviewLot(){
    var regNum=/^\d+$/;
    var regText=/^[a-zA-Z ]{1,255}$/;
    var errorMessage="";
    var errorFlag = false;
    var messageDiv = "message-div";
    //var condition = getSelectedRadioValue("cond");
    var sales_duration = getSelectedOptionChild("sales_duration");
    var country_code = getSelectedOptionChild("country_code");
    var atd_code = getSelectedOptionChild("atd_code");
    var location_code = getSelectedOptionChild("location_code");
    var forbid_bidding = getInputValue("forbid_bidding");
    var fixed_price = getInputValue("fixed_price");
    var fixed_price_only = getInputValue("fixed_price_only");
    var qnt_items_available = getInputValue("qnt_items_available");
    var floor_price = getInputValue("floor_price");
    var starting_bid = getInputValue("starting_bid");
    var currency_code = getSelectedOptionChild("currency_code");
    var payment_details = getPaymentDetails();
    var payment_instructions = getInputValue("payment_instructions");
    var shipping = getShippingDetails();
    var handling_time = getInputValue("handling_time");
    var description = getInputValue("description");
    var table = null;
    if(location_code==null || location_code==''){
        addTextInDiv(messageDiv,msg_lot_location_code);
        errorFlag = true; 
    }
    if(fixed_price==null || fixed_price==''){
        fixed_price = lbl_lot_not_allowed;
    }else if (!regNum.test(fixed_price)) {
        addTextInDiv(messageDiv,msg_lot_fixed_price);
        errorFlag = true;
    }
    if(floor_price==null || floor_price==''){
        floor_price = lbl_lot_not_allowed;
    }else if (!regNum.test(floor_price)) {
        addTextInDiv(messageDiv,msg_lot_floor_price);
        errorFlag = true;
    }
    if(starting_bid==null || starting_bid==''){
        starting_bid = lbl_lot_not_allowed;
    }else if (!regNum.test(starting_bid)) {
        addTextInDiv(messageDiv,msg_lot_starting_bid);
        errorFlag = true;
    }
    if(payment_details==''){
        payment_details = lbl_lot_not_allowed;
    }else if(payment_details==null){
        errorFlag = true;
    }
    if(shipping==''){
        shipping = lbl_lot_not_allowed;
    }else if(shipping==null){
        errorFlag = true;
    }
    if(payment_instructions==''){
        payment_instructions = lbl_lot_not_allowed;
    }else if (!regText.test(payment_instructions)) {
        addTextInDiv(messageDiv,msg_lot_payment_instructions);
        errorFlag = true;
    }
    if(description==''){
        description = lbl_lot_not_allowed;
    }else if (!regText.test(description)) {
        addTextInDiv(messageDiv,msg_lot_description);
        errorFlag = true;
    }
    if(!errorFlag){
        if(forbid_bidding==0){
            forbid_bidding = lbl_lot_yes;
        }else if (forbid_bidding==1){
            forbid_bidding = lbl_lot_no;
        }
        if(fixed_price_only==0){
            fixed_price_only = lbl_lot_yes;
        }else if(fixed_price_only==1){
            fixed_price_only = lbl_lot_no;
        }
        table = document.createElement("table");
        //inserTr(table,lbl_cond,document.createTextNode(condition));
        inserTrIfNotEmpty(table,lbl_sales_duration,document.createTextNode(sales_duration));
        inserTrIfNotEmpty(table,lbl_country_code,document.createTextNode(country_code));
        inserTrIfNotEmpty(table,lbl_atd_code,document.createTextNode(atd_code));
        inserTrIfNotEmpty(table,lbl_location_code,document.createTextNode(location_code));
        inserTrIfNotEmpty(table,lbl_forbid_bidding,document.createTextNode(forbid_bidding));
        inserTrIfNotEmpty(table,lbl_fixed_price,document.createTextNode(fixed_price));
        inserTrIfNotEmpty(table,lbl_fixed_price_only,document.createTextNode(fixed_price_only));
        inserTrIfNotEmpty(table,lbl_qnt_items_available,document.createTextNode(qnt_items_available));
        inserTrIfNotEmpty(table,lbl_floor_price,document.createTextNode(floor_price));
        inserTrIfNotEmpty(table,lbl_starting_bid,document.createTextNode(starting_bid));
        inserTrIfNotEmpty(table,lbl_currency_code,document.createTextNode(currency_code));
        inserTrIfNotEmpty(table,lbl_pmntmethod,document.createTextNode(payment_details));
        inserTrIfNotEmpty(table,lbl_payment_instructions,document.createTextNode(payment_instructions));
        inserTrIfNotEmpty(table,lbl_shipping,document.createTextNode(shipping));
        inserTrIfNotEmpty(table,lbl_handling_time,document.createTextNode(handling_time));
        inserTrIfNotEmpty(table,lbl_description,document.createTextNode(description));
    }
    return table;
}

function getPaymentDetails(){
    var value="";
    var pmntpref = document.getElementsByName("pmntpref");
    var pmntmethod = document.getElementsByName("pmntmethod");
    if(pmntmethod!=null){
        var length = pmntmethod.length-1;
        var itemPmntmethod;
        var itemPmntpref;
        for (var I = 0 ; I < length ; I++) {
            itemPmntmethod = pmntmethod[I];
            itemPmntpref = pmntpref[I];
            if(itemPmntmethod.checked && itemPmntpref.checked){
                value = value+itemPmntmethod.value+"(предпочтительно).";
            }else if (itemPmntmethod.checked){
                value = value+itemPmntmethod.value+".";
            }
        }
        itemPmntmethod = pmntmethod[length];
        itemPmntpref = pmntpref[length];
        var spcpmntmethod = getInputValue("SPM");
        if(itemPmntmethod.checked && itemPmntpref.checked){
            value = value+spcpmntmethod+"(предпочтительно).";
        }else if (itemPmntmethod.checked){
            value = value+spcpmntmethod+".";
        }
    }

    return value;
}

function getShippingDetails(){
    var value="";
    var selectName = "shipping";
    var radio = document.getElementsByName(selectName);
    if(radio!=null){
        var length = radio.length;
        var item;
        for (var I = 0 ; I < length ; I++) {
            item = radio[I];
            if(item.checked){
                if(item.id=='cbFS'){
                   var fsl = getShippingRadioValue("fsl","tFS4");
                   value=value+"бесплатная доставка: "+fsl+". ";
                }else if(item.id=='cbPS'){
                   var csl = getShippingRadioValue("csl","tPS2");
                   value=value+"за счёт покупателя: "+csl+". ";
                }
            }
        }
    }
    return value;
}

function getShippingRadioValue(selectName,otherId){
    var value="";
    var radio = document.getElementsByName(selectName);
    if(radio!=null){
        var length = radio.length-1;
        var item;
        for (var I = 0 ; I <= length ; I++) {
            item = radio[I];
            if(item.checked & I==length){
                value=getInputValue(otherId);
            }else if(item.checked){
                value=item.value;
                break;
            }
        }
    }
    return value;
}

function getSelectedOptionChild(selectName){
    var child;
    var select = document.getElementById(selectName);
    if(select!=null){
        var id = select.value;
        if(id!=''){
            child = $("#"+selectName+" option[value='"+id+"']").text();
        } else {
            child = '';
        }
    }
    return child;
}

function getSelectedRadioValue(selectName){
    var value="";
    var radio = document.getElementsByName(selectName);
    if(radio!=null){
        var length = radio.length;
        for (var I = 0 ; I < length ; I++) {
            var item = radio[I];
            if(item.checked){
                value=item.value;
                break; 
            }
        }
    }
    return value;
}

function getSelectedCheckboxValue(selectName){
    var value="";
    var radio = document.getElementsByName(selectName);
    if(radio!=null){
        var length = radio.length;
        for (var I = 0 ; I < length ; I++) {
            var item = radio[I];
            if(item.checked){
                value=value+item.value + " ";
            }
        }
    }
    return value;
}

function getInputValue(name){
    var value;
    var input = document.getElementById(name);
    if(input!=null){
        value = input.value;
    }
    return value;
}

function addTextInDiv(id,text){
    var div = document.getElementById(id);
    div.appendChild(document.createTextNode(text))
}

function inserTrIfNotEmpty(table,first,second){
    if(second.nodeValue!=null && second.nodeValue!='' && second.nodeValue!=lbl_none) {
        var num = table.rows.length;
        var newRow = table.insertRow(num);
        var newCell = newRow.insertCell(0);
        newCell.innerHTML=first;
        newCell = newRow.insertCell(1);
        newCell.appendChild(second);
    }
}