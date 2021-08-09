package com.example.retrofitkotlin.modelapi

data class GetUserResponse(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: ArrayList<UserItemResponse>
)

//karna data berupa array list of object maka kita buat dulu propertinya
data class UserItemResponse(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
)