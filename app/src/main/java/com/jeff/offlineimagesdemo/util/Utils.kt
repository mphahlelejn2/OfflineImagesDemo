package com.jeff.offlineimagesdemo.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import android.R
import com.jeff.offlineimagesdemo.view.MainActivity


class Utils {
    companion object {
        fun isInternetConnected(context: Context): Boolean {
            val connectivity = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivity == null) {
                return false
            } else {
                val info = connectivity.allNetworkInfo
                if (info != null) {
                    for (i in info.indices) {
                        if (info[i].state == NetworkInfo.State.CONNECTED) {
                            return true
                        }
                    }
                }
            }
            return false
        }


        fun loadImage(context: Context, imageView: ImageView, url: String) {
            Glide.with(context)
                .load(url)
                .apply(RequestOptions().placeholder(com.jeff.offlineimagesdemo.R.drawable.image_error)
                    .error(com.jeff.offlineimagesdemo.R.drawable.image_error))
                .into(imageView)
        }
    }
}