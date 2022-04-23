package com.example.facebookclone.view.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.facebookclone.R
import com.example.facebookclone.database.UserRepository
import com.example.facebookclone.database.UserRoomDatabase
import com.example.facebookclone.model.User
import com.example.facebookclone.model.UserSaved
import com.example.facebookclone.utils.COLLECTION_PATH_USER
import com.example.facebookclone.view.adapter.ProfileUserAdapter
import com.example.facebookclone.view.register.WhatYourNameActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_profile_login.*

class ProfileLoginActivity : AppCompatActivity() {

    private var profileAdapter: ProfileUserAdapter? = null
    private var listUser: List<UserSaved>? = null
    private var userRepository: UserRepository? = null
    private var db: FirebaseFirestore? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_login)

        db = Firebase.firestore

        btn_create_account.setOnClickListener {
            val create = Intent(this, WhatYourNameActivity::class.java)
            startActivity(create)
        }

        ln_log_another_account.setOnClickListener {
            val login = Intent(this, LoginActivity::class.java)
            startActivity(login)
        }

        userRepository = UserRepository(UserRoomDatabase.getDatabase(this).userDao())

        listUser = userRepository?.allUsers

        profileAdapter = ProfileUserAdapter(this, listUser = listUser) { user ->
            db?.collection(COLLECTION_PATH_USER)!!.document(user.phoneNumber).get()

                .addOnSuccessListener { document ->
                    if (document != null) {
                        val userCast = document.toObject<User>()
                        if (userCast?.password == user.password) {
                            //login
                        } else {
                            //
                            showSnackBar("Password wrong, please check again !")
                        }
                    }
                }
                .addOnFailureListener { exception ->
                    showSnackBar("Username and password don't match, please try again")
                }
        }


        rv_profile_login.layoutManager = LinearLayoutManager(this)
        rv_profile_login.setHasFixedSize(true)
        rv_profile_login.adapter = profileAdapter
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(container, message, Snackbar.LENGTH_LONG).show()
    }
}