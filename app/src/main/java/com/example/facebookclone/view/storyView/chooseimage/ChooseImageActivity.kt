package com.example.facebookclone.view.storyView.chooseimage

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.facebookclone.R
import com.example.facebookclone.model.GalleryPicture
import com.example.facebookclone.view.storyView.adapter.GalleryPicturesAdapter
import com.example.facebookclone.view.storyView.adapter.SpaceItemDecoration
import kotlinx.android.synthetic.main.activity_choose_image.*

class ChooseImageActivity : AppCompatActivity() {

    private val adapter by lazy {
        GalleryPicturesAdapter(pictures, 10,this@ChooseImageActivity,)
    }

    private val galleryViewModel: GalleryViewModel by viewModels()

    private val pictures by lazy {
        ArrayList<GalleryPicture>(galleryViewModel.getGallerySize(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_image)

        requestReadStoragePermission()
    }

    private fun requestReadStoragePermission() {
        val readStorage = Manifest.permission.READ_EXTERNAL_STORAGE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(
                this,
                readStorage
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(readStorage), 3)
        } else init()
    }

    private fun init() {
        val layoutManager = GridLayoutManager(this, 3)
        val pageSize = 20
        rv.layoutManager = layoutManager
        rv.addItemDecoration(SpaceItemDecoration(8))
        rv.adapter = adapter

        adapter.setOnClickListener { galleryPicture ->
            // click image
        }

        rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (layoutManager.findLastVisibleItemPosition() == pictures.lastIndex) {
                    loadPictures(pageSize)
                }
            }
        })

        ivBack.setOnClickListener {
            onBackPressed()
        }
        loadPictures(pageSize)
    }


    private fun loadPictures(pageSize: Int) {
        galleryViewModel.getImagesFromGallery(this, pageSize) {
            if (it.isNotEmpty()) {
                pictures.addAll(it)
                adapter.notifyItemRangeInserted(pictures.size, it.size)
            }
            Log.i("GalleryListSize", "${pictures.size}")
        }
    }


    private fun showToast(s: String) = Toast.makeText(this, s, Toast.LENGTH_SHORT).show()

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            init()
        else {
            showToast("Permission Required to Fetch Gallery.")
            super.onBackPressed()
        }
    }
}