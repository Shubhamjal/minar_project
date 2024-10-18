package com.example.collegeguide.AcademicRecord

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AcademicEntity::class], version = 1, exportSchema = true)
abstract class AcademicDatabase: RoomDatabase() {
    abstract fun academicInterface(): AcademicInterface
    companion object{
        private var academicDatabase:AcademicDatabase?=null
        fun getInstance(context: Context):AcademicDatabase{
            if(academicDatabase==null){
                academicDatabase= Room.databaseBuilder(context, AcademicDatabase::class.java,"AcademicDatabase").allowMainThreadQueries().build()
            }
            return academicDatabase!!
        }
    }

}