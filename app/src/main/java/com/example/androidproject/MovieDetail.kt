package com.example.androidproject

import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.androidproject.entity.Likes

class MovieDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val db = DatabaseHandler(this)

        val name = intent.getStringExtra("name");
        val posterPath = intent.getStringExtra("posterPath");
        val overview = intent.getStringExtra("overview");
        val movieID = intent.getIntExtra("id", 0);

        val moviePoster: ImageView = findViewById(R.id.poster);
        val movieName: TextView = findViewById(R.id.tv_name);
        val movieDescription: TextView = findViewById(R.id.description);
        val isLiked: CheckBox = findViewById(R.id.isLiked);

        Glide.with(getApplicationContext())
                .load("https://image.tmdb.org/t/p/w500/" + posterPath)
                .into(moviePoster);

        val like = db.getLike(movieID);
        if(like !== null){
            isLiked.isChecked = true;
        }

        isLiked.setOnCheckedChangeListener { _, isChecked ->

            if(isChecked){
                val newLike = Likes(movieID);

                Toast.makeText(this, "Movie has been liked", Toast.LENGTH_SHORT).show()
                Log.d("test", "added")
                db.addLike(newLike)
            }else{

                Toast.makeText(this, "Movie has been disliked", Toast.LENGTH_SHORT).show()
                db.deleteLike(movieID);

                Log.d("test", "new")
            }
        }

        movieName.text = name;
        movieDescription.text = overview;

    }
}