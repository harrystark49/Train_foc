package com.example.myapplication.Activities

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapters.CancelTicketAdapter
import com.example.myapplication.adapters.getTrainListByNameAdapter
import com.example.myapplication.database.DataBaseHelper
import com.example.myapplication.databinding.ActivityCancelTicketBinding
import com.example.myapplication.entities.Booked
import com.example.myapplication.entities.Passenger

class CancelTicket : AppCompatActivity() {
    internal lateinit var dbhelper: DataBaseHelper
    lateinit var db: SQLiteDatabase
    lateinit var adap:CancelTicketAdapter
    lateinit var binding: ActivityCancelTicketBinding
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCancelTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbhelper = DataBaseHelper(this)
        db = dbhelper.writableDatabase

        var list1=getdata()
        adap= CancelTicketAdapter(list1){ pssn, trainN ->
            dbhelper.deleteTicket(pssn,trainN)
            adap.trainList=getdata()
            adap.notifyDataSetChanged()
        }

        var b=Booked(p_ssn ="Passanger_ssn","Train_Number","Ticket_Type","Staus" )
        if(list1.contains(b)){
            list1.remove(b)
        }
        var llm= LinearLayoutManager(this)
        llm.orientation= LinearLayoutManager.VERTICAL
        var rcv= adap
        binding.rcv.layoutManager=llm
        binding.rcv.adapter=rcv
    }

    @SuppressLint("Range")
    fun getdata():ArrayList<Booked>{
        var cursor=db.rawQuery("select * from booked", arrayOf())
        var list1=ArrayList<Booked>()
        while (cursor.moveToNext()){
            var pssn=cursor.getString(cursor.getColumnIndex("passenger_ssn")).toString()
            var train_number=cursor.getString(cursor.getColumnIndex("train_number")).toString()
            var ticket_type=cursor.getString(cursor.getColumnIndex("ticket_type")).toString()
            var status=cursor.getString(cursor.getColumnIndex("status")).toString()
            list1.add(Booked(pssn,train_number,ticket_type,status))
        }
        return list1
    }
}