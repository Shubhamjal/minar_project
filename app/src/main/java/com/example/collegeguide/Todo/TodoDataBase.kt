package com.example.collegeguide.Todo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodoEntity::class], version = 1, exportSchema = true)
abstract class TodoDataBase :RoomDatabase(){

    abstract fun todoInterface(): TodoInterface

    companion object{
        private var todoDataBase: TodoDataBase?=null
        fun getInstance(context: Context):TodoDataBase{
            if(todoDataBase == null ){
                todoDataBase= Room.databaseBuilder(context,TodoDataBase::class.java,"TodoDataBase").allowMainThreadQueries().build()
            }
            return todoDataBase!!
        }
    }
}