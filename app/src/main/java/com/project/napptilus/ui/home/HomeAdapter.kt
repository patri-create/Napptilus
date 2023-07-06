package com.project.napptilus.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.domain.OompaLoompa
import com.project.napptilus.databinding.ItemCardOompaloompaBinding
import kotlin.properties.Delegates

class HomeAdapter(private val onClickItem: (Int) -> Unit) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    var items: List<OompaLoompa> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemCardOompaloompaBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCardOompaloompaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        onBind(holder, position)
    }

    override fun getItemCount(): Int = items.size

    private fun onBind(holder: ViewHolder, position: Int) {
        val item = items[position]
        fillOompaLoompaItem(holder.binding, item)
    }

    private fun fillOompaLoompaItem(binding: ItemCardOompaloompaBinding, item: OompaLoompa) {
        binding.item = item
        binding.root.setOnClickListener { item.id?.let { onClickItem(it) } }
    }
}