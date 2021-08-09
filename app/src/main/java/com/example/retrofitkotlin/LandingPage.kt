package com.example.retrofitkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitkotlin.modelapi.GetUserResponse
import com.example.retrofitkotlin.modelapp.User
import com.example.retrofitkotlin.objectretrofit.ApiClient
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_landing_page.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LandingPage : AppCompatActivity() {

    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)
        initAdapter()
        loadData()
    }

    //inisialisasi adapter terlebih dahulu
    private fun initAdapter() {
        //user adapter kirim list kosng dan context
        userAdapter = UserAdapter(ArrayList(), this)
        //ingin menampilkan di recycle view dengan set ke linear layput
        rvUser.layoutManager = LinearLayoutManager(this)
        //set adapter ke user adapter
        rvUser.adapter = userAdapter
    }

    private fun loadData() {
        ApiClient.apiService.getUsers().enqueue(object: Callback<GetUserResponse> {
            override fun onResponse(
                call: Call<GetUserResponse>,
                response: Response<GetUserResponse>
            ) {
                if (response.isSuccessful){
                    //kita ambil datanya sesuai respon
                    val result =response.body()?.data
                    //jika data tidak null
                    if (!result.isNullOrEmpty()){
                        //list merupakan variabel untuk menyimpan list kosong untuk ditampilkan
                        val list = ArrayList<User>()
//                        Log.d("<RESULT>", "onResponse: " + Gson().toJson(result))
                        //kita mengambil datanya dengan foreach untuk ditampilkan setelah login nanti
                        result.forEach{itemResponse ->
                            //buat object user dulu
                            val user = User(
                                itemResponse.id,
                                itemResponse.email,
                                itemResponse.first_name,
                                itemResponse.last_name,
                                itemResponse.avatar
                            )
                            //masukin object user yg kita convert
                            list.add(user)
                        }
                        userAdapter.updateData(list)
                    }
                }
            }

            override fun onFailure(call: Call<GetUserResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}