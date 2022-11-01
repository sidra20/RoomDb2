package com.sidrakotlin.roomdb

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class UserAdapter(val listener: editClickListener, private val userList:List<UserEntity>, private val context: Context) : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view:View = LayoutInflater.from(context).inflate(R.layout.user_item,parent,false)
        val holderObj = MyViewHolder(view, listener)
        return holderObj
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val users = userList[position]
        holder.name.text= users.name
        holder.email.text = users.email
        holder.delete.setOnClickListener{
            deleteUser(users)
            notifyDataSetChanged()
            Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show()
        }

        holder.edit.setOnClickListener{
            listener.editUser(users)
        }
    }
    private fun deleteUser(user : UserEntity){
        val db = AppDatabase.getDatabase(context)
        db.userDao().deleteUser(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(itemView: View, val listener: editClickListener): RecyclerView.ViewHolder(itemView) {

        var name:TextView = itemView.findViewById(R.id.userName)
        var email:TextView = itemView.findViewById(R.id.userEmail)
        var delete:ImageView = itemView.findViewById(R.id.deleteUser)
        var edit:ImageView = itemView.findViewById(R.id.editUser)
    }

    interface editClickListener{
        fun editUser(user: UserEntity)
    }
}