package com.example.ramtt.common

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.ramtt.R

/**
 * @author : Mingaleev D
 * @data : 8/01/2023
 */

fun ImageView.load(url: String){
   Glide.with(context)
      .load(url)
      .placeholder(R.drawable.ic_error)
      .into(this)
}