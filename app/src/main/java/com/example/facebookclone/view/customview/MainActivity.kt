package com.example.facebookclone.view.customview

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.facebookclone.R
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import android.view.View

import androidx.annotation.NonNull
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback


class MainActivity : AppCompatActivity() {

    private var bottomSheetBehavior: BottomSheetBehavior<*>? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomSheetBehavior = BottomSheetBehavior.from(container_bottom_sheet)

        bottomSheetBehavior?.peekHeight = 200

        bottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
        bottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED

        bottomSheetBehavior?.addBottomSheetCallback(object : BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_DRAGGING, BottomSheetBehavior.STATE_EXPANDED -> {
                        Log.d("hunghkp", "onStateChanged: STATE_DRAGGING")
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        Log.d("hunghkp", "onStateChanged: STATE_COLLAPSED")
                        bottomSheet.visibility = View.GONE
                    }
                    else -> {
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

                Log.d("hunghkp", "onStateChanged: onSlide")
            }
        })


    }
}