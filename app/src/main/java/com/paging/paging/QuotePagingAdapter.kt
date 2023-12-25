package com.paging.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.paging.databinding.QuoteItemBinding
import com.paging.model.Results

class QuotePagingAdapter :
    PagingDataAdapter<Results, QuotePagingAdapter.QuotePAgingViewHolder>(ResultsDiffUtilCallback()) {

    override fun onBindViewHolder(holder: QuotePAgingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotePAgingViewHolder {
        return QuotePAgingViewHolder(
            QuoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class QuotePAgingViewHolder(private val binding: QuoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Results?) {
            item?.let {
                binding.tvContent.text = it.content
            }
        }
    }
}

class ResultsDiffUtilCallback : DiffUtil.ItemCallback<Results>() {

    override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
        return oldItem._id == newItem._id
    }

    override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
        return oldItem == newItem
    }

}