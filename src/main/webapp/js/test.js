
function validate(form) {
    var login = document.registration_form.login.value;
    var password = document.registration_form.password.value;
    var mail = document.registration_form.email.value;
    var check_password = document.registration_form.password_confirmation.value;
    var telephone = document.registration_form.telephone.value;
    // var telephone = document.registration_form.telephone.value;

    var reLogin = new RegExp("^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,48}[a-zA-Z0-9]$");
    var rePassword = new RegExp("^(?=.*[0-9])(?=.*[a-z]).{8,32}$");
    var reEmail = new RegExp("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    var reTelephone = new RegExp("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$");
    var reText = new RegExp(".*");

    if (!reLogin.test(login) || login == null || login == "") {
        alert("Invalid login");
        return false;
    } else if (!rePassword.test(password) || password == "" || password == null) {
        alert("Password is non valid!");
        return false;
    } else if (!reEmail.test(mail) || mail == "" || mail == null) {
        alert("Email is non valid!");
        return false;
    } else if (check_password != password) {
        alert("Passwords are not the same.");
        return false;
    }else if (!reTelephone.test(telephone) || telephone == "" || telephone == null) {
        alert("Phone number is non valid!");
        return false;
    }
}