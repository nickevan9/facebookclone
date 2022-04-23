package com.example.facebookclone.view.homescreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.example.facebookclone.NotificationsFragment
import com.example.facebookclone.R
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        top_appbar.setNavigationOnClickListener {

        }

        top_appbar.setOnMenuItemClickListener { menuItem->
            when(menuItem.itemId){
                R.id.homeFragment->{
                    true
                }
                R.id.friendRequestFragment->{
                    true
                }
                R.id.personalProfileFragment->{
                    true
                }
                R.id.watchVideoFragment->{
                    true
                }
                R.id.notificationsFragment->{
                    true
                }
                R.id.menuFragment->{
                    true
                }
                else-> false
            }

        }
    }
}