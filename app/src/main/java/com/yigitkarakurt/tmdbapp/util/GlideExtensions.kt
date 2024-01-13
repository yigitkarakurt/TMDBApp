package com.yigitkarakurt.tmdbapp.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.centerCropTransform
import com.yigitkarakurt.tmdbapp.R

fun ImageView.loadCircleImage(path: String?){
    Glide.with(this.context).load(Constants.IMAGE_BASE_URL + path)
        .apply(centerCropTransform()
        .error(R.drawable.ic_error).circleCrop()).into(this)
}