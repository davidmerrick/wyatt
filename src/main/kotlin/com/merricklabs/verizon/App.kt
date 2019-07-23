package com.merricklabs.verizon

const val LOGIN_URL = "https://login.verizonwireless.com/vzauth/UI/Login?userNameOnly=false&mode=i&realm=vzw&customerType=DO"
const val USERNAME_FIELD_ID = "IDToken1"
const val PASSWORD_FIELD_ID = "IDToken2"

class App {
    val greeting: String
        get() {
            return "Hello world."
        }
}

fun main(args: Array<String>) {
    println(App().greeting)
}
