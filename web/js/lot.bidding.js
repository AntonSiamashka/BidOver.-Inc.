
function getLotId() {
	return document.getElementById("LotId").value;
}
function getMaxBid() {
	return document.getElementById("MaxBid").value;
}
function getOutBid() {
	return document.getElementById("OutBid").value;
}
function getExpirationTime() {
	return document.getElementById("ExpirationTime").value;
}
function getLotQnt() {
	return document.getElementById("LotQnt").value;
}
function getLotFixedPrice() {
	return document.getElementById("LotFixedPrice").value;
}

// --------------------------------
// --------- Place Bid
// --------------------------------
function placeBid() {
	document.getElementById("progress_indicator").style.visibility = 'visible';
	replaceText(document.getElementById("msg"), '');
	createRequest();

	var url = "Controller.do";
	request.open("POST", url, true);
	request.onreadystatechange = updatePlaceBidPage;
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.send( "command=PLACE_BID&lotid="+getLotId()+"&maxbid="+getMaxBid()+"&outbid="+getOutBid() );
}

function updatePlaceBidPage() {
	if (request.readyState == 4) {
//alert(request.responseText);
		var jsonData = eval("("+request.responseText+")");
		
		if (jsonData.status == 0) {
                    replaceText(document.getElementById("msg"), jsonData.message);
                    document.getElementById("progress_indicator").style.visibility = 'hidden';
                }
		else getHighBid();
	}
}

// --------------------------------
function getHighBid() {
	createRequest();
	var url = "Controller.do";
	request.open("POST", url, true);
	request.onreadystatechange = updateHighBidPage;
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.send( "command=GET_HIGH_BID&" + new Date().getTime() + "&lotid=" + encodeURI(getLotId()) + "&etime=" + getExpirationTime() );
}

function updateHighBidPage() {
	if (request.readyState == 4) {
//alert(request.responseText);
		var jsonData = eval("("+request.responseText+")");
		if (jsonData.status == 1) {
			document.getElementById("progress_indicator").style.visibility = 'hidden';
			replaceText(document.getElementById("TimeLeft"), jsonData.timeleft);
			replaceText(document.getElementById("curMaxBid"), jsonData.maxbid);
			replaceText(document.getElementById("nBids"), jsonData.nbids);
			replaceText(document.getElementById("requireOutBid"), jsonData.outbid);
			document.getElementById("OutBid").value = jsonData.outbid;
                        document.getElementById("MaxBid").value = '';
		}
		else if (jsonData.status == 2) {
			closeBidding(jsonData.lotid);
		}
		else replaceText(document.getElementById("msg"), jsonData.message);
	}
}

// --------------------------------
// --------- Buy Lot Now
// --------------------------------
function lotBuyNow() {
	createRequest();
	var url = "Controller.do";

	request.open("POST", url, true);
	request.onreadystatechange = updateBuyNowPage;
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.send( "command=BUY_NOW&lotId="+encodeURI(getLotId())+"&lotFixedPrice="+encodeURI(getLotFixedPrice())+"&lotQnt="+encodeURI(getLotQnt()) );
}

function updateBuyNowPage() {
	if (request.readyState == 4) {
//alert(request.responseText);
		var jsonData = eval("("+request.responseText+")");

		if(jsonData.status == 0) {
                    replaceText(document.getElementById("msg"), jsonData.message);
                }
		else {
//			replaceText(document.getElementById("msg_curbid"), newMessage);
//			document.getElementById("BidBlock").style.display="none";
//			document.getElementById("BuyNowBlock").style.display="none";
			window.location = "./cp.jsp";
		}
	}
}

// --------------------------------
function closeBidding(lotId) {
	createRequest();
	var url = "Controller.do";
	request.open("POST", url, true);
	request.onreadystatechange = updateCloseBidding;
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.send( "command=CLOSE_BIDDING&dt=" + new Date().getTime() + "&lotId="+lotId );
}

function updateCloseBidding() {
	if (request.readyState == 4) {
//alert(request.responseText);
		var jsonData = eval("("+request.responseText+")");
		if (jsonData.status == 1) window.location.reload(true);
	}
}