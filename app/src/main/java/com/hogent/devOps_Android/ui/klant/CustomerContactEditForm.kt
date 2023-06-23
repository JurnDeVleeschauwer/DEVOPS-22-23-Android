package com.hogent.devOps_Android.ui.klant

data class EditForm(var contact1 : ContactOne, var contact2: ContactTwo) {
    fun isValid(): Boolean{
        if(!contact1.isValid()) {
            return false;
        }
        if(contact2.isValid()) {
            return true;
        }
        if(contact2.email == "" && contact2.phone == "" && contact2.fullName == ""){
            return true;
        }
        return false;

    }
    fun getError(): String{
        if(!contact1.isValid()) {
            return contact1.getError();
        }
        if(!contact2.isValid()){
            return contact2.getError()
        }
        throw java.lang.NullPointerException();
    }

    fun reset(){
        contact1 = ContactOne("","","")
        contact2 = ContactTwo("","","")
    }
}


data class ContactOne(val email: String, val phone: String, val fullName: String) {
    fun isValid(): Boolean {
        return isValidEmail(email) && isValidPhone(phone) && isValidFullName(fullName);
    }

    fun getError(): String {
        if (!isValidEmail(email)) {
            return "Email contactpersoon 1 is ongeldig."
        }
        if (!isValidPhone(phone)) {
            return "Gsmnummer contactpersoon 1 is ongeldig."
        }
        if (!isValidFullName(fullName)) {
            return "Naam van contactpersoon 1 is ongeldig.";
        }
        return "";
    }
}

data class ContactTwo(val email: String, val phone: String, val fullName: String) {
    fun isValid(): Boolean {
        return isValidEmail(email) && isValidPhone(phone) && isValidFullName(fullName);
    }

    fun getError(): String {
        if (!isValidEmail(email)) {
            return "Email contactpersoon 2 is ongeldig."
        }
        if (!isValidPhone(phone)) {
            return "Gsmnummer contactpersoon 2 is ongeldig."
        }
        if (!isValidFullName(fullName)) {
            return "Naam contactpersoon 2 is ongeldig."
        }
        return "";
    }
}

    private fun isValidEmail(email: String) : Boolean{
        val pattern = Regex(pattern = "^[A-Za-z0-9+_.-]+@(.+\$)")
        return pattern.matches(email);
    }
    private fun isValidPhone(phone: String) : Boolean{
        val pattern = Regex("^(04\\d{8}|0[^0]\\d{8})$")
        return pattern.matches(phone);
    }
    private fun isValidFullName(fullName: String) : Boolean{
        if(!fullName.matches(Regex("[^0-9]"))){
            return fullName.split(" ").size >= 2;
        }
        return false;
    }
