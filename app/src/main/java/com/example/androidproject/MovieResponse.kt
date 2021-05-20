package com.example.androidproject

import com.google.gson.annotations.SerializedName

class MovieResponse {

    @SerializedName("results")
    var results = ArrayList<MovieDetails>();
}

class MovieDetails {

    @SerializedName("id")
    var id: Int = 0
    @SerializedName("original_title")
    var title: String = "asds"
    @SerializedName("poster_path")
    var posterPath: String = ""
}
