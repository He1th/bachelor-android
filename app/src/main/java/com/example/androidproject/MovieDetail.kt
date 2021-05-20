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
        val movieID = intent.getIntExtra("id", 0);

        val moviePoster: ImageView = findViewById(R.id.poster);
        val movieName: TextView = findViewById(R.id.tv_name);
        val isLiked: CheckBox = findViewById(R.id.isLiked);

        Glide.with(getApplicationContext())
                .load("https://image.tmdb.org/t/p/original/" + posterPath)
                .into(moviePoster);

        val like = db.getLike(movieID);
        if(like !== null){
            isLiked.isChecked = true;
        }

        isLiked.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this,isChecked.toString(),Toast.LENGTH_SHORT).show()

            if(isChecked){
                val newLike = Likes(movieID);

                Log.d("test", "added")
                db.addLike(newLike)
            }else{

                db.deleteLike(movieID);

                Log.d("test", "new")
            }
        }

        movieName.text = name;

    }
}