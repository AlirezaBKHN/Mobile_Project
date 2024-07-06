package com.example.mobile_project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_project.cellData.*

class DisplayDataActivity : AppCompatActivity() {

    private val cellInfoViewModel: CellInfoViewModel by viewModels()
    private lateinit var dataListAdapter: DataListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_data)

        val recyclerView: RecyclerView = findViewById(R.id.dataRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        dataListAdapter = DataListAdapter(emptyList()) // Initialize with empty list
        recyclerView.adapter = dataListAdapter

        loadDataFromViewModel()
    }

    private fun loadDataFromViewModel() {
        cellInfoViewModel.allCellInfo.observe(this, Observer { cellInfoList ->
            dataListAdapter.setData(cellInfoList)
        })


    }
}
