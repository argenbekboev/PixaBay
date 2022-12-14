package com.example.pixabay

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.pixabay.databinding.ItemImageBinding

class ImageAdapter(val list: List<ImageModel>): RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    class ImageViewHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root){


        fun onBind(imageModel: ImageModel) {
            binding.imageView.load(imageModel.largeImageURL)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}