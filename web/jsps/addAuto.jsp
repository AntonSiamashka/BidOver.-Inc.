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

        <form name="add_form" method="POST" action="Controller.do?command=add_auto">
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
                            <select size="10" name="make" id="make" onchange="getModels()">
                                <c:forEach var="make" items="${makeList}">
                                    <option value="${make}">${make}</option>
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
                            <div id="specification" style="visibility: hidden; display: none;">
                                <table>
                                    <tr>
                                        <td align="right">Year:</td>
                                        <td>
                                            <select name="year" id="year">
                                            
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">Body Style:</td>
                                        <td>
                                            <select name="body-style" id="body-style">
                                                <option width="100%" value=""><fmt:message key="lbl.none"/></option>
                                                <c:forEach items="${bodyStyles}" var="bodyStyle">
                                                    <option width="100%" value="${bodyStyle.id}">${bodyStyle.title}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">Drive Train:</td>
                                        <td>
                                            <select name="drive-train" id="drive-train">
                                                <option width="100%" value=""><fmt:message key="lbl.none"/></option>
                                                <c:forEach items="${driveTrains}" var="driveTrain">
                                                    <option width="100%" value="${driveTrain.id}">${driveTrain.title}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">Exterior Color:</td>
                                        <td>
                                            <select name="exterior-color" id="exterior-color">
                                                <option width="100%" value=""><fmt:message key="lbl.none"/></option>
                                                <c:forEach items="${exteriorColors}" var="exteriorColor">
                                                    <option width="100%" value="${exteriorColor.id}">${exteriorColor.title}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">Interior Color:</td>
                                        <td>
                                            <select name="interior-color" id="interior-color">
                                                <option width="100%" value=""><fmt:message key="lbl.none"/></option>
                                                <c:forEach items="${interiorColors}" var="interiorColor">
                                                    <option width="100%" value="${interiorColor.id}">${interiorColor.title}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">Fuel:</td>
                                        <td>
                                            <select name="fuel" id="fuel">
                                                <option width="100%" value=""><fmt:message key="lbl.none"/></option>
                                                <c:forEach items="${fuels}" var="fuel">
                                                    <option width="100%" value="${fuel.id}">${fuel.title}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">Tires:</td>
                                        <td>
                                            <select name="tires" id="tires">
                                                <option width="100%" value=""><fmt:message key="lbl.none"/></option>
                                                <c:forEach items="${tires}" var="tire">
                                                    <option width="100%" value="${tire.id}">${tire.title}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">Top Type:</td>
                                        <td>
                                            <select name="top-type" id="top-type">
                                                <option width="100%" value=""><fmt:message key="lbl.none"/></option>
                                                <c:forEach items="${topTypes}" var="topType">
                                                    <option width="100%" value="${topType.id}">${topType.title}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">Wheels:</td>
                                        <td>
                                            <select name="wheels" id="wheels">
                                                <option width="100%" value=""><fmt:message key="lbl.none"/></option>
                                                <c:forEach items="${wheelsList}" var="wheel">
                                                    <option width="100%" value="${wheel.id}">${wheel.title}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">Doors:</td>
                                        <td>
                                            <select name="door" id="door">
                                                <option width="100%" value=""><fmt:message key="lbl.none"/></option>
                                                <c:forEach items="${doors}" var="door">
                                                    <option width="100%" value="${door.id}">${door.title}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">Engine:</td>
                                        <td>
                                            <select name="engine" id="engine">
                                                <option width="100%" value=""><fmt:message key="lbl.none"/></option>
                                                <c:forEach items="${engines}" var="engine">
                                                    <option width="100%" value="${engine.id}">${engine.title}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">Interior Type:</td>
                                        <td>
                                            <select name="interior-type" id="interior-type">
                                                <option width="100%" value=""><fmt:message key="lbl.none"/></option>
                                                <c:forEach items="${interiorTypes}" var="interiorType">
                                                    <option width="100%" value="${interiorType.id}">${interiorType.title}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">Transmission:</td>
                                        <td>
                                            <select name="transmission" id="transmission">
                                                <option width="100%" value=""><fmt:message key="lbl.none"/></option>
                                                <c:forEach items="${transmissions}" var="transmission">
                                                    <option width="100%" value="${transmission.id}">${transmission.title}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">VIN:</td>
                                        <td>
                                            <input type="text" id="vin" name="vin" value="12345678912345678"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">Odometer:</td>
                                        <td>
                                            <input type="text" id="odometer" name="odometer" value="1000"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">Displacement</td>
                                        <td>
                                            <input type="text" id="displacement" name="displacement" value="1000"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">Salvage:</td>
                                        <td>
                                            <input type="checkbox" id="salvage" name="salvage"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">Country Of Assembley:</td>
                                        <td>
                                            <select name="country_assembly" id="country_assembly">
                                                <option width="100%" value=""><fmt:message key="lbl.none"/></option>
                                                <c:forEach items="${countryAssembleys}" var="countryAssembley">
                                                    <option width="100%" value="${countryAssembley.id}">${countryAssembley.title}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                </table>
                            </div>
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
                                            <input type="checkbox" name="pwr_locks" id="pwr_locks"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.pwr_windows" /></td>
                                        <td>
                                            <input type="checkbox" name="pwr_windows" id="pwr_windows"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.pwr_steering" /></td>
                                        <td>
                                            <input type="checkbox" name="pwr_steering" id="pwr_steering"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.pwr_mirrors" /></td>
                                        <td>
                                            <input type="checkbox" name="pwr_mirrors" id="pwr_mirrors"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.pwr_seats" /></td>
                                        <td>
                                            <input type="checkbox" name="pwr_seats" id="pwr_seats"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.pwr_seats_drivers_only" /></td>
                                        <td>
                                            <input type="checkbox" name="pwr_seats_drivers_only" id="pwr_seats_drivers_only"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.tilt_steering" /></td>
                                        <td>
                                            <input type="checkbox" name="tilt_steering" id="tilt_steering"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.air_conditioning" /></td>
                                        <td>
                                            <input type="checkbox" name="air_conditioning" id="air_conditioning"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.rear_air_conditioning" /></td>
                                        <td>
                                            <input type="checkbox" name="rear_air_conditioning" id="rear_air_conditioning"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.cruise_ctl" /></td>
                                        <td>
                                            <input type="checkbox" name="cruise_ctl" id="cruise_ctl"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.rear_defrost" /></td>
                                        <td>
                                            <input type="checkbox" name="rear_defrost" id="rear_defrost"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.side_air_bags" /></td>
                                        <td>
                                            <input type="checkbox" name="side_air_bags" id="side_air_bags"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.dual_air_bags" /></td>
                                        <td>
                                            <input type="checkbox" name="dual_air_bags" id="dual_air_bags"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.fog_lamps" /></td>
                                        <td>
                                            <input type="checkbox" name="fog_lamps" id="fog_lamps"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.trip_counter" /></td>
                                        <td>
                                            <input type="checkbox" name="trip_counter" id="trip_counter"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.luggage_rack" /></td>
                                        <td>
                                            <input type="checkbox" name="luggage_rack" id="luggage_rack"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.third_row_seat" /></td>
                                        <td>
                                            <input type="checkbox" name="third_row_seat" id="third_row_seat"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.xenon_headlights" /></td>
                                        <td>
                                            <input type="checkbox" name="xenon_headlights" id="xenon_headlights"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.cd_player" /></td>
                                        <td>
                                            <input type="checkbox" name="cd_player" id="cd_player"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.cd_changer" /></td>
                                        <td>
                                            <input type="checkbox" name="cd_changer" id="cd_changer"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.rear_wiper" /></td>
                                        <td>
                                            <input type="checkbox" name="rear_wiper" id="rear_wiper"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.floor_mats" /></td>
                                        <td>
                                            <input type="checkbox" name="floor_mats" id="floor_mats"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.security_system" /></td>
                                        <td>
                                            <input type="checkbox" name="security_system" id="security_system"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.traction_control" /></td>
                                        <td>
                                            <input type="checkbox" name="traction_control" id="traction_control"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.rear_tail" /></td>
                                        <td>
                                            <input type="checkbox" name="rear_tail" id="rear_tail"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.lift_gate" /></td>
                                        <td>
                                            <input type="checkbox" name="lift_gate" id="lift_gate"/>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.heated_seats" /></td>
                                        <td>
                                            <input type="checkbox" name="heated_seats" id="heated_seats"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.tinted_windows" /></td>
                                        <td>
                                            <input type="checkbox" name="tinted_windows" id="tinted_windows"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.homelink" /></td>
                                        <td>
                                            <input type="checkbox" name="homelink" id="homelink"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.tire_pressure_monitor_system" /></td>
                                        <td>
                                            <input type="checkbox" name="tire_pressure_monitor_system" id="tire_pressure_monitor_system"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.steering_wheel_audio_control" /></td>
                                        <td>
                                            <input type="checkbox" name="steering_wheel_audio_control" id="steering_wheel_audio_control"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.spoiler" /></td>
                                        <td>
                                            <input type="checkbox" name="spoiler" id="spoiler"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.bug_deflector" /></td>
                                        <td>
                                            <input type="checkbox" name="bug_deflector" id="bug_deflector"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.keyless_entry" /></td>
                                        <td>
                                            <input type="checkbox" name="keyless_entry" id="keyless_entry"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.hand_tool_kit" /></td>
                                        <td>
                                            <input type="checkbox" name="hand_tool_kit" id="hand_tool_kit"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.dual_sliding_side_doors" /></td>
                                        <td>
                                            <input type="checkbox" name="dual_sliding_side_doors" id="dual_sliding_side_doors"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.power_vent_windows" /></td>
                                        <td>
                                            <input type="checkbox" name="power_vent_windows" id="power_vent_windows"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.front_center_console" /></td>
                                        <td>
                                            <input type="checkbox" name="front_center_console" id="front_center_console"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.stow_and_go" /></td>
                                        <td>
                                            <input type="checkbox" name="stow_and_go" id="stow_and_go"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.stow_and_go_3rd_row_only" /></td>
                                        <td>
                                            <input type="checkbox" name="stow_and_go_3rd_row_only" id="stow_and_go_3rd_row_only"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.wood_trim_dash" /></td>
                                        <td>
                                            <input type="checkbox" name="wood_trim_dash" id="wood_trim_dash"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.woodgrain_interior_package" /></td>
                                        <td>
                                            <input type="checkbox" name="woodgrain_interior_package" id="woodgrain_interior_package"/>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </td>
                        <td width="33%" valign="top">
                            <div class="smtitle" style="border-bottom: 2px solid red">Damages</div>
                            <div id="damage_div" style="visibility:hidden;"><input type="button" value="show Damages" onclick="showDamages()" name="option" /></div>
                            <div id="hide_damage_div" style="visibility:hidden;"><input type="button" value="hide Damages" onclick="hideDamages()" name="option" /></div>
                            <div id="select_damage" style="visibility:hidden;display:none;">
                                <table>
                                    <tr>
                                        <td><fmt:message key="lbl.hood"/></td>
                                        <td>
                                            <select name="hood" id="hood">
                                                <option width="100%" value=""><fmt:message key="lbl.none"/></option>
                                                <c:forEach items="${damageConditions}" var="damageCondition">
                                                    <option width="100%" value="${damageCondition.id}">${damageCondition.title}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.roof"/></td>
                                        <td>
                                            <select name="roof" id="roof">
                                                <option width="100%" value=""><fmt:message key="lbl.none"/></option>
                                                <c:forEach items="${damageConditions}" var="damageCondition">
                                                    <option width="100%" value="${damageCondition.id}">${damageCondition.title}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.windshield"/></td>
                                        <td>
                                            <select name="windshield" id="windshield">
                                                <option width="100%" value=""><fmt:message key="lbl.none"/></option>
                                                <c:forEach items="${damageConditions}" var="damageCondition">
                                                    <option width="100%" value="${damageCondition.id}">${damageCondition.title}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.lf_door"/></td>
                                        <td>
                                            <select name="lf_door" id="lf_door">
                                                <option width="100%" value=""><fmt:message key="lbl.none"/></option>
                                                <c:forEach items="${damageConditions}" var="damageCondition">
                                                    <option width="100%" value="${damageCondition.id}">${damageCondition.title}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.lr_door"/></td>
                                        <td>
                                            <select name="lr_door" id="lr_door">
                                                <option width="100%" value=""><fmt:message key="lbl.none"/></option>
                                                <c:forEach items="${damageConditions}" var="damageCondition">
                                                    <option width="100%" value="${damageCondition.id}">${damageCondition.title}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.rf_door"/></td>
                                        <td>
                                            <select name="rf_door" id="rf_door">
                                                <option width="100%" value=""><fmt:message key="lbl.none"/></option>
                                                <c:forEach items="${damageConditions}" var="damageCondition">
                                                    <option width="100%" value="${damageCondition.id}">${damageCondition.title}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.rr_door"/></td>
                                        <td>
                                            <select name="rr_door" id="rr_door">
                                                <option width="100%" value=""><fmt:message key="lbl.none"/></option>
                                                <c:forEach items="${damageConditions}" var="damageCondition">
                                                    <option width="100%" value="${damageCondition.id}">${damageCondition.title}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.l_qtr_panel"/></td>
                                        <td>
                                            <select name="l_qtr_panel" id="l_qtr_panel">
                                                <option width="100%" value=""><fmt:message key="lbl.none"/></option>
                                                <c:forEach items="${damageConditions}" var="damageCondition">
                                                    <option width="100%" value="${damageCondition.id}">${damageCondition.title}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.r_qtr_panel"/></td>
                                        <td>
                                            <select name="r_qtr_panel" id="r_qtr_panel">
                                                <option width="100%" value=""><fmt:message key="lbl.none"/></option>
                                                <c:forEach items="${damageConditions}" var="damageCondition">
                                                    <option width="100%" value="${damageCondition.id}">${damageCondition.title}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.front_bumper"/></td>
                                        <td>
                                            <select name="front_bumper" id="front_bumper">
                                                <option width="100%" value=""><fmt:message key="lbl.none"/></option>
                                                <c:forEach items="${damageConditions}" var="damageCondition">
                                                    <option width="100%" value="${damageCondition.id}">${damageCondition.title}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.rear_bumper"/></td>
                                        <td>
                                            <select name="rear_bumper" id="rear_bumper">
                                                <option width="100%" value=""><fmt:message key="lbl.none"/></option>
                                                <c:forEach items="${damageConditions}" var="damageCondition">
                                                    <option width="100%" value="${damageCondition.id}">${damageCondition.title}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.lf_fender"/></td>
                                        <td>
                                            <select name="lf_fender" id="lf_fender">
                                                <option width="100%" value=""><fmt:message key="lbl.none"/></option>
                                                <c:forEach items="${damageConditions}" var="damageCondition">
                                                    <option width="100%" value="${damageCondition.id}">${damageCondition.title}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.rf_fender"/></td>
                                        <td>
                                            <select name="rf_fender" id="rf_fender">
                                                <option width="100%" value=""><fmt:message key="lbl.none"/></option>
                                                <c:forEach items="${damageConditions}" var="damageCondition">
                                                    <option width="100%" value="${damageCondition.id}">${damageCondition.title}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.deck_lid"/></td>
                                        <td>
                                            <select name="deck_lid" id="deck_lid">
                                                <option width="100%" value=""><fmt:message key="lbl.none"/></option>
                                                <c:forEach items="${damageConditions}" var="damageCondition">
                                                    <option width="100%" value="${damageCondition.id}">${damageCondition.title}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.seats"/></td>
                                        <td>
                                            <select name="seats" id="seats">
                                                <option width="100%" value=""><fmt:message key="lbl.none"/></option>
                                                <c:forEach items="${damageConditions}" var="damageCondition">
                                                    <option width="100%" value="${damageCondition.id}">${damageCondition.title}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="lbl.overall_vehicle"/></td>
                                        <td>
                                            <select name="overall_vehicle" id="overall_vehicle">
                                                <option width="100%" value=""><fmt:message key="lbl.none"/></option>
                                                <c:forEach items="${damageConditions}" var="damageCondition">
                                                    <option width="100%" value="${damageCondition.id}">${damageCondition.title}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                </table>
                            </div>
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
