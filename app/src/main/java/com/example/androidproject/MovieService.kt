package com.example.androidproject

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {
    @GET("popular")
    fun getCurrentWeatherData(@Query("api_key") api_key: String?, @Query("page") id: Int?): Call<MovieResponse>
}