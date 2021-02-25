package com.atef.clubhouse.utils

import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView

interface ImageLoader {
    fun loadImage(view: ImageView, url: String)
    fun loadImage(view: ImageView, drawable: BitmapDrawable)
}
