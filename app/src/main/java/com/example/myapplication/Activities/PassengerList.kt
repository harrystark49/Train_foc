package com.example.myapplication.Activities

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.adapters.PassengerListAdapter
import com.example.myapplication.database.DataBaseHelper
import com.example.myapplication.databinding.ActivityPassengerListBinding

class PassengerList : AppCompatActivity() {
    lateinit var binding: ActivityPassengerListBinding
    internal lateinit var dbhelper: DataBaseHelper
    lateinit var db: SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityPassengerListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbhelper = DataBaseHelper(this)
        db = dbhelper.writableDatabase
        var bundle=intent.extras
        var date=bundle?.getString("date")
        var trainName=bundle?.getString("trainname")

        var llm=LinearLayoutManager(this)
        llm.orientation= LinearLayoutManager.VERTICAL

        if(!date.isNullOrEmpty()){
            var list=dbhelper.getPassengersbyDate(date)

            if(list.isEmpty()){
                Glide.with(this)
                    .asGif()
                    .load(R.drawable.nodata)
                    .into(binding.iv1)
                binding.iv1.visibility= View.VISIBLE
            }else{
                binding.iv1.visibility= View.GONE
            }

            var rcv= PassengerListAdapter(list)
            binding.rcv.layoutManager=llm
            binding.rcv.adapter=rcv

        }else if(!trainName.isNullOrEmpty()){
            var list=dbhelper.getPlistFromTrain(trainName)
            var rcv= PassengerListAdapter(list)
            binding.rcv.layoutManager=llm
            binding.rcv.adapter=rcv
        }




    }
}