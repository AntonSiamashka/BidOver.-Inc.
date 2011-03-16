<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bo.main.css" type="text/css">
        <link rel="stylesheet" href="css/jquery.fancybox.css" type="text/css" media="screen">
        <script type="text/JavaScript" src="scripts/auto/variables.jsp" ></script>
        <script type="text/JavaScript" src="scripts/common/variables.jsp" ></script>
        <script type="text/JavaScript" src="scripts/auto/AddAuto.js" > </script>
        <script type="text/JavaScript" src="scripts/auto/AddAutoValidation.js" > </script>
        <script type="text/JavaScript" src="scripts/auto/PreviewAuto.js" > </script>
        <script type="text/JavaScript" src="scripts/common/PreviewLot.js" > </script>
        <script type="text/JavaScript" src="scripts/common/LotDetails.js" > </script>
        <script type="text/javascript" src="js/http.request.js"></script>
        <script type="text/javascript" src="js/lot.cond.js"></script>
        <script type="text/javascript" src="js/lot.payment.js"></script>
        <script type="text/javascript" src="js/lot.shipping.js"></script>
        <script type="text/javascript" src="js/lot.add.js"></script>
        <script type="text/javascript" src="js/lot.location.js"></script>
        <script type="text/javascript" src="js/jquery/jquery.js"></script>
        <script type="text/javascript" src="js/jquery/jquery.fancybox.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $("#cond a").fancybox({
                    frameWidth: 600,
                    frameHeight: 400,
                    overlayShow: false,
                    hideOnContentClick: false
                });
            });
        </script>
        <fmt:setLocale value="${sessionScope.LNG}" />
        <fmt:setBundle basename="Messages"/>
        <title>Add Auto</title>
    </head>

    <body>
        <br><br>
        <table width="100%">
            <tr><td>
                    <table>
                        <tr>
                            <td valign="top"><p class="title"><fmt:message key="lbl.account" />: <%//=userName%> (<fmt:message key="lbl.memberid" /> #<%//=UID%>)</p></td>
                            <td>&nbsp;&nbsp;&nbsp;</td>
                            <td valign="top">
                                <table style="border-top: 1px solid #444">
                                    <tr><td><img src="css/bo.home.024x024.png" border="0" alt=""></td><td valign="middle"><a href="./" class="small"><fmt:message key="btn.home" /></a></td></tr>
                                    <tr><td><img src="css/bo.arrow-left.024x024.png" border="0" alt=""></td><td valign="middle"><a href="./cp.jsp" class="small"><fmt:message key="btn.back" /></a></td></tr>
                                    <tr><td><img src="css/bo.arrow-left.024x024.png" border="0" alt=""></td><td valign="middle"><a href="#" class="small"><fmt:message key="btn.tolotslist" /></a></td></tr>
                                </table>
                            </td>
                        </tr>
                    </table>
            </td></tr>
        </table>

        <table width="100%" cellpadding="5">
            <tr><td width="20%" align="right"><img src='css/bo.add.032x032.png' border='0' alt=""></td><td><p class="smtitle"><fmt:message key="lbl.placelot" /></p></td><td width="10%"></td></tr>
            <tr><td width="20%" align="right"></td>
                <td><table cellpadding="5"><tr>
                            <td id="stepOne"><div class="smtitle"><fmt:message key="lbl.step" /> 1</div><fmt:message key="lbl.description" /></td>
                            <td style="border-right: 1px solid #ccc">&nbsp;&nbsp;&nbsp;</td>
                            <td id="stepTwo" style="color:#ccc"><div class="smtitle"><fmt:message key="lbl.step" /> 2</div><fmt:message key="lbl.revision" /></td>
                            <td style="border-right: 1px solid #ccc">&nbsp;&nbsp;&nbsp;</td>
                            <td id="stepThr" style="color:#ccc"><div class="smtitle"><fmt:message key="lbl.step" /> 3</div><fmt:message key="lbl.photo" /></td>
                    </tr></table>
                </td>
                <td width="10%"></td>
            </tr>
            <tr><td colspan="3">&nbsp;</td></tr>
        </table>

        <form name="add_form" method="POST" action="Controller?command=add_auto">
            <div id="add_form" style="display:block">
                <div id="status" style = "visibility:hidden;">
                    <img name ="status"  src="images/bo.progress_bar.gif" width="109" height="17" alt="bo.progress_bar"/>
                </div>
                <table>
                    <tr>
                        <th scope="col" class="name">Makes</th>
                        <th scope="col">Model</th>
                        <th scope="col">Modification</th>
                    </tr>
                    <tr>
                        <td width="100px">
                            <div id="select_make"></div>
                            <select size="10" name="make" id="make" onchange="getModels()">
                                <c:forEach var="make" items="${makeList}">
                                    <option width="100%" value="${make}" >${make}</option>;
                                </c:forEach>
                            </select>
                        </td>
                        <td width="100px">
                            <div id="select_model"></div>
                        </td>
                        <td width="100px">
                            <div id="select_modification"></div>
                        </td>
                    </tr>
                </table>
                <br/>
                <table width="100%">
                    <tr>
                        <td width="34%" valign="top">
                            <div class="smtitle" style="border-bottom: 2px solid blue">Specification</div>
                            <div id="specification"></div>
                        </td>
                        <td width="33%" valign="top">
                            <div class="smtitle" style="border-bottom: 2px solid green">Options</div>
                            <div id="option_div" style="visibility:hidden;"><input type="button" value="show Options" onclick="showOptions()" name="option" /></div>
                            <div id="hide_option_div" style="visibility:hidden;"><input type="button" value="hide Options" onclick="hideOptions()" name="option" /></div>
                            <div id="select_option" style="visibility:hidden;display:none;">
                                <table>
                                    <tr>
                                        <td><fmt:message key="lbl.pwr_locks" /></td>
                                        <td>
                                            <input type="radio" name="pwr_locks" value="0" checked="true"/>
                                            <input type="radio" name="pwr_locks" value="1"/>
                                            <input type="radio" name="pwr_locks" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.pwr_windows" /></td>
                                        <td>
                                            <input type="radio" name="pwr_windows" value="0" checked="true"/>
                                            <input type="radio" name="pwr_windows" value="1"/>
                                            <input type="radio" name="pwr_windows" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.pwr_steering" /></td>
                                        <td>
                                            <input type="radio" name="pwr_steering" value="0" checked="true"/>
                                            <input type="radio" name="pwr_steering" value="1"/>
                                            <input type="radio" name="pwr_steering" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.pwr_mirrors" /></td>
                                        <td>
                                            <input type="radio" name="pwr_mirrors" value="0" checked="true"/>
                                            <input type="radio" name="pwr_mirrors" value="1"/>
                                            <input type="radio" name="pwr_mirrors" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.pwr_seats" /></td>
                                        <td>
                                            <input type="radio" name="pwr_seats" value="0" checked="true"/>
                                            <input type="radio" name="pwr_seats" value="1"/>
                                            <input type="radio" name="pwr_seats" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.pwr_seats_drivers_only" /></td>
                                        <td>
                                            <input type="radio" name="pwr_seats_drivers_only" value="0" checked="true"/>
                                            <input type="radio" name="pwr_seats_drivers_only" value="1"/>
                                            <input type="radio" name="pwr_seats_drivers_only" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.tilt_steering" /></td>
                                        <td>
                                            <input type="radio" name="tilt_steering" value="0" checked="true"/>
                                            <input type="radio" name="tilt_steering" value="1"/>
                                            <input type="radio" name="tilt_steering" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.air_conditioning" /></td>
                                        <td>
                                            <input type="radio" name="air_conditioning" value="0" checked="true"/>
                                            <input type="radio" name="air_conditioning" value="1"/>
                                            <input type="radio" name="air_conditioning" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.rear_air_conditioning" /></td>
                                        <td>
                                            <input type="radio" name="rear_air_conditioning" value="0" checked="true"/>
                                            <input type="radio" name="rear_air_conditioning" value="1"/>
                                            <input type="radio" name="rear_air_conditioning" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.cruise_ctl" /></td>
                                        <td>
                                            <input type="radio" name="cruise_ctl" value="0" checked="true"/>
                                            <input type="radio" name="cruise_ctl" value="1"/>
                                            <input type="radio" name="cruise_ctl" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.rear_defrost" /></td>
                                        <td>
                                            <input type="radio" name="rear_defrost" value="0" checked="true"/>
                                            <input type="radio" name="rear_defrost" value="1"/>
                                            <input type="radio" name="rear_defrost" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.side_air_bags" /></td>
                                        <td>
                                            <input type="radio" name="side_air_bags" value="0" checked="true"/>
                                            <input type="radio" name="side_air_bags" value="1"/>
                                            <input type="radio" name="side_air_bags" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.dual_air_bags" /></td>
                                        <td>
                                            <input type="radio" name="dual_air_bags" value="0" checked="true"/>
                                            <input type="radio" name="dual_air_bags" value="1"/>
                                            <input type="radio" name="dual_air_bags" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.fog_lamps" /></td>
                                        <td>
                                            <input type="radio" name="fog_lamps" value="0" checked="true"/>
                                            <input type="radio" name="fog_lamps" value="1"/>
                                            <input type="radio" name="fog_lamps" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.trip_counter" /></td>
                                        <td>
                                            <input type="radio" name="trip_counter" value="0" checked="true"/>
                                            <input type="radio" name="trip_counter" value="1"/>
                                            <input type="radio" name="trip_counter" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.luggage_rack" /></td>
                                        <td>
                                            <input type="radio" name="luggage_rack" value="0" checked="true"/>
                                            <input type="radio" name="luggage_rack" value="1"/>
                                            <input type="radio" name="luggage_rack" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.third_row_seat" /></td>
                                        <td>
                                            <input type="radio" name="third_row_seat" value="0" checked="true"/>
                                            <input type="radio" name="third_row_seat" value="1"/>
                                            <input type="radio" name="third_row_seat" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.xenon_headlights" /></td>
                                        <td>
                                            <input type="radio" name="xenon_headlights" value="0" checked="true"/>
                                            <input type="radio" name="xenon_headlights" value="1"/>
                                            <input type="radio" name="xenon_headlights" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.cd_player" /></td>
                                        <td>
                                            <input type="radio" name="cd_player" value="0" checked="true"/>
                                            <input type="radio" name="cd_player" value="1"/>
                                            <input type="radio" name="cd_player" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.cd_changer" /></td>
                                        <td>
                                            <input type="radio" name="cd_changer" value="0" checked="true"/>
                                            <input type="radio" name="cd_changer" value="1"/>
                                            <input type="radio" name="cd_changer" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.rear_wiper" /></td>
                                        <td>
                                            <input type="radio" name="rear_wiper" value="0" checked="true"/>
                                            <input type="radio" name="rear_wiper" value="1"/>
                                            <input type="radio" name="rear_wiper" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.floor_mats" /></td>
                                        <td>
                                            <input type="radio" name="floor_mats" value="0" checked="true"/>
                                            <input type="radio" name="floor_mats" value="1"/>
                                            <input type="radio" name="floor_mats" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.security_system" /></td>
                                        <td>
                                            <input type="radio" name="security_system" value="0" checked="true"/>
                                            <input type="radio" name="security_system" value="1"/>
                                            <input type="radio" name="security_system" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.traction_control" /></td>
                                        <td>
                                            <input type="radio" name="traction_control" value="0" checked="true"/>
                                            <input type="radio" name="traction_control" value="1"/>
                                            <input type="radio" name="traction_control" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.rear_tail" /></td>
                                        <td>
                                            <input type="radio" name="rear_tail" value="0" checked="true"/>
                                            <input type="radio" name="rear_tail" value="1"/>
                                            <input type="radio" name="rear_tail" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.lift_gate" /></td>
                                        <td>
                                            <input type="radio" name="lift_gate" value="0" checked="true"/>
                                            <input type="radio" name="lift_gate" value="1"/>
                                            <input type="radio" name="lift_gate" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.heated_seats" /></td>
                                        <td>
                                            <input type="radio" name="heated_seats" value="0" checked="true"/>
                                            <input type="radio" name="heated_seats" value="1"/>
                                            <input type="radio" name="heated_seats" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.tinted_windows" /></td>
                                        <td>
                                            <input type="radio" name="tinted_windows" value="0" checked="true"/>
                                            <input type="radio" name="tinted_windows" value="1"/>
                                            <input type="radio" name="tinted_windows" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.homelink" /></td>
                                        <td>
                                            <input type="radio" name="homelink" value="0" checked="true"/>
                                            <input type="radio" name="homelink" value="1"/>
                                            <input type="radio" name="homelink" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.tire_pressure_monitor_system" /></td>
                                        <td>
                                            <input type="radio" name="tire_pressure_monitor_system" value="0" checked="true"/>
                                            <input type="radio" name="tire_pressure_monitor_system" value="1"/>
                                            <input type="radio" name="tire_pressure_monitor_system" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.steering_wheel_audio_control" /></td>
                                        <td>
                                            <input type="radio" name="steering_wheel_audio_control" value="0" checked="true"/>
                                            <input type="radio" name="steering_wheel_audio_control" value="1"/>
                                            <input type="radio" name="steering_wheel_audio_control" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.spoiler" /></td>
                                        <td>
                                            <input type="radio" name="spoiler" value="0" checked="true"/>
                                            <input type="radio" name="spoiler" value="1"/>
                                            <input type="radio" name="spoiler" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.bug_deflector" /></td>
                                        <td>
                                            <input type="radio" name="bug_deflector" value="0" checked="true"/>
                                            <input type="radio" name="bug_deflector" value="1"/>
                                            <input type="radio" name="bug_deflector" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.keyless_entry" /></td>
                                        <td>
                                            <input type="radio" name="keyless_entry" value="0" checked="true"/>
                                            <input type="radio" name="keyless_entry" value="1"/>
                                            <input type="radio" name="keyless_entry" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.hand_tool_kit" /></td>
                                        <td>
                                            <input type="radio" name="hand_tool_kit" value="0" checked="true"/>
                                            <input type="radio" name="hand_tool_kit" value="1"/>
                                            <input type="radio" name="hand_tool_kit" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.dual_sliding_side_doors" /></td>
                                        <td>
                                            <input type="radio" name="dual_sliding_side_doors" value="0" checked="true"/>
                                            <input type="radio" name="dual_sliding_side_doors" value="1"/>
                                            <input type="radio" name="dual_sliding_side_doors" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.power_vent_windows" /></td>
                                        <td>
                                            <input type="radio" name="power_vent_windows" value="0" checked="true"/>
                                            <input type="radio" name="power_vent_windows" value="1"/>
                                            <input type="radio" name="power_vent_windows" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.front_center_console" /></td>
                                        <td>
                                            <input type="radio" name="front_center_console" value="0" checked="true"/>
                                            <input type="radio" name="front_center_console" value="1"/>
                                            <input type="radio" name="front_center_console" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.stow_and_go" /></td>
                                        <td>
                                            <input type="radio" name="stow_and_go" value="0" checked="true"/>
                                            <input type="radio" name="stow_and_go" value="1"/>
                                            <input type="radio" name="stow_and_go" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.stow_and_go_3rd_row_only" /></td>
                                        <td>
                                            <input type="radio" name="stow_and_go_3rd_row_only" value="0" checked="true"/>
                                            <input type="radio" name="stow_and_go_3rd_row_only" value="1"/>
                                            <input type="radio" name="stow_and_go_3rd_row_only" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.wood_trim_dash" /></td>
                                        <td>
                                            <input type="radio" name="wood_trim_dash" value="0" checked="true"/>
                                            <input type="radio" name="wood_trim_dash" value="1"/>
                                            <input type="radio" name="wood_trim_dash" value="2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.woodgrain_interior_package" /></td>
                                        <td>
                                            <input type="radio" name="woodgrain_interior_package" value="0" checked="true"/>
                                            <input type="radio" name="woodgrain_interior_package" value="1"/>
                                            <input type="radio" name="woodgrain_interior_package" value="2"/>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </td>
                        <td width="33%" valign="top">
                            <div class="smtitle" style="border-bottom: 2px solid red">Damages</div>
                            <div id="damage_div" style="visibility:hidden;"><input type="button" value="show Damages" onclick="showDamages()" name="option" /></div>
                            <div id="hide_damage_div" style="visibility:hidden;"><input type="button" value="hide Damages" onclick="hideDamages()" name="option" /></div>
                            <div id="select_damage" style="visibility:hidden;display:none;"></div>
                        </td>
                    </tr>
                </table>
                <br/>
                <div class="smtitle" style="border-bottom: 2px solid blue;">Sales Details</div>
                <div id="sales_details" style="display:none;">
                    <%@include file="salesDetails.jspf" %>
                </div>

                <div  id="preview_div" style="visibility:hidden">
                </div>

            </div>

            <div id="preview_form" style="display:none">
            </div>

            <div id="add_div" style="visibility:hidden">
                <table width="100%" cellpadding="5">
                    <tr><td colspan="3">&nbsp;</td></tr>
                    <tr><td width="20%" style="border-top: 1px solid #ccc">&nbsp;</td><td><input id="Reverse" type="button" value='<fmt:message key="btn.retmod" />' onclick="showAddForm();"/> <input type="submit" name="add_lot" value='<fmt:message key="btn.continue" />'/>&nbsp;<span id="progress_indicator_lot" style="visibility:hidden"><img src="css/bo.progress_bar.gif" alt=""></span></td><td width="10%"></td></tr>
                    <tr><td colspan="3">&nbsp;</td></tr>
                </table>
            </div>

        </form>

        <%@include file="footer.jspf" %>

    </body>
</html>
