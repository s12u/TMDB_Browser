package com.tistory.mybstory.tmdbbrowser.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:url", "app:errorDrawable", requireAll = false)
fun bindImageView(view: ImageView, url: String?, error: Drawable?) {
    Glide.with(view)
        .load(url)
        .error(error)
        .into(view)
}
