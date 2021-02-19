package com.blandinf.neighbors.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blandinf.neighbors.R
import com.blandinf.neighbors.databinding.NeighboardItemBinding
import com.blandinf.neighbors.models.Neighbor
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListNeighborsAdapter(items: List<Neighbor>, val callback: ListNeighborHandler) : RecyclerView.Adapter<ListNeighborsAdapter.ViewHolder>() {
    private var mNeighbours: List<Neighbor> = items
    private lateinit var binding: NeighboardItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = NeighboardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val neighbour: Neighbor = mNeighbours[position]
        // Display Neighbour Name
        holder.binding.itemListName.text = neighbour.name
        val context = binding.root.context
        // Display Neighbour Avatar
        Glide.with(context)
                .load(neighbour.avatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_person)
                .skipMemoryCache(false)
                .into(binding.itemListAvatar)

        holder.binding.itemListDeleteButton.setOnClickListener {
            callback.onDeleteNeibor(neighbor = neighbour)
        }
    }

    override fun getItemCount(): Int {
        return mNeighbours.size
    }

    class ViewHolder(val binding: NeighboardItemBinding) : RecyclerView.ViewHolder(binding.root)
}