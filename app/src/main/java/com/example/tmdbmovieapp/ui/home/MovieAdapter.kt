package com.example.tmdbmovieapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbmovieapp.databinding.ItemHomeRecyclerViewBinding
import com.example.tmdbmovieapp.model.MovieItem
import com.example.tmdbmovieapp.util.loadCircleImage

class MovieAdapter(private  val movieList: List<MovieItem?>): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemHomeRecyclerViewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHomeRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList[position]

        holder.binding.textViewMovieTitle.text = movie?.title
        holder.binding.textViewMovieOverview.text = movie?.overview
        holder.binding.textViewMovieRating.text = movie?.voteAverage.toString()

        holder.binding.imageViewMovie.loadCircleImage(movie?.posterPath)
    }
}