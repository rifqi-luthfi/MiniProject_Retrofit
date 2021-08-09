package com.example.retrofitkotlin.MakeUrl

import com.example.retrofitkotlin.modelapi.GetUserResponse
import com.example.retrofitkotlin.modelapi.LoginResponse
import com.example.retrofitkotlin.modelapp.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

//keuntungan retrofit, gaperlu tau implementasi dari get post delete dan lain lain.
//cukup mendeklarasikan nama functionnya aja
interface ApiService {

    @POST("api/login")
        //mengirim req ke server dalam bentuk asyn dan sycn, Call mengirim response yang diharapkan (LoginResponse)
        //mengirim email dan password ke server (User)
    fun login(@Body user: User): Call<LoginResponse>

    @GET("api/users")
    fun getUsers(): Call<GetUserResponse>

}