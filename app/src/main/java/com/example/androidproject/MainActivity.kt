package com.example.androidproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), OnMovieClickListener {

    var movies = ArrayList<Movie>()
    var page = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var movieAdapter = MovieAdapter(movies, this)
        val movieList: RecyclerView = findViewById(R.id.rv_movie_list)
        movieList.adapter = movieAdapter
        movieList.layoutManager = LinearLayoutManager(this);

        getCurrentData(movieAdapter, page);


        movieList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    getCurrentData(movieAdapter, page);
                }
            }
        })


    }

    private fun increatePage(){
        page++;
    }

    override fun onMovieItemClicked(position: Int) {

        val intent = Intent(this, MovieDetail::class.java)
        intent.putExtra("name", movies[position].name)
        intent.putExtra("id", movies[position].id)
        intent.putExtra("posterPath", movies[position].posterPath)
        intent.putExtra("overview", movies[position].overview)
        startActivity(intent)

    }


    private fun getCurrentData(movieAdapter: MovieAdapter, page: Int) {

        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(MovieService::class.java)
        val call: Call<MovieResponse> = service.getPopularMovies(AppId, page)

        call.enqueue(object : Callback<MovieResponse> {

            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                if (response != null) {

                    var results : ArrayList<MovieDetails> = response.body().results;

                    for (item in results)
                        movies.add(Movie(item.title, item.voteAverage, item.id, item.posterPath, item.overview))
                }
                movieAdapter.notifyDataSetChanged()
                increatePage();
            }

            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                Log.d("urlCall", t.toString());
            }

        })


    }
    companion object {

        var BaseUrl = "https://api.themoviedb.org/3/movie/"
        var AppId = "6695860255dc3d466ab6598ff64580f4"

    }

}
