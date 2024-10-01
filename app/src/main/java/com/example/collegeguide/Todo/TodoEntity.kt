package com.example.collegeguide.Todo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class TodoEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int =0,
    var Title:String?="",
    var Description:String?="",
    var isCompleted: Boolean? = false
)