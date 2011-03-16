function validateFormRegistration()
{
    var flag = false;
    var email = document.getElementById("e-mail");
    var emailStr = email.value;
    var regEmail= new RegExp("[0-9a-z_]+@[0-9a-z_^.]+\\.[a-z]{2,3}", 'i');
    var message = "";
    if (!regEmail.test(emailStr)) {
        message = "\n (!) email is incorrect!!!  ";
        alert(message);
    }else{
        flag = true;
    }
    return flag;
}