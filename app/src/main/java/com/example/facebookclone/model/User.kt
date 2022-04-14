package com.example.facebookclone.model

import java.io.Serializable

data class User(
    var firstName : String,
    var lastName : String,
    var birthday : String,
    var gender : Int,
    var phoneNumber : String,
    var email : String,
    var password : String
) : Serializable {

}
