package com.example.facebookclone.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.facebookclone.R
import com.example.facebookclone.database.UserDao
import com.example.facebookclone.database.UserRepository
import com.example.facebookclone.database.UserRoomDatabase
import com.example.facebookclone.view.login.LoginActivity
import com.example.facebookclone.view.login.ProfileLoginActivity
import com.example.facebookclone.view.register.JoinFacebookActivity

class SplashScreenActivity : AppCompatActivity() {
    private var userRepository : UserRepository ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        userRepository = UserRepository(UserRoomDatabase.getDatabase(this).userDao())

        if (userRepository?.allUsers!!.isEmpty()){
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(SplashScreenActivity@this, LoginActivity::class.java))
            }, 2000)
            // dieu huong login

        }else{
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(SplashScreenActivity@this, ProfileLoginActivity::class.java))
            }, 2000)
            // dieu huong man chon user
        }


        // logic cua phan authoritor




    }
}