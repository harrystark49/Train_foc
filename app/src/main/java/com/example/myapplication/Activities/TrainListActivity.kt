package com.example.myapplication.Activities

import android.content.Intent
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.RequestlayoutBinding
import java.lang.Exception

class TrainListActivity : AppCompatActivity() {
    lateinit var binding: RequestlayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= RequestlayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this)
            .asGif()
            .fitCenter()
            .load(R.drawable.metro)
            .into(binding.iv1)

        Glide.with(this)
            .asGif()
            .load(R.drawable.train2gif)
            .into(binding.c1)
        var bundle=intent.extras
        var i=bundle?.getString("key").toString()

        val myShader: Shader = LinearGradient(
            0f, 0f, 0f, 100f,
            Color.parseColor("#FFD33F3F"), Color.parseColor("#191717"),
            Shader.TileMode.CLAMP
        )
        binding.textView2.getPaint().setShader(myShader)
        if(i=="1"){
            binding.tv1.hint="First Name"
            binding.tv2.hint="Last Name"
        }
        if(i=="2"){
           binding.textView2.text="Get Passengers by date"
            binding.cv2.visibility=View.GONE
            binding.tv2.visibility-View.GONE
            binding.tv1.hint="yyyy-mm-dd"
        }
        if(i=="3"){
            binding.textView2.text="Get Train and Passenger details \n by age"
            binding.cv2.visibility=View.GONE
            binding.tv2.visibility-View.GONE
            binding.tv1.hint="Please enter age"
        }
        if(i=="5"){
            binding.textView2.text="Confirmed list \n of \n Passengers"

            binding.cv2.visibility=View.GONE
            binding.tv2.visibility-View.GONE
            binding.tv1.hint="Please Enter Train Name"

        }



        binding.button.setOnClickListener {
            if(i=="1") {
                var fname = binding.tv1.text.toString()
                var lname = binding.tv2.text.toString()
                if(!fname.isNullOrEmpty() && !lname.isNullOrEmpty()){
                    var intent = Intent(this, TrainListActivityWithData::class.java)
                    intent.putExtra("fname", fname)
                    intent.putExtra("lname", lname)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "First Name and Last Name Can't be empty", Toast.LENGTH_SHORT).show()
                }

            }else if(i=="2"){
                var date=binding.tv1.text.toString()
                var validation=checkDate(date)
                if(validation){
                    var intent=Intent(this,PassengerList::class.java)
                    intent.putExtra("date",date)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Please enter valid date", Toast.LENGTH_SHORT).show()
                }
            }else if(i=="5"){
                var trainName=binding.tv1.text.toString()
                var intent=Intent(this,PassengerList::class.java)
                intent.putExtra("trainname",trainName)
                startActivity(intent)
            }else if(i=="3"){
                var age=binding.tv1.text.toString()
                var intent=Intent(this,DetailsByAge::class.java)
                intent.putExtra("age",age)
                startActivity(intent)

            }
        }

    }
    fun checkDate(date: String):Boolean {

        var x=0;
        for(i in date){
            if(i=='-'){
                x++
            }
        }
        if(x!=2){
            return false
        }
        val list=date.split("-")
        try {
            if((list[0].count()==4) && (list[1].toInt()<13 && list[1].toInt()>0) && (list[1].toInt()>0 && list[1].toInt()<13)){
                return true
            }else{
                return false
            }
        }catch (e:Exception){
            return false
        }

    }

    override fun onResume() {
        binding.tv1.text.clear()
        binding.tv2.text.clear()
        binding.tv1.requestFocus()

        var anim= AnimationUtils.loadAnimation(this, R.anim.bounce)
        binding.iv1.startAnimation(anim)
        binding.c1.startAnimation(anim)
        binding.cv5.startAnimation(anim)



        super.onResume()
    }

}