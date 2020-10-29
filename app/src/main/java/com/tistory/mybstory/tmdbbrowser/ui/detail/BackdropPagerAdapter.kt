package com.tistory.mybstory.tmdbbrowser.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.mybstory.tmdbbrowser.R
import com.tistory.mybstory.tmdbbrowser.databinding.ItemBackdropBinding
import com.tistory.mybstory.tmdbbrowser.model.MovieImage

class BackdropPagerAdapter: ListAdapter<MovieImage, BackdropViewHolder>(backdropDiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackdropViewHolder {
        val binding = DataBindingUtil.inflate<ItemBackdropBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_backdrop,
            parent,
        false)

        return BackdropViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BackdropViewHolder, position: Int) {
        holder.binding.backdrop = getItem(position)
    }

    companion object {

        val backdropDiffUtilCallback = object : DiffUtil.ItemCallback<MovieImage>() {
            override fun areItemsTheSame(oldItem: MovieImage, newItem: MovieImage) =
                oldItem.filePath == newItem.filePath

            override fun areContentsTheSame(oldItem: MovieImage, newItem: MovieImage) =
                oldItem == newItem
        }
    }
}

class BackdropViewHolder(val binding: ItemBackdropBinding): RecyclerView.ViewHolder(binding.root)

