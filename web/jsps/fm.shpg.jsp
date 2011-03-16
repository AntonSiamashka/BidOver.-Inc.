<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.LNG}"/>
<fmt:setBundle basename="Messages"/>
<table>
  <tr>
    <td><input id="cbFS" type="checkbox" name="shipping" value="<fmt:message key="lbl.freeshipping" />" onClick="ckboxFS(this.form);getShipping(this.form);" /></td>
    <td colspan="2"><fmt:message key="lbl.freeshipping" /></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><input id="rFS1" disabled type="radio" name="fsl" value="<fmt:message key="lbl.locally" />" onClick="getShipping(this.form);" /></td>
    <td><fmt:message key="lbl.locally" /></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><input id="rFS2" disabled type="radio" name="fsl" value="<fmt:message key="lbl.inland" />" onClick="getShipping(this.form);" /></td>
    <td><fmt:message key="lbl.inland" /></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><input id="rFS3" disabled type="radio" name="fsl" value="<fmt:message key="lbl.worldwide" />" onClick="getShipping(this.form);" /></td>
    <td><fmt:message key="lbl.worldwide" /></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><input id="rFS4" disabled type="radio" name="fsl" value="<fmt:message key="lbl.other" />"  onClick="getShipping(this.form);"/></td>
    <td><fmt:message key="lbl.other" />&nbsp;<input id="tFS4" disabled type="text" name="fslo" value=""  onKeyUp="getShipping(this.form);"/></td>
  </tr>
  <tr>
    <td><input id="cbPS" type="checkbox" name="shipping" value="<fmt:message key="lbl.paidshipping" />" onClick="ckboxPS(this.form);getShipping(this.form);" /></td>
    <td colspan="2"><fmt:message key="lbl.paidshipping" /></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><input id="rPS1" disabled type="radio" name="csl" value="<fmt:message key="lbl.worldwide" />" onClick="getShipping(this.form);" /></td>
    <td><fmt:message key="lbl.worldwide" /></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><input id="rPS2" disabled type="radio" name="csl" value="<fmt:message key="lbl.other" />" onClick="getShipping(this.form);" /></td>
    <td><fmt:message key="lbl.other" />&nbsp;<input id="tPS2" disabled type="text" name="cslo" value="" onKeyUp="getShipping(this.form);" /></td>
  </tr>
</table>
