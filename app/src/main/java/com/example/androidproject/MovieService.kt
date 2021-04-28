package com.example.androidproject

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("posts")
    fun getCurrentWeatherData(): Call<List<MovieResponse>>
}