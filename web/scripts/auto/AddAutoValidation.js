function validateAutoField()
{
    var message_vin = "";
    var message_odometer = "";
    var vin = document.getElementById("vin");
    var vinStr = vin.value;
    var odometer = document.getElementById("odometer");
    var odometerStr = odometer.value;

    var vinReg= new RegExp("[1-9_A-Z]{17}", 'i');
    if(!vinReg.test(vinStr)){
        message_vin = "\n (!) Your VIN is wrong!";
        alert(message_vin+message_odometer);
        return false;
    }
    var odometerReg= new RegExp("[0-9]+", 'i');
    if(!odometerReg.test(odometerStr)){
        message_odometer = "\n (!) Your odometer value is wrong!";
        alert(message_vin+message_odometer);
        return false;
    }else{
        var o = Integer.valueOf(odometerStr);
        if(o<=0){
            message_odometer = message_odometer + " Enter pos number";
            alert(message_vin+message_odometer);
            return false;
        }
    }

    return true;


}