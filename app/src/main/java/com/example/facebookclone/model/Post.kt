package com.example.facebookclone.model

import java.io.Serializable

data class Post(
    var idPost: Int = 0,
    var listFile : String ="",
    var typeFile : String = "",
    var likeTotal : Int = 0,
    var commentTotal : Int = 0,
    var shareTotal : Int = 0,
    var createAt : String = "",
    var createBy : String = ""
):Serializable
