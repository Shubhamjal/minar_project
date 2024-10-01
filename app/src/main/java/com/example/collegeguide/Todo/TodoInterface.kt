package com.example.collegeguide.Todo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoInterface {

    @Insert
    fun insertTodo(todoEntity: TodoEntity)

    @Query("SELECT*FROM TodoEntity")
    fun getList():List<TodoEntity>

    @Update
    fun updateTodoEntity(todoEntity: TodoEntity)

    @Delete
    fun deleteTodoEntity(todoEntity: TodoEntity)

}