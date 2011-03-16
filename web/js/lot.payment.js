function setPref(frm){
   for (var i = 0; i < frm.pmntpref.length; i++) {
		if (frm.pmntpref[i].checked) frm.pmntmethod[i].checked = true;
   }
    getPmnt(frm);
}

function getPmnt(frm){
   var message = "";
   var j = 0;

   for (var i = 0; i < frm.pmntpref.length; i++) {
      if (frm.pmntpref[i].checked) {
		  if (i==frm.pmntpref.length-1) message += frm.spcpmntmethod.value + " " + frm.pmntpref[i].value;
		  else message += frm.pmntmethod[i].value + " " + frm.pmntpref[i].value;
		  j=i;
		  break;
      }
   }

   for (i = 0; i < frm.pmntmethod.length; i++)
      if (frm.pmntmethod[i].checked & i!=j){
		  if (i==frm.pmntmethod.length-1) message += ", " + frm.spcpmntmethod.value;
		  else message += ", " + frm.pmntmethod[i].value
      }

   replaceText(document.getElementById("msg_pmnt"), message);
   document.getElementById("payment_method").value = message;
}