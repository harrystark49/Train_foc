package com.example.myapplication.Activities

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.adapters.DataByAgeAdapter
import com.example.myapplication.adapters.passengercountAdapter
import com.example.myapplication.database.DataBaseHelper
import com.example.myapplication.databinding.ActivityDetailsByAgeBinding

class DetailsByAge : AppCompatActivity() {
    lateinit var binding: ActivityDetailsByAgeBinding
    internal lateinit var dbhelper: DataBaseHelper
    lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailsByAgeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbhelper = DataBaseHelper(this)
        db = dbhelper.writableDatabase

        var bundle=intent.extras
        var age=bundle?.getString("age").toString()
        var list=dbhelper.getInbetween(age)

        var age2=age.toIntOrNull()
        if(list.isEmpty()){
            binding.iv1.visibility= View.VISIBLE
            Glide.with(this)
                .asGif()
                .load(R.drawable.nodata)
                .into(binding.iv1)

            if(!age.isNullOrBlank()){
                if (age2 != null) {
                    if(!(age2>=50 && age2<=60)){
                        Toast.makeText(this, "Age should be inbetween 50 and 60", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }

        var llm= LinearLayoutManager(this)
        llm.orientation= LinearLayoutManager.VERTICAL
        var rcv= DataByAgeAdapter(list)
        binding.rcv.layoutManager=llm
        binding.rcv.adapter=rcv




    }
}

