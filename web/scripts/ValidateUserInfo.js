function validateUserInfoForm()
{
    var lastName = document.getElementById("lastName");
    var lastNameStr = lastName.value;
    var name = document.getElementById("firstName");
    var nameStr = name.value;
    var nickName = document.getElementById("nickName");
    var nickNameStr = nickName.value;
    
    var message = "";
    var message_3 = "";
    var regLogin= new RegExp("[0-9a-z_]{3,}", 'i');

    if(lastNameStr.length == 0 ){
        message = "lastName   ";
    }
    if(nameStr.length == 0 ){
        message = message + "firstName   ";
    }
    if(nickNameStr.length == 0 ){
        message = message + "nickName   ";
    }
    if(!regLogin.test(nickNameStr)){
        message_3 = "\n (!) Your nickName is too short. it's length must be more then 3 simbols!  ";
    }
    if((message.length != 0) || (message_3.length != 0)){
        if(message.length != 0){
            message = "You are FOOL!!!\n (!) Fields   " + message + "are EMPTY!";
        }
        alert(message + message_3);
        return false;
    }
    if((message.length == 0) && (message_3.length == 0)){
        return true;
    }
    return true;
}