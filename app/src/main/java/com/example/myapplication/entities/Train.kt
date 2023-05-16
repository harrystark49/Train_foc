package com.example.myapplication.entities

import androidx.room.Entity

@Entity
data class Train(val train_number:String,val train_name:String="",val premium_fair:String="",val general_fair:String="",val source_station:String="",val destination_source:String="")
