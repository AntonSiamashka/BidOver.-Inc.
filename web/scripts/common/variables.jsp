<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.LNG}" />
<fmt:setBundle basename="Messages"/>
var lbl_cond='<fmt:message key="lbl.cond" />';
var lbl_sales_duration='<fmt:message key="lbl.sales_duration" />';
var lbl_country_code='<fmt:message key="lbl.country_code" />';
var lbl_atd_code='<fmt:message key="lbl.atd_code" />';
var lbl_location_code='<fmt:message key="lbl.location_code" />';
var lbl_forbid_bidding='<fmt:message key="lbl.forbid_bidding" />';
var lbl_fixed_price='<fmt:message key="lbl.fixed_price" />';
var lbl_fixed_price_only='<fmt:message key="lbl.fixed_price_only" />';
var lbl_qnt_items_available='<fmt:message key="lbl.qnt_items_available" />';
var lbl_floor_price='<fmt:message key="lbl.floor_price" />';
var lbl_starting_bid='<fmt:message key="lbl.starting_bid" />';
var lbl_currency_code='<fmt:message key="lbl.currency_code" />';
var lbl_pmntpref='<fmt:message key="lbl.pmntpref" />';
var lbl_pmntmethod='<fmt:message key="lbl.pmntmethod" />';
var lbl_payment_instructions='<fmt:message key="lbl.payment_instructions" />';
var lbl_handling_time='<fmt:message key="lbl.handling_time" />';
var lbl_description='<fmt:message key="lbl.description" />';
var lbl_shipping='<fmt:message key="lbl.shipping" />';
var lbl_lot_not_allowed='<fmt:message key="lbl.lot.not_allowed" />';
var lbl_lot_yes='<fmt:message key="lbl.lot.yes" />';
var lbl_lot_no='<fmt:message key="lbl.lot.no" />';
var msg_lot_location_code='<fmt:message key="msg.lot.location_code" />';
var msg_lot_fixed_price='<fmt:message key="msg.lot.fixed_price" />';
var msg_lot_floor_price='<fmt:message key="msg.lot.floor_price" />';
var msg_lot_starting_bid='<fmt:message key="msg.lot.starting_bid" />';
var msg_lot_payment_instructions='<fmt:message key="msg.lot.starting_bid" />';
var msg_lot_description='<fmt:message key="msg.lot.description" />';