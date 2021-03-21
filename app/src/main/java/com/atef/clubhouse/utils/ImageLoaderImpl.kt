package com.atef.clubhouse.utils

import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import coil.api.load
import coil.transform.CircleCropTransformation
import javax.inject.Inject

class ImageLoaderImpl @Inject constructor() : ImageLoader {
    override fun loadImage(view: ImageView, url: String) {
        view.load(url) {
            crossfade(true)
            listener(onError = { _, throwable ->
                throwable.printStackTrace()
            })
        }
    }

    override fun loadImage(view: ImageView, drawable: BitmapDrawable) {
        view.load(drawable) {
            crossfade(true)
            listener(onError = { _, throwable ->
                throwable.printStackTrace()
            })
        }
    }

    override fun loadCircleImage(view: ImageView, url: String) {
        view.load(url) {
            crossfade(true)
            transformations(CircleCropTransformation())
            listener(onError = { _, throwable ->
                throwable.printStackTrace()
            })
        }
    }
}
