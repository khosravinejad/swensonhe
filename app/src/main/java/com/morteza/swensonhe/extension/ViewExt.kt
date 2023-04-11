package com.morteza.swensonhe.extension

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

fun ImageView.loadInCircle(url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(this.context).load(url).circleCrop().into(this)
    }
}

fun ImageView.load(url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(this.context).load(url).into(this)
    }
}

fun ImageView.load(@DrawableRes imageRes: Int?) {
    if (imageRes != null) {
        Glide.with(this.context).load(imageRes).centerCrop().into(this)
    }
}