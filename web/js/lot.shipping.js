
function getShipping(frm){
	var message = "";
	var free = "";
	var paid = "";
        var i;
	
      if (frm.shipping[0].checked){
		for (i = 0; i < frm.fsl.length; i++) {
		  if (frm.fsl[i].checked) {
			  free = (i==frm.fsl.length-1 ? frm.fslo.value : frm.fsl[i].value);
  			  message += frm.shipping[0].value+": "+free+"<br><br>";
			  break;
		  }
		}
      }
     
      if (frm.shipping[1].checked){
		for (i = 0; i < frm.csl.length; i++) {
		  if (frm.csl[i].checked) {
			  paid = (i==frm.csl.length-1 ? frm.cslo.value : frm.csl[i].value);
   			  message += frm.shipping[1].value+": "+paid;
			  break;
		  }
		}
	  }

	replaceText(document.getElementById("msg_shpg"), message);
	document.getElementById("free_shipping").value = free;
	document.getElementById("paid_shipping").value = paid;
}

function ckboxFS(frm){
	if (frm.shipping[0].checked) {
  		document.getElementById("rFS1").disabled = false;
		document.getElementById("rFS2").disabled = false;
		document.getElementById("rFS3").disabled = false;
		document.getElementById("rFS4").disabled = false;
		document.getElementById("tFS4").disabled = false; 
	}
	else {
      	document.getElementById("rFS1").disabled = true;
		document.getElementById("rFS2").disabled = true;
		document.getElementById("rFS3").disabled = true;
		document.getElementById("rFS4").disabled = true;
		document.getElementById("tFS4").disabled = true;
	}
}

function ckboxPS(frm){
	if (frm.shipping[1].checked) {
		document.getElementById("rPS1").disabled = false;
		document.getElementById("rPS2").disabled = false;
		document.getElementById("tPS2").disabled = false;

	}
	else {
		document.getElementById("rPS1").disabled = true;
		document.getElementById("rPS2").disabled = true;
		document.getElementById("tPS2").disabled = true;
	}
}