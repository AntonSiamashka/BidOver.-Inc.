<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.LNG}"/>
<fmt:setBundle basename="Messages"/>
<table title="Payment Method">
  <tr align="center">
    <td style="border-bottom: 1px solid #444">P</td>
    <td style="border-bottom: 1px solid #444">A</td>
    <td></td>
  </tr>
  <tr>
    <td><input type="radio" name="pmntpref" id="PP1" value="<fmt:message key="lbl.preferred" />" onclick="setPref(this.form)"></td>
    <td><input type="checkbox" name="pmntmethod" id="PM1" value="PayPal" onClick="getPmnt(this.form);"></td>
    <td>PayPal</td>
  </tr>
  <tr>
    <td><input type="radio" name="pmntpref" id="PP2" value="<fmt:message key="lbl.preferred" />" onclick="setPref(this.form)"></td>
    <td><input type="checkbox" name="pmntmethod" id="PM2" value="Credit Card" onClick="getPmnt(this.form);"></td>
    <td>Credit Card</td>
  </tr>
  <tr>
    <td><input type="radio" name="pmntpref" id="PP3" value="<fmt:message key="lbl.preferred" />" onclick="setPref(this.form)"></td>
    <td><input type="checkbox" name="pmntmethod" id="PM3" value="eCheque" onClick="getPmnt(this.form);"></td>
    <td>eCheque</td>
  </tr>
  <tr>
    <td><input type="radio" name="pmntpref" id="PP4" value="<fmt:message key="lbl.preferred" />" onclick="setPref(this.form)"></td>
    <td><input type="checkbox" name="pmntmethod" id="PM4" value="WireTransfer" onClick="getPmnt(this.form);"></td>
    <td>WireTransfer</td>
  </tr>
  <tr>
    <td><input type="radio" name="pmntpref" id="PP5" value="<fmt:message key="lbl.preferred" />" onclick="setPref(this.form)"></td>
    <td><input type="checkbox" name="pmntmethod" id="PM5" value="WebMoney" onClick="getPmnt(this.form);"></td>
    <td>WebMoney</td>
  </tr>
  <tr>
    <td><input type="radio" name="pmntpref" id="PP6" value="<fmt:message key="lbl.preferred" />" onClick="setPref(this.form)"></td>
    <td><input type="checkbox" name="pmntmethod" id="PM6" value="<fmt:message key="lbl.other" />" onClick="getPmnt(this.form);"></td>
    <td><fmt:message key="lbl.other" />&nbsp;<input name="spcpmntmethod" id="SPM" type="text" value="" onKeyUp="getPmnt(this.form);"></td>
  </tr>
</table>
<div class="small">P - <fmt:message key="lbl.prefmeth" /></div>
<div class="small">A - <fmt:message key="lbl.altermeth" /></div>