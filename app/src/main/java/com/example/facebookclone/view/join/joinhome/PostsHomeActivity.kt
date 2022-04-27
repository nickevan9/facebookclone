package com.example.facebookclone.view.homescreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.facebookclone.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_create_post.*
import kotlinx.android.synthetic.main.layout_bottom_create_post.*


class PostsHomeActivity : AppCompatActivity() {

    private var bottomSheetBehavior: BottomSheetBehavior<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)
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
            card_menu.visibility = View.VISIBLE
            ln_options_post_home.visibility = View.GONE
            bottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
        }
        if(et_thinking_pos.text.isNotEmpty()){
            btn_post.isEnabled = true
        }

        initBottomSheet()
    }

    private fun initBottomSheet(){
        bottomSheetBehavior = BottomSheetBehavior.from(card_menu)

        bottomSheetBehavior?.peekHeight = 200

        bottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
        bottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED

        bottomSheetBehavior?.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_DRAGGING, BottomSheetBehavior.STATE_EXPANDED -> {

                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        card_menu.visibility = View.GONE
                        ln_options_post_home.visibility = View.VISIBLE
                    }
                    else -> {
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
        })

    }



}