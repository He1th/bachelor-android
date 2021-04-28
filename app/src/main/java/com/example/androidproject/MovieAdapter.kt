package com.example.androidproject

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(private val movies : ArrayList<Movie>, private val onMovieClickListener: OnMovieClickListener) : RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_list_item,parent, false))

    }

    override fun getItemCount(): Int {
        return movies.size;
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        var movie = movies[position]

        holder.itemView.findViewById<TextView>(R.id.tv_name).text = movie.name;
        holder.itemView.findViewById<TextView>(R.id.tv_genre).text = movie.genre;
        holder.itemView.findViewById<TextView>(R.id.tv_year).text = movie.year;

        holder.itemView.setOnClickListener {
            onMovieClickListener.onMovieItemClicked(position)
        }

    }
}