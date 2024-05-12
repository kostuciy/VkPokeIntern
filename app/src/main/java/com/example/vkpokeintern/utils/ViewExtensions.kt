package com.example.vkpokeintern.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.vkpokeintern.R

object ViewExtensions {

    fun ImageView.loadFromUrl(url: String) {
        Glide.with(this)
            .load(url)
            .placeholder(R.drawable.pokemon_logo)
            .into(this)
    }
}
