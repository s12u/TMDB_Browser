package com.tistory.mybstory.tmdbbrowser.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tistory.mybstory.tmdbbrowser.R
import com.tistory.mybstory.tmdbbrowser.databinding.ItemTitleBinding
import com.tistory.mybstory.tmdbbrowser.model.Title

class TrendingMoviesAdapter constructor(
    val itemCallback: (Title)->Unit
) : PagingDataAdapter<Title, TitleViewHolder>(TitleDiffCallback) {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(
            recyclerView.context,
            RecyclerView.HORIZONTAL,
            false
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleViewHolder {
        return TitleViewHolder(
            ItemTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ).apply {
            itemView.setOnClickListener { _ ->
                getItem(bindingAdapterPosition)?.let {
                    itemCallback(it)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: TitleViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}

class TrendingMoviesHeaderAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder> () {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(
            recyclerView.context,
            RecyclerView.VERTICAL,
            false
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val headerView = LayoutInflater.from(parent.context).inflate(R.layout.header_trending, parent, false)
        return HeaderViewHolder(headerView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 1

    inner class HeaderViewHolder(view: View): RecyclerView.ViewHolder(view)
}

class TitleViewHolder(
    private val binding: ItemTitleBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Title) = with(binding) {
        title = item
        executePendingBindings()
    }
}



object TitleDiffCallback : DiffUtil.ItemCallback<Title>() {
    override fun areItemsTheSame(oldItem: Title, newItem: Title): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Title, newItem: Title): Boolean {
        return oldItem == newItem
    }
}
