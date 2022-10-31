package com.sidrakotlin.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sidrakotlin.roomdb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding
    private lateinit var myDb: AppDatabase
    private lateinit var user: UserEntity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myDb = AppDatabase.getDatabase(this)
        binding.submit.setOnClickListener {
            addData()
        }
    }
    private fun addData()
    {
        var name1:String = binding.name.text.toString()
        var email1:String = binding.email.text.toString()
        if(name1.isNotEmpty() && email1.isNotEmpty())
        {
            user = UserEntity(0,name1,email1)
            myDb.userDao().insertUser(user)
            Toast.makeText(this,"Data added!",Toast.LENGTH_SHORT).show()

        }

        else{
            Toast.makeText(this,"Required!",Toast.LENGTH_SHORT).show()
        }



    }
}