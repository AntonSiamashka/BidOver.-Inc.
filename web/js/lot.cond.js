function setCond(frm) {
   for (var i = 0; i < frm.cond.length; i++) {
      if (frm.cond[i].checked) {
		  document.getElementById("condition").value = frm.cond[i].value;
		  
  		  if (i == 0) { 
		  	document.getElementById("CondNR").style.fontWeight = 'bold';
		  	document.getElementById("CondUsed").style.fontWeight = 'normal';
		  	document.getElementById("CondNew").style.fontWeight = 'normal';
		  }
		  else if (i < 11) {
			document.getElementById("CondUsed").style.fontWeight = 'bold';
		  	document.getElementById("CondNR").style.fontWeight = 'normal';
		  	document.getElementById("CondNew").style.fontWeight = 'normal';
		  }
		  else if (i > 10) { 
		  	document.getElementById("CondNew").style.fontWeight = 'bold';
		  	document.getElementById("CondUsed").style.fontWeight = 'normal';
		  	document.getElementById("CondNR").style.fontWeight = 'normal';
		  }
		  break;
      }
   }
}

function getCond(i) {
      document.forms.Condition.cond[i].checked = true;
	  if (i == 0) { 
	  	document.getElementById("CondNR").style.fontWeight = 'bold';
	  	document.getElementById("CondUsed").style.fontWeight = 'normal';
	  	document.getElementById("CondNew").style.fontWeight = 'normal';
	  }
	  else if (i < 11) {
		document.getElementById("CondUsed").style.fontWeight = 'bold';
	  	document.getElementById("CondNR").style.fontWeight = 'normal';
	  	document.getElementById("CondNew").style.fontWeight = 'normal';
	  }
	  else if (i > 10) { 
	  	document.getElementById("CondNew").style.fontWeight = 'bold';
	  	document.getElementById("CondUsed").style.fontWeight = 'normal';
	  	document.getElementById("CondNR").style.fontWeight = 'normal';
	  }
}