package iot.lab.qrdetails.data.model

data class RegistrationData(
    val id : String? ,
    val first_name : String? ,
    val last_name : String? ,
    val email_personal : String? ,
    val plan_type : String? ,
    val plan_description : String? ,
    val food_opted : Boolean? ,
    val ph_number : String? ,
    val total_fare : String?
)