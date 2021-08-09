package com.example.retrofitkotlin.modelapp
// package ini adalah untuk model aplikasi kita (apa data yang bakal user isi)

data class User(
    val id: Int,
    var email: String,
    var password: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
) {
    constructor(email: String, password: String) : this(0,email, password, "", "", "")
    constructor(
        id: Int,
        email: String,
        first_name: String,
        last_name: String,
        avatar: String
    ) : this(id, email,"", first_name, last_name, avatar)
}

