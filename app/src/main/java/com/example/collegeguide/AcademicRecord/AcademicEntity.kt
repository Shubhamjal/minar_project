package com.example.collegeguide.AcademicRecord

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AcademicEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val pa1Score: Int,
    val pa2Score: Int,
    val pa3Score: Int,
    val attendance: Int,
    val internalScore: Int,
    val externalScore: Int,
)
