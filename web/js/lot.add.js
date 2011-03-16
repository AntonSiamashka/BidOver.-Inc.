
function getLeftKey() {
	return document.getElementById("leftKey").value;
}

function getTitle() {
	return document.getElementById("Title").value;
}

function getDescr() {
	return document.getElementById("Descr").value;
}

function getShipsToFree() {
	return document.getElementById("ShipsToFree").value;
}

function getShipsToPaid() {
	return document.getElementById("ShipsToPaid").value;
}

function getHandlingTime() {
	return document.getElementById("HandlingTime").value;
}

function getLocCode() {
	return document.getElementById("LC").value;
}

function getPmntMeth() {
	return document.getElementById("PmntMeth").value;
}

function getPmntInstr() {
	return document.getElementById("PmntInstr").value;
}

function getCondId() {
	return document.getElementById("LotCond").value;
}

function getSalesDuration() {
	return document.getElementById("SalesDuration").value;
}

function getBuyNow() {
	return document.getElementById("fixed_price").value;
}

function getFPSOnly() {
	return document.getElementById("fixed_price_only").value;
}

function getItemsAvailable() {
	return document.getElementById("qnt_items_available").value;
}

function getMinPrice() {
	return document.getElementById("floor_price").value;
}

function getStartingBid() {
	return document.getElementById("starting_bid").value;
}

function getCurCode() {
	return document.getElementById("CurCode").value;
}

function getBidForbid() {
	return document.getElementById("forbid_bidding").value;
}

function ckboxBidForbid() {
	if (document.getElementById("forbid_bidding").value=='1') document.getElementById("forbid_bidding").value='0';
	else document.getElementById("forbid_bidding").value='1';
}

function ckboxFPSOnly() {
	if (document.getElementById("fixed_price_only").value=='1') {
		document.getElementById("fixed_price_only").value='0';
                document.getElementById("BlkQntDefault").style.display="block";
                document.getElementById("BlkQntItemsAvailable").style.display="none";
		document.getElementById("floor_price").disabled = false;
		document.getElementById("starting_bid").disabled = false;
	}
	else {
		document.getElementById("fixed_price_only").value='1';
		document.getElementById("floor_price").value = '0';
		document.getElementById("floor_price").disabled = true;
		document.getElementById("starting_bid").value = '0';
		document.getElementById("starting_bid").disabled = true;
		document.getElementById("BlkStartingBid").style.display="none";
                document.getElementById("BlkQntDefault").style.display="none";
                document.getElementById("BlkQntItemsAvailable").style.display="block";
	}
}

function ckboxFloorPrice() {
	if (document.getElementById("floor_price").value != '') {
		document.getElementById("BlkStartingBid").style.display="block";
	}
	else {
		document.getElementById("BlkStartingBid").style.display="none";
		document.getElementById("starting_bid").value = '';
	}
}

function reverse() {
	document.getElementById("stOne").style.color="#444";
	document.getElementById("stTwo").style.color="#ccc";
	document.getElementById("lotDescription").style.display="block";
	document.getElementById("lotRevision").style.display="none";
}

function showRevision() {
	document.getElementById("lotRevision").style.display="block";
}

function hideDescription() {
	document.getElementById("lotDescription").style.display="none";
}
// -------------------------------
// --------- Add New Lot (Revision)
// -------------------------------
function getLotInfo(step) {
	document.getElementById("progress_indicator_lot").style.visibility = 'visible';
	createRequest();
	var url = "ck.jsp";
	var alt = step == 1 ? 4 : 5;

	request.open("POST", url, true);
	request.onreadystatechange = updateAddPage;
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.send("alt="+alt+
				"&leftKey="+encodeURI(getLeftKey())+
				"&lot_title="+encodeURI(getTitle())+
				"&lot_descr="+encodeURI(getDescr())+
				"&lot_min_price="+encodeURI(getMinPrice())+
				"&lot_starting_bid="+encodeURI(getStartingBid())+
				"&lot_buynow="+encodeURI(getBuyNow())+
				"&lot_fpsonly="+encodeURI(getFPSOnly())+
				"&lot_items_qnt="+encodeURI(getItemsAvailable())+
				"&lot_ship_free="+encodeURI(getShipsToFree())+
				"&lot_ship_paid="+encodeURI(getShipsToPaid())+
				"&lot_handling="+encodeURI(getHandlingTime())+
				"&lot_pmnt_meth="+encodeURI(getPmntMeth())+
				"&lot_pmnt_instr="+encodeURI(getPmntInstr())+
				"&lot_currency="+encodeURI(getCurCode())+
				"&lot_cond_id="+encodeURI(getCondId())+
				"&lot_location="+encodeURI(getLocCode())+
				"&lot_forbid="+encodeURI(getBidForbid())+
				"&lot_sales_duration="+encodeURI(getSalesDuration()));
}

function updateAddPage() {
	if (request.readyState == 4) {
alert(request.responseText);		
		document.getElementById("progress_indicator_lot").style.visibility = 'hidden';
		var jsonData = eval("("+request.responseText+")");
		var newStatus = jsonData.status;

		if (newStatus == 1) {
			replaceText(document.getElementById("revNode"), jsonData.node);
			replaceText(document.getElementById("revTitle"), jsonData.title);
			replaceText(document.getElementById("revDescr"), jsonData.descr);
			replaceText(document.getElementById("revCond"), jsonData.cond);
			replaceText(document.getElementById("revDuration"), jsonData.duration);
			replaceText(document.getElementById("revLocation"), jsonData.location);
			if (jsonData.forbid == 1) document.getElementById("revBidForbid").checked = true;
			replaceText(document.getElementById("revFixPrice"), jsonData.fixprice);
			if (jsonData.fpsonly == 1) document.getElementById("revFPSOnly").checked = true;
			replaceText(document.getElementById("revItemsQnt"), jsonData.itemsqnt);
			replaceText(document.getElementById("revFloorPrice"), jsonData.floorprice);
			replaceText(document.getElementById("revStartBid"), jsonData.startbid);
			replaceText(document.getElementById("revPayment"), jsonData.pmntmethod);
			replaceText(document.getElementById("revPayInstr"), jsonData.pmntinstr);
			replaceText(document.getElementById("revShipFree"), jsonData.shipfree);
			replaceText(document.getElementById("revShipPaid"), jsonData.shippaid);
			replaceText(document.getElementById("revHandling"), jsonData.handling);
			replaceText(document.getElementById("revFeeTotal"), jsonData.feetotal);
			replaceText(document.getElementById("revExpSaleDate"), jsonData.expsaledate);
			
			document.getElementById("stOne").style.color="#ccc";
			document.getElementById("stTwo").style.color="#444";
			
			showRevision();
			hideDescription();
		}
		else if (newStatus == 2) window.location = "cp.jsp?alt=32&lot="+jsonData.LID;
		else replaceText(document.getElementById("msg_lot"), jsonData.message);
	}
}