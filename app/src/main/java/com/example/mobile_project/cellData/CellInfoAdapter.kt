package com.example.mobile_project.cellData

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_project.R

class CellInfoAdapter<T : Any>(private val bindView: (View, T) -> Unit,
                         private val areItemsTheSame: (oldItem: T, newItem: T) -> Boolean,
                         private val areContentsTheSame: (oldItem: T, newItem: T) -> Boolean) : ListAdapter<T, CellInfoAdapter<T>.CellInfoViewHolder>(CellInfoDiffCallback(areItemsTheSame, areContentsTheSame)) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_info_item, parent, false)
        return CellInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CellInfoViewHolder, position: Int) {
        val cellInfo = getItem(position)
        bindView(holder.itemView, cellInfo)
    }

    inner class CellInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}



class CellInfoDiffCallback<T : Any>(private val areItemsTheSame: (oldItem: T, newItem: T) -> Boolean,
                              private val areContentsTheSame: (oldItem: T, newItem: T) -> Boolean) : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return areItemsTheSame.invoke(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return areContentsTheSame.invoke(oldItem, newItem)
    }
}

