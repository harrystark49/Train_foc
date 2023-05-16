package com.example.myapplication.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Booked(@PrimaryKey
                  @ColumnInfo(name = "p_ssn")
                  val p_ssn:String ,
                  @ColumnInfo(name = "train_number")
                  val train_number:String,
                  @ColumnInfo(name = "ticket_type")
                  val ticket_type:String,
                  @ColumnInfo(name = "Status")
                  val Status:String)
