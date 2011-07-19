function showPreviewAdd(){
    var regNum=/^\d+$/;
    var errorFlag = false;
    var messageDiv = "message-div";
    cleanPreview("message-div");
    var previewForm = document.getElementById("preview_form");
    cleanPreview("preview_form");
    var vin = getInputValue("vin");
    var odometer = getInputValue("odometer");
    var displacement = getInputValue("displacement");
    if (!regNum.test(vin)) {
        addTextInDiv(messageDiv,msg_auto_vin);
        errorFlag = true;
    }
    if (!regNum.test(odometer)) {
        addTextInDiv(messageDiv,msg_auto_odometer);
        errorFlag = true;
    }
    if (!regNum.test(displacement)) {
        addTextInDiv(messageDiv,msg_auto_displacement);
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
        //var trim = getSelectedOptionChild("trim");
        var engine = getSelectedOptionChild("engine");
        var modification = getSelectedOptionChild("modification");
        var year = getSelectedOptionChild("year");
        var body_style = getSelectedOptionChild("body-style");
        var door = getSelectedOptionChild("door");
        var drive_train = getSelectedOptionChild("drive-train");
        var exterior_color = getSelectedOptionChild("exterior-color");
        var interior_color = getSelectedOptionChild("interior-color");
        var fuel = getSelectedOptionChild("fuel");
        //var tires = getSelectedOptionChild("tires");
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
        var country_assembly = getSelectedOptionChild("country_assembly");
        var salvage = getCheckboxValue("salvage");

        var pwr_locks = getCheckboxValue("pwr_locks");
        var pwr_windows = getCheckboxValue("pwr_windows");
        var pwr_mirrors = getCheckboxValue("pwr_mirrors");
        var pwr_seats = getCheckboxValue("pwr_seats");
        var pwr_seats_drivers_only = getCheckboxValue("pwr_seats_drivers_only");
        var tilt_steering = getCheckboxValue("tilt_steering");
        var air_conditioning = getCheckboxValue("air_conditioning");
        var rear_air_conditioning = getCheckboxValue("rear_air_conditioning");
        var cruise_ctl = getCheckboxValue("cruise_ctl");
        var rear_defrost = getCheckboxValue("rear_defrost");
        var side_air_bags = getCheckboxValue("side_air_bags");
        var dual_air_bags = getCheckboxValue("dual_air_bags");
        var fog_lamps = getCheckboxValue("fog_lamps");
        var trip_counter = getCheckboxValue("trip_counter");
        var luggage_rack = getCheckboxValue("luggage_rack");
        var third_row_seat = getCheckboxValue("third_row_seat");
        var xenon_headlights = getCheckboxValue("xenon_headlights");
        var cd_player = getCheckboxValue("cd_player");
        var cd_changer = getCheckboxValue("cd_changer");
        var rear_wiper = getCheckboxValue("rear_wiper");
        var floor_mats = getCheckboxValue("floor_mats");
        var security_system = getCheckboxValue("security_system");
        var traction_control = getCheckboxValue("traction_control");
        var rear_tail = getCheckboxValue("rear_tail");
        var lift_gate = getCheckboxValue("lift_gate");
        var heated_seats = getCheckboxValue("heated_seats");
        var tinted_windows = getCheckboxValue("tinted_windows");
        var homelink = getCheckboxValue("homelink");
        var tire_pressure_monitor_system = getCheckboxValue("tire_pressure_monitor_system");
        var steering_wheel_audio_control = getCheckboxValue("steering_wheel_audio_control");
        var spoiler = getCheckboxValue("spoiler");
        var bug_deflector = getCheckboxValue("bug_deflector");
        var keyless_entry = getCheckboxValue("keyless_entry");
        var hand_tool_kit = getCheckboxValue("hand_tool_kit");
        var dual_sliding_side_doors = getCheckboxValue("dual_sliding_side_doors");
        var power_vent_windows = getCheckboxValue("power_vent_windows");
        var front_center_console = getCheckboxValue("front_center_console");
        var stow_and_go = getCheckboxValue("stow_and_go");
        var stow_and_go_3rd_row_only = getCheckboxValue("stow_and_go_3rd_row_only");
        var wood_trim_dash = getCheckboxValue("wood_trim_dash");
        var woodgrain_interior_package = getCheckboxValue("woodgrain_interior_package");
        var pwr_steering = getCheckboxValue("pwr_steering");
        
        var table = document.createElement("table");
        inserTrIfNotEmpty(table,lbl_make,document.createTextNode(make));
        inserTrIfNotEmpty(table,lbl_model,document.createTextNode(model));
        inserTrIfNotEmpty(table,lbl_modification,document.createTextNode(modification));
        inserTrIfNotEmpty(table,lbl_year,document.createTextNode(year));
        //inserTrIfNotEmpty(table,lbl_trim,document.createTextNode(trim));
        inserTrIfNotEmpty(table,lbl_engine,document.createTextNode(engine));
        inserTrIfNotEmpty(table,lbl_odometer,document.createTextNode(odometer));
        inserTrIfNotEmpty(table,lbl_displacement,document.createTextNode(displacement));
        inserTrIfNotEmpty(table,lbl_body_style,document.createTextNode(body_style));
        inserTrIfNotEmpty(table,lbl_door,document.createTextNode(door));
        inserTrIfNotEmpty(table,lbl_drive_train,document.createTextNode(drive_train));
        inserTrIfNotEmpty(table,lbl_exterior_color,document.createTextNode(exterior_color));
        inserTrIfNotEmpty(table,lbl_interior_color,document.createTextNode(interior_color));
        inserTrIfNotEmpty(table,lbl_interior_type,document.createTextNode(interior_type));
        inserTrIfNotEmpty(table,lbl_fuel,document.createTextNode(fuel));
        //inserTrIfNotEmpty(table,lbl_tires,document.createTextNode(tires));
        inserTrIfNotEmpty(table,lbl_top_type,document.createTextNode(top_type));
        inserTrIfNotEmpty(table,lbl_wheels,document.createTextNode(wheels));
        inserTrIfNotEmpty(table,lbl_transmission,document.createTextNode(transmission));
        inserTrIfNotEmpty(table,lbl_vin,document.createTextNode(vin));
        
        //damages
        inserTrIfNotEmpty(table,lbl_hood,document.createTextNode(hood));
        inserTrIfNotEmpty(table,lbl_roof,document.createTextNode(roof));
        inserTrIfNotEmpty(table,lbl_windshield,document.createTextNode(windshield));
        inserTrIfNotEmpty(table,lbl_lf_door,document.createTextNode(lf_door));
        inserTrIfNotEmpty(table,lbl_lr_door,document.createTextNode(lr_door));
        inserTrIfNotEmpty(table,lbl_rf_door,document.createTextNode(rf_door));
        inserTrIfNotEmpty(table,lbl_rr_door,document.createTextNode(rr_door));
        inserTrIfNotEmpty(table,lbl_l_qtr_panel,document.createTextNode(l_qtr_panel));
        inserTrIfNotEmpty(table,lbl_r_qtr_panel,document.createTextNode(r_qtr_panel));
        inserTrIfNotEmpty(table,lbl_front_bumper,document.createTextNode(front_bumper));
        inserTrIfNotEmpty(table,lbl_rear_bumper,document.createTextNode(rear_bumper));
        inserTrIfNotEmpty(table,lbl_lf_fender,document.createTextNode(lf_fender));
        inserTrIfNotEmpty(table,lbl_rf_fender,document.createTextNode(rf_fender));
        inserTrIfNotEmpty(table,lbl_deck_lid,document.createTextNode(deck_lid));
        inserTrIfNotEmpty(table,lbl_seats,document.createTextNode(seats));
        if(pwr_locks)
        inserTrIfNotEmpty(table,lbl_pwr_locks,document.createTextNode(lbl_lot_yes));
        if(pwr_windows)
        inserTrIfNotEmpty(table,lbl_pwr_windows,document.createTextNode(lbl_lot_yes));
         if(pwr_mirrors)
        inserTrIfNotEmpty(table,lbl_pwr_mirrors,document.createTextNode(lbl_lot_yes));
         if(pwr_seats)
        inserTrIfNotEmpty(table,lbl_pwr_seats,document.createTextNode(lbl_lot_yes));
         if(pwr_seats_drivers_only)
        inserTrIfNotEmpty(table,lbl_pwr_seats_drivers_only,document.createTextNode(lbl_lot_yes));
         if(tilt_steering)
        inserTrIfNotEmpty(table,lbl_tilt_steering,document.createTextNode(lbl_lot_yes));
         if(air_conditioning)
        inserTrIfNotEmpty(table,lbl_air_conditioning,document.createTextNode(lbl_lot_yes));
         if(rear_air_conditioning)
        inserTrIfNotEmpty(table,lbl_rear_air_conditioning,document.createTextNode(lbl_lot_yes));
         if(cruise_ctl)
        inserTrIfNotEmpty(table,lbl_cruise_ctl,document.createTextNode(lbl_lot_yes));
         if(rear_defrost)
        inserTrIfNotEmpty(table,lbl_rear_defrost,document.createTextNode(lbl_lot_yes));
         if(side_air_bags)
        inserTrIfNotEmpty(table,lbl_side_air_bags,document.createTextNode(lbl_lot_yes));
         if(dual_air_bags)
        inserTrIfNotEmpty(table,lbl_dual_air_bags,document.createTextNode(lbl_lot_yes));
         if(fog_lamps)
        inserTrIfNotEmpty(table,lbl_fog_lamps,document.createTextNode(lbl_lot_yes));
         if(trip_counter)
        inserTrIfNotEmpty(table,lbl_trip_counter,document.createTextNode(lbl_lot_yes));
         if(luggage_rack)
        inserTrIfNotEmpty(table,lbl_luggage_rack,document.createTextNode(lbl_lot_yes));
         if(third_row_seat)
        inserTrIfNotEmpty(table,lbl_third_row_seat,document.createTextNode(lbl_lot_yes));
         if(xenon_headlights)
        inserTrIfNotEmpty(table,lbl_xenon_headlights,document.createTextNode(lbl_lot_yes));
         if(cd_player)
        inserTrIfNotEmpty(table,lbl_cd_player,document.createTextNode(lbl_lot_yes));
         if(cd_changer)
        inserTrIfNotEmpty(table,lbl_cd_changer,document.createTextNode(lbl_lot_yes));
         if(rear_wiper)
        inserTrIfNotEmpty(table,lbl_rear_wiper,document.createTextNode(lbl_lot_yes));
         if(floor_mats)
        inserTrIfNotEmpty(table,lbl_floor_mats,document.createTextNode(lbl_lot_yes));
         if(security_system)
        inserTrIfNotEmpty(table,lbl_security_system,document.createTextNode(lbl_lot_yes));
         if(traction_control)
        inserTrIfNotEmpty(table,lbl_traction_control,document.createTextNode(lbl_lot_yes));
         if(rear_tail)
        inserTrIfNotEmpty(table,lbl_rear_tail,document.createTextNode(lbl_lot_yes));
         if(lift_gate)
        inserTrIfNotEmpty(table,lbl_lift_gate,document.createTextNode(lbl_lot_yes));
         if(heated_seats)
        inserTrIfNotEmpty(table,lbl_heated_seats,document.createTextNode(lbl_lot_yes));
         if(tinted_windows)
        inserTrIfNotEmpty(table,lbl_tinted_windows,document.createTextNode(lbl_lot_yes));
         if(homelink)
        inserTrIfNotEmpty(table,lbl_homelink,document.createTextNode(lbl_lot_yes));
         if(tire_pressure_monitor_system)
        inserTrIfNotEmpty(table,lbl_tire_pressure_monitor_system,document.createTextNode(lbl_lot_yes));
         if(steering_wheel_audio_control)
        inserTrIfNotEmpty(table,lbl_steering_wheel_audio_control,document.createTextNode(lbl_lot_yes));
         if(spoiler)
        inserTrIfNotEmpty(table,lbl_spoiler,document.createTextNode(lbl_lot_yes));
         if(bug_deflector)
        inserTrIfNotEmpty(table,lbl_bug_deflector,document.createTextNode(lbl_lot_yes));
         if(keyless_entry)
        inserTrIfNotEmpty(table,lbl_keyless_entry,document.createTextNode(lbl_lot_yes));
         if(hand_tool_kit)
        inserTrIfNotEmpty(table,lbl_hand_tool_kit,document.createTextNode(lbl_lot_yes));
         if(dual_sliding_side_doors)
        inserTrIfNotEmpty(table,lbl_dual_sliding_side_doors,document.createTextNode(lbl_lot_yes));
         if(power_vent_windows)
        inserTrIfNotEmpty(table,lbl_power_vent_windows,document.createTextNode(lbl_lot_yes));
         if(front_center_console)
        inserTrIfNotEmpty(table,lbl_front_center_console,document.createTextNode(lbl_lot_yes));
         if(stow_and_go)
        inserTrIfNotEmpty(table,lbl_stow_and_go,document.createTextNode(lbl_lot_yes));
         if(stow_and_go_3rd_row_only)
        inserTrIfNotEmpty(table,lbl_stow_and_go_3rd_row_only,document.createTextNode(lbl_lot_yes));
         if(wood_trim_dash)
        inserTrIfNotEmpty(table,lbl_wood_trim_dash,document.createTextNode(lbl_lot_yes));
         if(woodgrain_interior_package)
        inserTrIfNotEmpty(table,lbl_woodgrain_interior_package,document.createTextNode(lbl_lot_yes));
         if(pwr_steering)
        inserTrIfNotEmpty(table,lbl_pwr_steering,document.createTextNode(lbl_lot_yes));
        
        inserTrIfNotEmpty(table,"Country of assembly",document.createTextNode(country_assembly));
         if(salvage)
        inserTrIfNotEmpty(table,"Salvage",document.createTextNode(lbl_lot_yes));
        
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

function getSelectedOptionChild(selectName){
    var child;
    var select = document.getElementById(selectName);
    if(select!=null){
        var id = select.value;
        if(id!=''){
            child = $("#"+selectName+" option[value='"+id+"']").text();
        } else {
            child = '';
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

function getCheckboxValue(name){
    var value;
    var input = document.getElementById(name);
    if(input!=null){
        value = input.checked;
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

function inserTrIfNotEmpty(table,first,second){
    if(second.nodeValue!=null && second.nodeValue!='' && second.nodeValue!=lbl_none) {
        var num = table.rows.length;
        var newRow = table.insertRow(num);
        var newCell = newRow.insertCell(0);
        newCell.innerHTML=first;
        newCell = newRow.insertCell(1);
        newCell.appendChild(second);
    }
}