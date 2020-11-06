package com.tistory.mybstory.tmdbbrowser.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.tistory.mybstory.tmdbbrowser.R
import com.tistory.mybstory.tmdbbrowser.model.Genre
import com.tistory.mybstory.tmdbbrowser.model.MovieImage
import com.tistory.mybstory.tmdbbrowser.ui.detail.BackdropPagerAdapter
import java.io.InvalidClassException

@BindingAdapter("app:url", "app:errorDrawable", requireAll = false)
fun bindImageView(view: ImageView, url: String?, error: Drawable?) {
    val crossFadeTransition = DrawableTransitionOptions.withCrossFade(300)

    Glide.with(view)
        .load(url)
        .transition(crossFadeTransition)
        .error(error)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .into(view)
}

@BindingAdapter("app:pagerImagesList")
fun bindMovieImagesOnPager(view: ViewPager2, imageList: List<MovieImage>?) {
    val adapter = when (view.adapter) {
        is BackdropPagerAdapter -> view.adapter as BackdropPagerAdapter
        else -> throw InvalidClassException("Not a pager adapter")
    }
    imageList?.also { adapter.submitList(it) }
}

@BindingAdapter("app:genreList")
fun bindGenresListOnChipGroup(chipGroup: ChipGroup, genres: List<Genre>?) {
    genres?.let {
        for (genre in genres) {
            Chip(chipGroup.context).apply {
                text = genre.name
                setTextAppearance(R.style.GenreChipTextStyle)
            }.also { chipGroup.addView(it) }
        }
    }
}