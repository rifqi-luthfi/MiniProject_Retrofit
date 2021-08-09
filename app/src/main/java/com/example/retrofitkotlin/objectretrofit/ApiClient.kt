package com.example.retrofitkotlin.objectretrofit
//untuk membuat retrofit kita perlu membuat object retrofit terlebih dahulu, ini kita buat dengan single tone
// single tone = object user jika dibuat sekali gabisa dibuat ulang agar tidak membuang resource
import com.example.retrofitkotlin.MakeUrl.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    //Buat object retrofit menggunakan Builder,
    // Builder itu sistemnya pengen ngeset atribut apa aja dulu,
    // dan akhir akhir object akan dibuat
    private val retrofit = Retrofit.Builder()
            //menambahkan coverter (pengen pake GSON untuk serealize dan deserealize data json)
        .addConverterFactory(GsonConverterFactory.create())
            // memberitahukan base URL kita
        .baseUrl("https://reqres.in/")
            //object retrofit sudah jadi
        .build()

    //function ini menggunakan object retrofit untuk membuat API service
    val apiService = retrofit.create(ApiService::class.java)
}