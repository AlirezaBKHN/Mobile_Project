package com.example.mobile_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_project.cellData.*
import com.example.mobile_project.R

class DataListAdapter(private var dataList: List<CellInfoEntity>) :
    RecyclerView.Adapter<DataListAdapter.DataViewHolder>() {

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dataTextView: TextView = itemView.findViewById(R.id.dataTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        return DataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.dataTextView.text = currentItem.info
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(data: List<CellInfoEntity>) {
        dataList = data
        notifyDataSetChanged()
    }
}
