package com.example.retrofitkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitkotlin.modelapp.User
import kotlinx.android.synthetic.main.user_item_layout.view.*

//untuk membuat recycleview kita harus membuat User Adapter dulu dan view holder
//kita mengextends Recyclerview.adapter yang isinya view holder yang telah kita buat
// user adapter ini kita bakan mengirim array list ke adapternya
class UserAdapter(private val users: ArrayList<User>, private val context: Context)
    : RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    //dalam membuat view holder dalam kelas,kita harus mengextends Recyclerview.viewholder
    //dan menerima parameter view sebagai constructurnya
    //mengatur tiap item Item data
    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        //variable untuk mendifinisikan tiap jenis type ke view untuk di set
        private val ivAvatar : ImageView = view.ivAvatar
        private val tvName : TextView = view.tvName
        private val tvEmail : TextView = view.tvEmail
        //untuk dipakai di Onbind
        fun bind(user: User) {
            tvName.text = "${user.first_name} ${user.last_name}"
            tvEmail.text = user.email
            //membutuhkan context
            Glide.with(context)
                    //load url
                .load(user.avatar)
                    //masukkan ke avavatar
                .into(ivAvatar)


        }

    }


    //membuat View Holdernya
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        //karna view holder menerima view maka untuk mendapatkan objek view kita menggunakan Layout inflater
        val layoutInflater = LayoutInflater.from(parent.context)
        //buat object
        val view = layoutInflater.inflate(R.layout.user_item_layout, parent, false)
        //mengembalikan object yang telah kita buat
        return UserViewHolder(view)
    }

    //set data user ke tiap cardnya user item
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        //disini kita set
        holder.bind(user)
    }

    //menghitung banyak data user
    override fun getItemCount(): Int = users.size

    //update list data baru kita
    fun updateData(newUsers: ArrayList<User>){
        this.users.clear()
        this.users.addAll(newUsers)
        notifyDataSetChanged()
    }
}