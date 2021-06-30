package com.repairzone.brightontes.ui.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("tools:imgUrl")
    fun setImageFromUrl(view: ImageView, url: String?){
        if (!url.equals("") || url != null || url != ""){
            val options = RequestOptions().transform(CenterCrop())
                .diskCacheStrategy(DiskCacheStrategy.DATA)
            Glide.with(view.context)
                .load(url)
                .apply(options)
                .centerCrop()
                .into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("tools:htmlText")
    fun convertToText(view: TextView, str: String?){
        view.text = HtmlCompat.fromHtml(str!!, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}