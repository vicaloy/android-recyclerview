package com.valoy.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.valoy.recyclerview.databinding.RowItemBinding

class CustomAdapter(private val dataSet: List<Item>, private val onItemClick: (Item) -> Unit) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(binding: RowItemBinding, onItemClick: (Item) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        private val titleTextView: TextView = binding.titleTextview
        private val descriptionTextView: TextView = binding.descriptionTextview
        private var currentItem: Item? = null

        init {
            binding.root.setOnClickListener {
                currentItem?.let {
                    onItemClick(it)
                }
            }
        }

        fun bind(item: Item) {
            currentItem = item
            titleTextView.text = item.title
            descriptionTextView.text = item.description
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RowItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size
}
