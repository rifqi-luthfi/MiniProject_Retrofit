package com.example.retrofitkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.retrofitkotlin.modelapi.LoginResponse
import com.example.retrofitkotlin.modelapp.User
import com.example.retrofitkotlin.objectretrofit.ApiClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //ketika button login dipencet kita akan mengambil apa yg diinput oleh user,
        // lalu mengirim data tersebut ke server
        btnLogin.setOnClickListener{
            login()
        }
    }

    private fun login() {
        //mengambil apa yang diinput oleh user
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        //membuat objek user dari email dan password yg telah diambil
        val user = User(email,password)
        //override interface dg object callback
        //memanggi object retrofit
        //enqueue  menjalankan untuk emminta request secara ascyn
        ApiClient.apiService.login(user).enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                //ketika status 200an
                if (response.isSuccessful){
                    //kita ambil tokennya
                    val token = response.body()?.token
                    Log.d("<RESULT>", "onResponse:  + $token")
                    Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
                    //jika dapat tokennya kita bisa langsung login
                    startActivity(Intent(this@MainActivity, LandingPage::class.java))

                }
            }

            // ke trigger ketika req ke server mengembalikan 500
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@MainActivity, "check your email and password", Toast.LENGTH_SHORT).show()
                //
            }

        })
    }
}