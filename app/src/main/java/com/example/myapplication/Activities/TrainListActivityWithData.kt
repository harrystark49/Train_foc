package com.example.myapplication.Activities

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.adapters.getTrainListByNameAdapter
import com.example.myapplication.database.DataBaseHelper
import com.example.myapplication.databinding.ActivityTrainListWithDataBinding

class TrainListActivityWithData : AppCompatActivity() {
    lateinit var binding: ActivityTrainListWithDataBinding
    internal lateinit var dbhelper: DataBaseHelper
    lateinit var db: SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTrainListWithDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbhelper = DataBaseHelper(this)
        db = dbhelper.writableDatabase
        var bundle=intent.extras
        var fname=bundle?.getString("fname")
        var lname=bundle?.getString("lname")
        var list1=dbhelper.getBookedTrains(fname.toString(),lname.toString())
        if(list1.isEmpty()){
            Glide.with(this)
                .asGif()
                .load(R.drawable.nodata)
                .into(binding.iv1)
            binding.iv1.visibility=View.VISIBLE
        }else{
            binding.iv1.visibility=View.GONE
        }
        var llm=LinearLayoutManager(this)
        llm.orientation=LinearLayoutManager.VERTICAL
        var rcv=getTrainListByNameAdapter(list1)
        binding.rcv.layoutManager=llm
        binding.rcv.adapter=rcv

    }
}