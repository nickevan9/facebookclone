package com.example.facebookclone.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.facebookclone.R
import com.example.facebookclone.view.register.JoinFacebookActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        // logic cua phan authoritor



        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(SplashScreenActivity@this, JoinFacebookActivity::class.java))
        }, 2000)
    }
}