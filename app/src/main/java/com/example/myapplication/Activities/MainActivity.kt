package com.example.myapplication.Activities

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.database.DataBaseHelper
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.entities.Booked
import com.example.myapplication.entities.Passenger
import com.example.myapplication.entities.Train
import com.example.myapplication.entities.TrainStatus
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    internal lateinit var dbhelper: DataBaseHelper
    lateinit var db: SQLiteDatabase

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbhelper = DataBaseHelper(this)
        db = dbhelper.writableDatabase

        val myShader: Shader = LinearGradient(
            0f, 0f, 0f, 100f,
            Color.parseColor("#FFD33F3F"), Color.parseColor("#191717"),
            Shader.TileMode.CLAMP
        )


        with(myShader) {

            binding.tv.paint.setShader(myShader)
            binding.tv1.getPaint().setShader(myShader)
            binding.tv2.getPaint().setShader(myShader)
            binding.tv3.getPaint().setShader(myShader)
            binding.tv4.getPaint().setShader(myShader)
            binding.tv5.getPaint().setShader(myShader)
            binding.tv6.getPaint().setShader(myShader)

        }

        binding.cv1.setOnClickListener {
            val intent= Intent(this, TrainListActivity::class.java)
            intent.putExtra("key","1")
            startActivity(intent)
        }
        binding.cv2.setOnClickListener {
            val intent= Intent(this, TrainListActivity::class.java)
            intent.putExtra("key","2")
            startActivity(intent)
        }
        binding.cv3.setOnClickListener {
            val intent= Intent(this, TrainListActivity::class.java)
            intent.putExtra("key","3")
            startActivity(intent)
        }

        binding.cv4.setOnClickListener {
            val intent= Intent(this, TrainWithPassengerCount::class.java)
            startActivity(intent)
        }

        binding.cv5.setOnClickListener {
            val intent= Intent(this, TrainListActivity::class.java)
            intent.putExtra("key","5")
            startActivity(intent)
        }

        binding.cv6.setOnClickListener {
            val intent= Intent(this, CancelTicket::class.java)
            startActivity(intent)
        }
        Glide.with(this)
            .asGif()
            .fitCenter()
            .load(R.drawable.metro)
            .into(binding.iv1)
        val booked = InputStreamReader(assets.open("booked.csv"))
        val passenger= InputStreamReader(assets.open("Passengerdata.csv"))
        val Train= InputStreamReader(assets.open("Train.csv"))
        val Train_Status= InputStreamReader(assets.open("Train_status.csv"))

        val bookedreader = BufferedReader(booked)
        val passengerReader= BufferedReader(passenger)
        val trainReader= BufferedReader(Train)
        val trainStatusReader= BufferedReader(Train_Status)

        val bookedList = readBookedCsv(bookedreader)
        val passengerList=readPassengerCsv(passengerReader)
        val trainList=readTrainCsv(trainReader)
        val trainStatusList=readTrainStatusCsv(trainStatusReader)




        for (i in bookedList) {
            val values = ContentValues().apply {
                put("passenger_ssn", i.p_ssn.trim())
                put("train_number", i.train_number.trim())
                put("ticket_type", i.ticket_type.trim())
                put("status", i.Status.trim())
            }
            db.insert("booked", null, values)
        }
        for( i in trainList){
            var cv= ContentValues().apply {
                put("train_number",i.train_number.trim())
                put("train_name",i.train_name.trim())
                put("premium_fair",i.premium_fair.trim())
                put("general_fair",i.general_fair.trim())
                put("source_station",i.source_station.trim())
                put("destination_station",i.destination_source.trim())
            }
            db.insert("Train",null,cv)
        }
        for( i in passengerList){
            val cv= ContentValues().apply {
                put("first_name",i.first_name)
                put("last_name",i.last_name)
                put("address",i.address)
                put("city",i.city)
                put("county",i.county)
                put("phone",i.phone)
                put("SSN",i.SSN)
                put("bdate",i.bdate.toString())
            }
            db.insert("Passengers",null,cv)
        }
        for(i in trainStatusList){
            val cv= ContentValues().apply {
                put("train_date",i.TrainDate)
                put("Train_name",i.TrainName)
                put("PremiumSeatsAvailable",i.PremiumSeatsAvailable)
                put("GenSeatsAvailable",i.GenSeatsAvailable)
                put("PremiumSeatsOccupied",i.PremiumSeatsOccupied)
                put("GenSeatsOccupied",i.GenSeatsOccupied)
            }
            db.insert("train_status",null,cv)
        }
    }

    private fun readTrainStatusCsv(trainStatusReader: BufferedReader): List<TrainStatus> {
        return trainStatusReader.lineSequence().filter { it.isNotBlank() }.map {
            val (tdate,tname,preSeatsAvailable,genSeatsAvailable,preSeatsOccupied,genSeatsOccupied)=it.split(',', limit = 6)
            TrainStatus(
                tdate.trim(),
                tname.trim(),
                preSeatsAvailable.trim(),
                genSeatsAvailable.trim(),
                preSeatsOccupied.trim(),
                genSeatsOccupied.trim()
            )
        }.toList()
    }

    fun readBookedCsv(reader: BufferedReader): List<Booked> {
            return reader.lineSequence()
                .filter { it.isNotBlank() }
                .map {
                    val (p_ssn, train_number, ticket_type, Status) = it.split(
                        ',',
                        ignoreCase = false,
                        limit = 4
                    )
                    Booked(p_ssn.trim(), train_number.trim(), ticket_type.trim(), Status.trim())
                }.toList()
        }

    fun readTrainCsv(reader: BufferedReader):List<Train>{
        return reader.lineSequence()
            .filter {
            it.isNotBlank()
                        }.map {
                            val (tno,tname,premiumFair,generalFair,sourceStation,destionationSource)=it.split(',', limit = 6, ignoreCase = false)
                Train(tno, tname, premiumFair, generalFair, sourceStation, destionationSource)

        }.toList()
    }

    fun readPassengerCsv(reader: BufferedReader):List<Passenger>{

        return  reader.lineSequence().filter {
            it.isNotBlank()
        }.map {
            val (fname,lname,add,city,country,phone,ssn,bdate)=it.split(',', ignoreCase = false, limit = 8)

            Passenger(
                fname.trim(),
                lname.trim(),
                add.trim(),
                city.trim(),
                country.trim(),
                phone,
                ssn,
                bdate
            )

        }.toList()
    }
}

operator fun <T> List<T>.component6(): T = get(5)
operator fun <T> List<T>.component7(): T = get(6)
operator fun <T> List<T>.component8(): T = get(7)



