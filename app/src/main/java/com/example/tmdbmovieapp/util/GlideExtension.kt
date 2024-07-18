package com.example.tmdbmovieapp.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.centerCropTransform
import com.example.tmdbmovieapp.R

fun ImageView.loadCircleImage(path: String?) {
    Glide.with(this.context).load(Contants.IMAGE_BASE_URL + path)
        .apply(centerCropTransform().error(R.drawable.baseline_error_24).circleCrop()).into(this)
}