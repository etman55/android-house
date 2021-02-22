package com.atef.clubhouse.utils

import android.widget.ImageView
import coil.api.load
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
}
