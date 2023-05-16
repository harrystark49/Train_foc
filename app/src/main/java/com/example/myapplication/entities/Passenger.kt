package com.example.myapplication.entities

import androidx.room.Entity

@Entity
data class Passenger(val first_name:String,val last_name:String,val address:String,val city:String,val county:String,
                     val phone:String,val SSN:String,val bdate:String
)
