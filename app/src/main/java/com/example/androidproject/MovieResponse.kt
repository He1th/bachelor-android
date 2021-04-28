package com.example.androidproject

import com.google.gson.annotations.SerializedName

class MovieResponse {

    @SerializedName("title")
    var title: String = "asds"
    @SerializedName("userId")
    var userId: Int = 0
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("body")
    var body: String = "asd"
}
