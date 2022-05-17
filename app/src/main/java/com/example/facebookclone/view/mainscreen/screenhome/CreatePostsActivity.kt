package com.example.facebookclone.view.mainscreen.screenhome

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.facebookclone.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_create_post.*
import kotlinx.android.synthetic.main.layout_bottom_create_post.*
import kotlinx.android.synthetic.main.layout_menu_bottom_create_post.*
import java.io.ByteArrayOutputStream
import java.io.File


class CreatePostsActivity : AppCompatActivity() {
    val storage = Firebase.storage
    private var bottomSheetBehavior: BottomSheetBehavior<*>? = null
    private val storageRef = Firebase.storage.reference

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            val data = intent?.getStringExtra("KEY_PATH_IMAGE")


            Glide.with(this).load(data).into(img_pick)
            img_pick.visibility = View.VISIBLE
            ln_add_images.visibility = View.VISIBLE
            et_thinking_pos.isCursorVisible = true
            card_menu.visibility = View.GONE
            ln_options_post_home.visibility = View.VISIBLE

//                        val riversRef = storageRef.child("post/${file.lastPathSegment}")
//            img_pick.isDrawingCacheEnabled = true
//            img_pick.buildDrawingCache()
//            val bitmap = (img_pick.drawable as BitmapDrawable).bitmap
//            val bitmap = BitmapFactory.decodeResource(getResources(),
//                R.drawable.ic_add_)
//            val baos = ByteArrayOutputStream()
////            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
//            val dataq = baos.toByteArray()
//
//            var uploadTask = storageRef.putBytes(dataq)
//            uploadTask.addOnFailureListener {
//                Log.d("thaonh", ":${it.message} ")
//                // Handle unsuccessful uploads
//            }.addOnSuccessListener { taskSnapshot ->
//                // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
//                Log.d("thaonh", ":addOnSuccessListener ")
//                // ...
//            }

            var file = Uri.fromFile(File(data))
            Log.d("thaonh", "${file.toString()}")
            val riversRef = storageRef.child("post/${file.lastPathSegment}")
            var uploadTask = riversRef.putFile(file)

// Register observers to listen for when the download is done or if it fails
            uploadTask.addOnFailureListener {
                // Handle unsuccessful uploads
                Log.d("thaonh", ":${it.message} ")
            }.addOnSuccessListener { taskSnapshot ->
                // taskSnapshot.metadata contains file metadata such as size, content-type, etc.

                // ...
                Log.d("thaonh", ":addOnSuccessListener ")
            }

            // Handle the Intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)
        initView()
    }


    @SuppressLint("ResourceAsColor")
    private fun initView(){
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
            hideSoftKeyboard(this,container)
        }

        iv_images.setOnClickListener {
            startForResult.launch(Intent(this, PickImagePostActivity::class.java))
        }

        ln_images.setOnClickListener {
            startForResult.launch(Intent(this, PickImagePostActivity::class.java))
        }

        iv_emoji.setOnClickListener {
            val intent =Intent(this,EmojiActionActivity::class.java)
            startActivity(intent)
        }

        et_thinking_pos.setOnClickListener {
            et_thinking_pos.isFocusable = true
        }

        et_thinking_pos.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus){
                et_thinking_pos.isCursorVisible = true
                card_menu.visibility = View.GONE
                ln_options_post_home.visibility = View.VISIBLE
            }
        }


        et_thinking_pos.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }
            override fun afterTextChanged(editable: Editable) {
                if(et_thinking_pos.text.toString().trim().isNotEmpty()){
                    btn_post.isEnabled = true
                    btn_post.setBackgroundResource(R.drawable.rounded_button_posts)
                    btn_post.setTextColor(R.color.black_mode)
                }
                else{
                    if(btn_post.isEnabled){
                        btn_post.isEnabled = false
                        btn_post.setBackgroundResource(R.drawable.rounded_home_post_file)
                        btn_post.setTextColor(R.color.general_grey)
                    }

                }
            }
        })

        btn_post.setOnClickListener {
        }


        initBottomSheet()
    }

//    override fun onResume() {
//        super.onResume()
//        et_thinking_pos.clearFocus();
//        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//    }

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

    private fun hideKeyboard(activity: Activity) {
        val view = activity.currentFocus
        val methodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        methodManager.hideSoftInputFromWindow(view!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

    }

    fun hideSoftKeyboard(activity: Activity, view: View?) {
        val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun showKeyboard(activity: Activity) {
        card_menu.isVisible = false
        val view = activity.currentFocus
        val methodManager = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    }

    private fun View.showKeyboard() {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }



}

