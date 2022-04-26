package com.example.facebookclone.view.homescreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.facebookclone.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_posts_home.*


class PostsHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts_home)
        clickOpenBottomSheetDialog()
        initview()
    }


    private fun initview(){
        ln_object_post.setOnClickListener {
            val intent =Intent(this,ObjectPostHomeActivity::class.java)
            startActivity(intent)
        }

        iv_back.setOnClickListener {
            finish()
        }

        im_post_more.setOnClickListener {
            clickOpenBottomSheetDialog()
        }
    }
    private fun clickOpenBottomSheetDialog(){
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.activity_home_bottom_sheet,null)
        dialog.behavior.isHideable = true
        dialog.behavior.isFitToContents = true
        dialog.behavior.state = (BottomSheetBehavior.STATE_HALF_EXPANDED)
        dialog.setContentView(view)
        dialog.show()

    }


}