<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.LNG}"/>
<fmt:setBundle basename="Messages"/>
<table title="Lot Condition" style="border-left: 1px solid #ccc;">
  <tr>
    <td><input type="radio" checked name="cond" id="Cond0" value="0" onclick="setCond(this.form)"></td>
    <td><span id="CondNR" style="font-weight:bold;"><fmt:message key="lbl.notrequired" /></span></td>
  </tr>
  <tr><td></td><td><span id="CondUsed"><fmt:message key="lbl.used" /></span></td></tr>
  <tr><td></td><td>
  	<table style="border-top: 1px solid #ccc;">
    	<tr align="center"><td><span class="small"><fmt:message key="lbl.grade" />:</span></td><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td><td>8</td><td>9</td><td>10</td></tr>
    	<tr>
        <td></td>
        <td><input type="radio" name="cond" id="Cond1" value="1" onclick="setCond(this.form)"></td>
        <td><input type="radio" name="cond" id="Cond2" value="2" onclick="setCond(this.form)"></td>
        <td><input type="radio" name="cond" id="Cond3" value="3" onclick="setCond(this.form)"></td>
        <td><input type="radio" name="cond" id="Cond4" value="4" onclick="setCond(this.form)"></td>
        <td><input type="radio" name="cond" id="Cond5" value="5" onclick="setCond(this.form)"></td>
        <td><input type="radio" name="cond" id="Cond6" value="6" onclick="setCond(this.form)"></td>
        <td><input type="radio" name="cond" id="Cond7" value="7" onclick="setCond(this.form)"></td>
        <td><input type="radio" name="cond" id="Cond8" value="8" onclick="setCond(this.form)"></td>
        <td><input type="radio" name="cond" id="Cond9" value="9" onclick="setCond(this.form)"></td>
        <td><input type="radio" name="cond" id="Cond10" value="10" onclick="setCond(this.form)"></td>
        </tr>
    </table>
  </td></tr>
  <tr><td></td><td><span id="CondNew"><fmt:message key="lbl.new" /></span></td></tr>
  <tr><td></td><td>
  	<table style="border-top: 1px solid #ccc;">
    	<tr align="center"><td><span class="small"><fmt:message key="lbl.grade" />:</span></td><td width="40">Disp</td><td width="40">Ref</td><td width="40">Sale</td><td width="40">NIB</td><td width="40">Mint</td></tr>
    	<tr align="center">
        <td></td>
        <td><input type="radio" name="cond" id="Cond11" value="11" onclick="setCond(this.form)"></td>
        <td><input type="radio" name="cond" id="Cond12" value="12" onclick="setCond(this.form)"></td>
        <td><input type="radio" name="cond" id="Cond13" value="13" onclick="setCond(this.form)"></td>
        <td><input type="radio" name="cond" id="Cond14" value="14" onclick="setCond(this.form)"></td>
        <td><input type="radio" name="cond" id="Cond15" value="15" onclick="setCond(this.form)"></td>
        </tr>
    </table>
  </td></tr>
</table>