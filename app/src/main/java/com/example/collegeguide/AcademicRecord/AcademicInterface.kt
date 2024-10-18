package com.example.collegeguide.AcademicRecord

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface AcademicInterface {
    @Insert
    fun insertTodo(academicEntity: AcademicEntity)

    @Query("SELECT*FROM AcademicEntity")
    fun getList():List<AcademicEntity>

    @Update
    fun updateAcademicEntity(academicEntity: AcademicEntity)

    @Delete
    fun deleteAcademicEntity(academicEntity: AcademicEntity)

}