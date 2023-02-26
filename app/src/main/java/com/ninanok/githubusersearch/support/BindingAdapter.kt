package com.ninanok.githubusersearch.support

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ninanok.githubusersearch.R

object BindingAdapter {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String){
        Glide.with(imageView.context)
            .load(url)
            .error(R.drawable.ic_launcher_background)
            .centerCrop()
            .into(imageView)
    }
}