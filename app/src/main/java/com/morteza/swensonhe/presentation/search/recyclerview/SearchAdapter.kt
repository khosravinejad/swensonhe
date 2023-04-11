package com.morteza.swensonhe.presentation.search.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.morteza.swensonhe.databinding.ItemEmptySearchBinding
import com.morteza.swensonhe.databinding.ItemLocationSearchBinding
import com.morteza.swensonhe.domain.model.Location
import com.morteza.swensonhe.presentation.search.SearchListItem

class SearchAdapter : RecyclerView.Adapter<ViewHolder>() {

    companion object {
        private enum class ViewTypes {
            LOCATION, EMPTY
        }
    }

    private val items: MutableList<SearchListItem> = mutableListOf()
    private var itemClickListener: ((Location) -> (Unit))? = null

    fun updateItems(newItems: List<SearchListItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: ((Location) -> (Unit))? = null) {
        itemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == ViewTypes.LOCATION.ordinal) {
            SearchViewHolder(
                ItemLocationSearchBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        } else {
            EmptySearchViewHolder(
                ItemEmptySearchBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].let {
            when (it) {
                is SearchListItem.LocationItem -> (holder as SearchViewHolder).bind(it.location)
                is SearchListItem.Empty -> (holder as EmptySearchViewHolder).bind()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is SearchListItem.LocationItem -> ViewTypes.LOCATION.ordinal
            is SearchListItem.Empty -> ViewTypes.EMPTY.ordinal
        }
    }

    override fun getItemCount() = items.size

    inner class SearchViewHolder(private val binding: ItemLocationSearchBinding) :
        ViewHolder(binding.root) {
        fun bind(item: Location) {
            binding.apply {
                city.text = item.name
                region.text = " - ${item.region}"
                root.setOnClickListener {
                    itemClickListener?.invoke(item)
                }
            }
        }
    }

    inner class EmptySearchViewHolder(private val binding: ItemEmptySearchBinding) :
        ViewHolder(binding.root) {
        fun bind() {}
    }
}