package com.sidrakotlin.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase:RoomDatabase() {

    abstract fun userDao() :UserDAO

    companion object{
        private var instance:AppDatabase?=null

        fun getDatabase(context: Context):AppDatabase
        {
            if(instance==null)
            {

                synchronized(this)
                {


                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "db"
                    ).allowMainThreadQueries()
                        .build()
                }
            }
            return instance!!
        }

    }
}