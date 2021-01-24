package com.example.simplegallery.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simplegallery.databinding.GridViewItemBinding
import com.example.simplegallery.network.GalleryProperty

class GridAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<GalleryProperty, GridAdapter.GalleryPropertyViewHolder>(DiffCallback) {
    class GalleryPropertyViewHolder(private var binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(galleryProperty: GalleryProperty) {
            binding.property = galleryProperty
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<GalleryProperty>() {
        override fun areItemsTheSame(oldItem: GalleryProperty, newItem: GalleryProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: GalleryProperty,
            newItem: GalleryProperty
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GalleryPropertyViewHolder {
        return GalleryPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: GalleryPropertyViewHolder, position: Int) {
        val galleryProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(galleryProperty)
        }
        holder.bind(galleryProperty)
    }

    class OnClickListener(val clickListener: (galleryProperty:GalleryProperty) -> Unit) {
        fun onClick(galleryProperty: GalleryProperty) = clickListener(galleryProperty)
    }
}