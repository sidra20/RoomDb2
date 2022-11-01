package com.sidrakotlin.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sidrakotlin.roomdb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() , UserAdapter.editClickListener {
    private lateinit var binding :ActivityMainBinding
    private lateinit var myDb: AppDatabase
    private lateinit var user: UserEntity
    private lateinit var userList: List<UserEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.userRv.layoutManager = LinearLayoutManager(this);
        myDb = AppDatabase.getDatabase(this)
        binding.submit.setOnClickListener {

                addData()



        }

        getUsersList()
    }
    private fun addData()
    {
        var name1: String = binding.name.text.toString()
        var email1: String = binding.email.text.toString()
        val dao = myDb.userDao()

        if(binding.submit.text.equals("Submit")) {

            if (name1.isNotEmpty() && email1.isNotEmpty()) {
                user = UserEntity(0, name1, email1)

                dao.insertUser(user)
                Toast.makeText(this, "Data added!", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "Required!", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            val user = UserEntity(binding.name.getTag(binding.name.id).toString().toInt(), name1, email1)
            dao.updateUser(user)
            Toast.makeText(this, "Updated!",Toast.LENGTH_SHORT).show()

            binding.submit.text="Submit"
        }
        binding.name.setText("")
        binding.email.setText("")

    }

    private fun getUsersList()
    {
        userList = myDb.userDao().getAll()
        if(userList.isEmpty())
        {
            Toast.makeText(this, "Empty!",Toast.LENGTH_SHORT).show()

        }
        val userAdapter:UserAdapter=UserAdapter(this@MainActivity,userList!!,this,  )
        binding.userRv.adapter=userAdapter
        userAdapter.notifyDataSetChanged()
    }

    override fun editUser(user: UserEntity) {
        binding.name.setText(user.name)
        binding.email.setText(user.email)
        binding.name.setTag(binding.name.id, user.id)
        binding.submit.setText("Update")
    }
}