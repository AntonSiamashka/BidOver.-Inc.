function getSalesDetails(){
    createRequest();
    var url = "Controller.do";
    request.open("POST", url, true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = updateSalesDetailsList;
    request.send("command=ADD_SALES_DETAILS_TO_ADD");
}

function updateSalesDetailsList(){

    if(request.readyState == 4){
        if(request.status == 200){
            var jsonData = eval("("+request.responseText+")");
            replaceText(document.getElementById("sales_duration_list"), jsonData.sales_duration_list);
            replaceText(document.getElementById("fee_floor_price"), jsonData.fee_floor_price);
            replaceText(document.getElementById("currency_list"), jsonData.currency_list);
            replaceText(document.getElementById("country_list"), jsonData.country_list);
            replaceText(document.getElementById("atd_list"), jsonData.atd_list);
        }
    }

}
