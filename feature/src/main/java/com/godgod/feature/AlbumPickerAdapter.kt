package com.godgod.feature

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.godgod.feature.databinding.ViewholderAlbumItemBinding
import com.godgod.share.model.AlbumData

class AlbumPickerAdapter : PagingDataAdapter<AlbumData, AlbumPickerViewHolder>(diffUtil) {

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<AlbumData>() {
            override fun areItemsTheSame(oldItem: AlbumData, newItem: AlbumData): Boolean {
                return oldItem.uri == newItem.uri
            }

            override fun areContentsTheSame(oldItem: AlbumData, newItem: AlbumData): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumPickerViewHolder {
        val binding =
            ViewholderAlbumItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumPickerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumPickerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class AlbumPickerViewHolder(private val binding: ViewholderAlbumItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: AlbumData?) {
        binding.data = data
        binding.executePendingBindings()
    }

}