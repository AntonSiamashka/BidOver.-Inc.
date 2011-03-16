function showPreviewAdd(){
    var regNum=/^\d+$/;
    var errorFlag = false;
    var messageDiv = "message-div";
    cleanPreview("message-div");
    var previewForm = document.getElementById("preview_form");
    cleanPreview("preview_form");
    var vin = getInputValue("vin");
    var odometer = getInputValue("odometer");
    if (!regNum.test(vin)) {
        addTextInDiv(messageDiv,msg_auto_vin);
        errorFlag = true;
    }
    if (!regNum.test(odometer)) {
        addTextInDiv(messageDiv,msg_auto_odometer);
        errorFlag = true;
    }
    var previewLot = showPreviewLot();
    if(previewLot==null){
        errorFlag = true;
    }
    if(!errorFlag){
        previewForm.style.visibility = "visible";
        previewForm.style.display = "block";
        var addDiv = document.getElementById("add_div");
        addDiv.style.visibility = "visible";
        addDiv.style.display = "block";
        var addForm = document.getElementById("add_form");
        addForm.style.visibility = "hidden";
        addForm.style.display = "none";
        document.getElementById("stepOne").style.color="#ccc";
        document.getElementById("stepTwo").style.color="#444";
        var make = getSelectedOptionChild("make");
        var model = getSelectedOptionChild("model");
        var trim = getSelectedOptionChild("trim");
        var engine = getSelectedOptionChild("engine");
        var modification = getSelectedOptionChild("modification");
        var year = getSelectedOptionChild("year");
        var body_style = getSelectedOptionChild("body-style");
        var door = getSelectedOptionChild("door");
        var drive_train = getSelectedOptionChild("drive-train");
        var exterior_color = getSelectedOptionChild("exterior-color");
        var interior_color = getSelectedOptionChild("interior-color");
        var fuel = getSelectedOptionChild("fuel");
        var tires = getSelectedOptionChild("tires");
        var top_type = getSelectedOptionChild("top-type");
        var wheels = getSelectedOptionChild("wheels");
        var transmission = getSelectedOptionChild("transmission");
        var interior_type = getSelectedOptionChild("interior-type");
        var hood = getSelectedOptionChild("hood");
        var roof = getSelectedOptionChild("roof");
        var windshield = getSelectedOptionChild("windshield");
        var lf_door = getSelectedOptionChild("lf_door");
        var lr_door = getSelectedOptionChild("lr_door");
        var rf_door = getSelectedOptionChild("rf_door");
        var rr_door = getSelectedOptionChild("rr_door");
        var l_qtr_panel = getSelectedOptionChild("l_qtr_panel");
        var r_qtr_panel = getSelectedOptionChild("r_qtr_panel");
        var front_bumper = getSelectedOptionChild("front_bumper");
        var rear_bumper = getSelectedOptionChild("rear_bumper");
        var lf_fender = getSelectedOptionChild("lf_fender");
        var rf_fender = getSelectedOptionChild("rf_fender");
        var deck_lid = getSelectedOptionChild("deck_lid");
        var seats = getSelectedOptionChild("seats");

        var pwr_locks = createAutoOptionsLabel(getSelectedRadioValue("pwr_locks"));
        var pwr_windows = createAutoOptionsLabel(getSelectedRadioValue("pwr_windows"));
        var pwr_mirrors = createAutoOptionsLabel(getSelectedRadioValue("pwr_mirrors"));
        var pwr_seats = createAutoOptionsLabel(getSelectedRadioValue("pwr_seats"));
        var pwr_seats_drivers_only = createAutoOptionsLabel(getSelectedRadioValue("pwr_seats_drivers_only"));
        var tilt_steering = createAutoOptionsLabel(getSelectedRadioValue("tilt_steering"));
        var air_conditioning = createAutoOptionsLabel(getSelectedRadioValue("air_conditioning"));
        var rear_air_conditioning = createAutoOptionsLabel(getSelectedRadioValue("rear_air_conditioning"));
        var cruise_ctl = createAutoOptionsLabel(getSelectedRadioValue("cruise_ctl"));
        var rear_defrost = createAutoOptionsLabel(getSelectedRadioValue("rear_defrost"));
        var side_air_bags = createAutoOptionsLabel(getSelectedRadioValue("side_air_bags"));
        var dual_air_bags = createAutoOptionsLabel(getSelectedRadioValue("dual_air_bags"));
        var fog_lamps = createAutoOptionsLabel(getSelectedRadioValue("fog_lamps"));
        var trip_counter = createAutoOptionsLabel(getSelectedRadioValue("trip_counter"));
        var luggage_rack = createAutoOptionsLabel(getSelectedRadioValue("luggage_rack"));
        var third_row_seat = createAutoOptionsLabel(getSelectedRadioValue("third_row_seat"));
        var xenon_headlights = createAutoOptionsLabel(getSelectedRadioValue("xenon_headlights"));
        var cd_player = createAutoOptionsLabel(getSelectedRadioValue("cd_player"));
        var cd_changer = createAutoOptionsLabel(getSelectedRadioValue("cd_changer"));
        var rear_wiper = createAutoOptionsLabel(getSelectedRadioValue("rear_wiper"));
        var floor_mats = createAutoOptionsLabel(getSelectedRadioValue("floor_mats"));
        var security_system = createAutoOptionsLabel(getSelectedRadioValue("security_system"));
        var traction_control = createAutoOptionsLabel(getSelectedRadioValue("traction_control"));
        var rear_tail = createAutoOptionsLabel(getSelectedRadioValue("rear_tail"));
        var lift_gate = createAutoOptionsLabel(getSelectedRadioValue("lift_gate"));
        var heated_seats = createAutoOptionsLabel(getSelectedRadioValue("heated_seats"));
        var tinted_windows = createAutoOptionsLabel(getSelectedRadioValue("tinted_windows"));
        var homelink = createAutoOptionsLabel(getSelectedRadioValue("homelink"));
        var tire_pressure_monitor_system = createAutoOptionsLabel(getSelectedRadioValue("tire_pressure_monitor_system"));
        var steering_wheel_audio_control = createAutoOptionsLabel(getSelectedRadioValue("steering_wheel_audio_control"));
        var spoiler = createAutoOptionsLabel(getSelectedRadioValue("spoiler"));
        var bug_deflector = createAutoOptionsLabel(getSelectedRadioValue("bug_deflector"));
        var keyless_entry = createAutoOptionsLabel(getSelectedRadioValue("keyless_entry"));
        var hand_tool_kit = createAutoOptionsLabel(getSelectedRadioValue("hand_tool_kit"));
        var dual_sliding_side_doors = createAutoOptionsLabel(getSelectedRadioValue("dual_sliding_side_doors"));
        var power_vent_windows = createAutoOptionsLabel(getSelectedRadioValue("power_vent_windows"));
        var front_center_console = createAutoOptionsLabel(getSelectedRadioValue("front_center_console"));
        var stow_and_go = createAutoOptionsLabel(getSelectedRadioValue("stow_and_go"));
        var stow_and_go_3rd_row_only = createAutoOptionsLabel(getSelectedRadioValue("stow_and_go_3rd_row_only"));
        var wood_trim_dash = createAutoOptionsLabel(getSelectedRadioValue("wood_trim_dash"));
        var woodgrain_interior_package = createAutoOptionsLabel(getSelectedRadioValue("woodgrain_interior_package"));
        var pwr_steering = createAutoOptionsLabel(getSelectedRadioValue("pwr_steering"));
    
        var table = document.createElement("table");
        inserTr(table,lbl_make,document.createTextNode(make));
        inserTr(table,lbl_model,document.createTextNode(model));
        inserTr(table,lbl_modification,document.createTextNode(modification));
        inserTr(table,lbl_year,document.createTextNode(year));
        inserTr(table,lbl_trim,document.createTextNode(trim));
        inserTr(table,lbl_engine,document.createTextNode(engine));
        inserTr(table,lbl_odometer,document.createTextNode(odometer));
        inserTr(table,lbl_body_style,document.createTextNode(body_style));
        inserTr(table,lbl_door,document.createTextNode(door));
        inserTr(table,lbl_drive_train,document.createTextNode(drive_train));
        inserTr(table,lbl_exterior_color,document.createTextNode(exterior_color));
        inserTr(table,lbl_interior_color,document.createTextNode(interior_color));
        inserTr(table,lbl_interior_type,document.createTextNode(interior_type));
        inserTr(table,lbl_fuel,document.createTextNode(fuel));
        inserTr(table,lbl_tires,document.createTextNode(tires));
        inserTr(table,lbl_top_type,document.createTextNode(top_type));
        inserTr(table,lbl_wheels,document.createTextNode(wheels));
        inserTr(table,lbl_transmission,document.createTextNode(transmission));
        inserTr(table,lbl_vin,document.createTextNode(vin));
        inserTr(table,lbl_hood,document.createTextNode(hood));
        inserTr(table,lbl_roof,document.createTextNode(roof));
        inserTr(table,lbl_windshield,document.createTextNode(windshield));
        inserTr(table,lbl_lf_door,document.createTextNode(lf_door));
        inserTr(table,lbl_lr_door,document.createTextNode(lr_door));
        inserTr(table,lbl_rf_door,document.createTextNode(rf_door));
        inserTr(table,lbl_rr_door,document.createTextNode(rr_door));
        inserTr(table,lbl_l_qtr_panel,document.createTextNode(l_qtr_panel));
        inserTr(table,lbl_r_qtr_panel,document.createTextNode(r_qtr_panel));
        inserTr(table,lbl_front_bumper,document.createTextNode(front_bumper));
        inserTr(table,lbl_rear_bumper,document.createTextNode(rear_bumper));
        inserTr(table,lbl_lf_fender,document.createTextNode(lf_fender));
        inserTr(table,lbl_rf_fender,document.createTextNode(rf_fender));
        inserTr(table,lbl_deck_lid,document.createTextNode(deck_lid));
        inserTr(table,lbl_seats,document.createTextNode(seats));
        inserTr(table,lbl_pwr_locks,document.createTextNode(pwr_locks));
        inserTr(table,lbl_pwr_windows,document.createTextNode(pwr_windows));
        inserTr(table,lbl_pwr_mirrors,document.createTextNode(pwr_mirrors));
        inserTr(table,lbl_pwr_seats,document.createTextNode(pwr_seats));
        inserTr(table,lbl_pwr_seats_drivers_only,document.createTextNode(pwr_seats_drivers_only));
        inserTr(table,lbl_tilt_steering,document.createTextNode(tilt_steering));
        inserTr(table,lbl_air_conditioning,document.createTextNode(air_conditioning));
        inserTr(table,lbl_rear_air_conditioning,document.createTextNode(rear_air_conditioning));
        inserTr(table,lbl_cruise_ctl,document.createTextNode(cruise_ctl));
        inserTr(table,lbl_rear_defrost,document.createTextNode(rear_defrost));
        inserTr(table,lbl_side_air_bags,document.createTextNode(side_air_bags));
        inserTr(table,lbl_dual_air_bags,document.createTextNode(dual_air_bags));
        inserTr(table,lbl_fog_lamps,document.createTextNode(fog_lamps));
        inserTr(table,lbl_trip_counter,document.createTextNode(trip_counter));
        inserTr(table,lbl_luggage_rack,document.createTextNode(luggage_rack));
        inserTr(table,lbl_third_row_seat,document.createTextNode(third_row_seat));
        inserTr(table,lbl_xenon_headlights,document.createTextNode(xenon_headlights));
        inserTr(table,lbl_cd_player,document.createTextNode(cd_player));
        inserTr(table,lbl_cd_changer,document.createTextNode(cd_changer));
        inserTr(table,lbl_rear_wiper,document.createTextNode(rear_wiper));
        inserTr(table,lbl_floor_mats,document.createTextNode(floor_mats));
        inserTr(table,lbl_security_system,document.createTextNode(security_system));
        inserTr(table,lbl_traction_control,document.createTextNode(traction_control));
        inserTr(table,lbl_rear_tail,document.createTextNode(rear_tail));
        inserTr(table,lbl_lift_gate,document.createTextNode(lift_gate));
        inserTr(table,lbl_heated_seats,document.createTextNode(heated_seats));
        inserTr(table,lbl_tinted_windows,document.createTextNode(tinted_windows));
        inserTr(table,lbl_homelink,document.createTextNode(homelink));
        inserTr(table,lbl_tire_pressure_monitor_system,document.createTextNode(tire_pressure_monitor_system));
        inserTr(table,lbl_steering_wheel_audio_control,document.createTextNode(steering_wheel_audio_control));
        inserTr(table,lbl_spoiler,document.createTextNode(spoiler));
        inserTr(table,lbl_bug_deflector,document.createTextNode(bug_deflector));
        inserTr(table,lbl_keyless_entry,document.createTextNode(keyless_entry));
        inserTr(table,lbl_hand_tool_kit,document.createTextNode(hand_tool_kit));
        inserTr(table,lbl_dual_sliding_side_doors,document.createTextNode(dual_sliding_side_doors));
        inserTr(table,lbl_power_vent_windows,document.createTextNode(power_vent_windows));
        inserTr(table,lbl_front_center_console,document.createTextNode(front_center_console));
        inserTr(table,lbl_stow_and_go,document.createTextNode(stow_and_go));
        inserTr(table,lbl_stow_and_go_3rd_row_only,document.createTextNode(stow_and_go_3rd_row_only));
        inserTr(table,lbl_wood_trim_dash,document.createTextNode(wood_trim_dash));
        inserTr(table,lbl_woodgrain_interior_package,document.createTextNode(woodgrain_interior_package));
        inserTr(table,lbl_pwr_steering,document.createTextNode(pwr_steering));
        previewForm.appendChild(table);
        previewForm.appendChild(previewLot);
    }
}

