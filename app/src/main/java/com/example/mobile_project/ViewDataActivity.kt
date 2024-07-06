package com.example.mobile_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ViewDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_data)

        val buttonViewGsm: Button = findViewById(R.id.button_view_gsm)
        val buttonViewUmts: Button = findViewById(R.id.button_view_umts)
        val buttonViewLte: Button = findViewById(R.id.button_view_lte)

        buttonViewGsm.setOnClickListener {
            navigateToMap("GSM")
        }

        buttonViewUmts.setOnClickListener {
            navigateToMap("UMTS")
        }

        buttonViewLte.setOnClickListener {
            navigateToMap("LTE")
        }
    }

    private fun navigateToMap(cellType: String) {
        val intent = Intent(this, MapActivity::class.java)
        intent.putExtra("cellType", cellType)
        startActivity(intent)
    }
}
