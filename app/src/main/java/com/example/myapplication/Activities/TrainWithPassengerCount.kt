package com.example.myapplication.Activities

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.example.myapplication.R
import com.example.myapplication.adapters.PassengerListAdapter
import com.example.myapplication.adapters.passengercountAdapter
import com.example.myapplication.database.DataBaseHelper
import com.example.myapplication.databinding.ActivityTrainWithPassengerCountBinding

class TrainWithPassengerCount : AppCompatActivity() {
    lateinit var binding: ActivityTrainWithPassengerCountBinding
    internal lateinit var dbhelper: DataBaseHelper
    lateinit var db: SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTrainWithPassengerCountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbhelper = DataBaseHelper(this)
        db = dbhelper.writableDatabase

        var list=dbhelper.getBookPassengerCount()

        var llm= LinearLayoutManager(this)
        llm.orientation= LinearLayoutManager.VERTICAL
        var rcv= passengercountAdapter(list)
        binding.rcv.layoutManager=llm
        binding.rcv.adapter=rcv



    }
}