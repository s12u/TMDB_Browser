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

class BackdropPagerAdapter: RecyclerView.Adapter<BackdropViewHolder>() {

    private val backdropImagesList = mutableListOf<MovieImage>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackdropViewHolder {
        val binding = DataBindingUtil.inflate<ItemBackdropBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_backdrop,
            parent,
        false)

        return BackdropViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BackdropViewHolder, position: Int) {
        holder.binding.backdrop = backdropImagesList[position]
    }

    override fun getItemCount() = backdropImagesList.size

    fun submitList(imagesList: List<MovieImage>) {
        backdropImagesList.addAll(imagesList)
        notifyDataSetChanged()
    }
}

class BackdropViewHolder(val binding: ItemBackdropBinding): RecyclerView.ViewHolder(binding.root)

