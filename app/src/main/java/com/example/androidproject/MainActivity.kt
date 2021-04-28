package com.example.androidproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), OnMovieClickListener {

    var movies = ArrayList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //createTestMovieList();
        var movieAdapter = MovieAdapter(movies, this)
        val movieList: RecyclerView = findViewById(R.id.rv_movie_list)
        movieList.adapter = movieAdapter
        movieList.layoutManager = LinearLayoutManager(this);
        getCurrentData(movieAdapter);

    }

    fun createTestMovieList(){

        for(i in 1..100){
            movies.add(Movie("Navn" + i, "Test genre", "2001"))
        }

    }

    override fun onMovieItemClicked(position: Int) {
        //Toast.makeText(this, "movie" + position + "Clicked", Toast.LENGTH_LONG).show();

        val intent = Intent(this, MovieDetail::class.java)
        intent.putExtra("name", movies[position].name)
        startActivity(intent)

    }

    private val recyclerDataArrayList: ArrayList<MovieResponse>? = null

    private fun getCurrentData(movieAdapter: MovieAdapter) {

        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WeatherService::class.java)
        val call: Call<List<MovieResponse>> = service.getCurrentWeatherData()

        call.enqueue(object : Callback<List<MovieResponse>> {

            override fun onResponse(call: Call<List<MovieResponse>>?, response: Response<List<MovieResponse>>?) {
                if (response != null) {
                    for (item in response.body())
                        movies.add(Movie(item.title, "asdas", "asdas"))
                }
                movieAdapter.notifyDataSetChanged()
                Log.d("asd", "DET VIRKER");
            }

            override fun onFailure(call: Call<List<MovieResponse>>?, t: Throwable?) {
                Log.d("asd", "asdåoajsdsaj");
            }
        })


    }
    companion object {

        var BaseUrl = "https://jsonplaceholder.typicode.com/"
        var AppId = "20480b32fedffa9559da9ca0b82f4673"
        var lat = "35"
        var lon = "139"
    }

}
