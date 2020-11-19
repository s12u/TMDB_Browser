package com.tistory.mybstory.tmdbbrowser.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tistory.mybstory.tmdbbrowser.databinding.ItemSearchResultBinding
import com.tistory.mybstory.tmdbbrowser.model.Title

class MovieSearchResultAdapter constructor(
    val itemCallback: (Title) -> Unit
) : PagingDataAdapter<Title, SearchResultViewHolder>(TitleDiffCallback) {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(
            recyclerView.context,
            RecyclerView.VERTICAL,
            false
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        return SearchResultViewHolder(
            ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ).apply {
            itemView.setOnClickListener { _ ->
                getItem(bindingAdapterPosition)?.let {
                    itemCallback(it)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}

class SearchResultViewHolder(
    private val binding: ItemSearchResultBinding
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
