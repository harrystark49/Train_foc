package com.example.myapplication.Activities

import android.content.Intent
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.Shader.TileMode
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySplashScreenBinding


class splashScreen : AppCompatActivity() {
    lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var anim=AnimationUtils.loadAnimation(this, R.anim.bounce)
        binding.tv1.startAnimation(anim)
        Glide.with(this)
            .asGif()
            .centerCrop()
            .load(R.drawable.train)
            .into(binding.iv1)

        val myShader: Shader = LinearGradient(
            0f, 0f, 0f, 100f,
            Color.parseColor("#FFD33F3F"), Color.parseColor("#191717"),
            TileMode.CLAMP
        )
        binding.tv1.getPaint().setShader(myShader)

        val handler = Handler()
        handler.postDelayed(Runnable {
            val mInHome = Intent(this, MainActivity::class.java)
            startActivity(mInHome)
            finish()
        }, 6000)


    }
}