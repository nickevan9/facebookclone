package com.example.facebookclone.view.register

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chivorn.datetimeoptionspicker.DateTimePickerView
import com.example.facebookclone.R
import kotlinx.android.synthetic.main.activity_register_birthday_activity.*
import java.text.SimpleDateFormat
import java.util.*

class RegisterBirthdayActivity : AppCompatActivity() {

    private var pvTime: DateTimePickerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_birthday_activity)


        initTimePicker()


    }

    private fun initTimePicker() {
        val selectedDate = Calendar.getInstance()
        val startDate = Calendar.getInstance()
        startDate[2013, 0] = 23
        val endDate = Calendar.getInstance()
        endDate[2019, 11] = 28
        pvTime = DateTimePickerView.Builder(
            this
        ) { date, v -> //选中事件回调
            Toast.makeText(this,getTime(date),Toast.LENGTH_SHORT).show()
        }
            .setLayoutRes(
                R.layout.datetimeoptionspicker_custom_time
            ) { v ->

            }
            .setType(booleanArrayOf(true, true, true, false, false, false))
            .setLabel("", "", "", "", "", "") //设置空字符串以隐藏单位提示   hide label
            .setDividerColor(Color.DKGRAY)
            .setContentSize(20)
            .setDate(selectedDate)
            .setRangDate(startDate, selectedDate)
            .setDecorView(frame_picker_view) //非dialog模式下,设置ViewGroup, pickerView将会添加到这个ViewGroup中
            .setBackgroundId(0x00000000)
            .setOutSideCancelable(false)
            .build()
        pvTime?.setKeyBackCancelable(false) //系统返回键监听屏蔽掉
        pvTime?.show()
    }

    private fun getTime(date: Date): String? { //可根据需要自行截取数据显示
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return format.format(date)
    }
}