function showAddForm(){

    document.getElementById("stepOne").style.color="#444";
    document.getElementById("stepTwo").style.color="#ccc";

    var previewForm = document.getElementById("preview_form");
    previewForm.style.visibility = "hidden";
    previewForm.style.display = "none";

    var addDiv = document.getElementById("add_div");
    addDiv.style.visibility = "hidden";
    addDiv.style.display = "none";

    var addForm = document.getElementById("add_form");
    addForm.style.visibility = "visible";
    addForm.style.display = "block";
}

function createAutoOptionsLabel(value){
    var title="";
    if(value=='0'){title="Не указано";}
    if(value=='1'){title="Да";}
    if(value=='2'){title="Нет";}
    return title;
}

function getSelectedOptionChild(selectName){
    var child;
    var select = document.getElementById(selectName);
    if(select!=null){
        var id = select.value;
        var items = select.childNodes;
        for (var I = 0 ; I < items.length ; I++) {
            var item = items[I];
            var value = item.getAttribute("value");
            if(value==id){
                child = item.firstChild.nodeValue;
            }
        }
    }
    return child;
}

function getSelectedRadioValue(selectName){
    var value=0;
    var radio = document.getElementsByName(selectName);
    if(radio!=null){
        var length = radio.length;
        for (var I = 0 ; I < length ; I++) {
            var item = radio[I];
            if(item.checked){
                value=item.value;
                break;
            }
        }
    }
    return value;
}
function getSelectedCheckboxValue(selectName){
    var value="";
    var radio = document.getElementsByName(selectName);
    if(radio!=null){
        var length = radio.length;
        for (var I = 0 ; I < length ; I++) {
            var item = radio[I];
            if(item.checked){
                value=value+item.value + " ";
            }
        }
    }
    return value;
}


function getInputValue(name){
    var value;
    var input = document.getElementById(name);
    if(input!=null){
        value = input.value;
    }
    return value;
}

function cleanPreview(name){
    var previewTable = document.getElementById(name);
    var items = previewTable.childNodes;
    for (var I = items.length ; 0 < I ; I--) {
        previewTable.removeChild(items[I-1]);
    }
}

function addTextInDiv(id,text){
    var div = document.getElementById(id);
    var currentText="";
    var child=div.firstChild;
    if(child!=null){
        currentText=child.nodeValue;
    }
    div.appendChild(document.createTextNode(currentText+text))
}