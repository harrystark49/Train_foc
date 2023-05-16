package com.example.myapplication.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class TrainStatus(
    @ColumnInfo(name = "TrainDate")
    val TrainDate:String,
    @ColumnInfo(name = "TrainName")
    val TrainName:String,
    @ColumnInfo(name = "PremiumSeatsAvailable")
    val PremiumSeatsAvailable:String,
    @ColumnInfo(name = "GenSeatsAvailable")
    val GenSeatsAvailable:String,
    @ColumnInfo(name = "PremiumSeatsOccupied")
    val PremiumSeatsOccupied:String,
    @ColumnInfo(name = "GenSeatsOccupied")
    val GenSeatsOccupied:String
)