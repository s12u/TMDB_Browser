package com.tistory.mybstory.tmdbbrowser.util

import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
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

@BindingAdapter("app:url", requireAll = false)
fun bindImageView(view: ImageView, url: String?) {
    val crossFadeTransition = DrawableTransitionOptions.withCrossFade(300)

    Glide.with(view)
        .load(url)
        .transition(crossFadeTransition)
        .placeholder(R.drawable.bg_image_placeholder)
        .error(R.drawable.bg_no_image)
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
                setChipBackgroundColorResource(R.color.colorAccent)
            }.also { chipGroup.addView(it) }
        }
    }
}

@BindingAdapter("app:queryHandler")
fun bindQueryOnSearchView(view: SearchView, handler: (String?) -> Unit) {
    view.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            handler.invoke(newText)
            return false
        }
    })

}

@BindingAdapter("app:ellipsizedText")
fun bindEllipsizedText(view: TextView, text: String) {
    val result = if (text.length > 25) {
        text.dropLast(3).plus("...")
    } else text
    view.text = result
}
