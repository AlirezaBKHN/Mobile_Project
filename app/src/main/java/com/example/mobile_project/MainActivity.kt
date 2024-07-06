package com.example.mobile_project

import android.os.Bundle
import android.telephony.TelephonyManager
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
//import android.os.Bundle
import android.telephony.CellInfo
//import android.telephony.TelephonyManager
import android.widget.Button
import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import android.util.Log
import androidx.activity.viewModels
import com.example.mobile_project.cellData.*

import androidx.appcompat.app.AppCompatActivity // For Activities



class MainActivity : AppCompatActivity() {


//    private lateinit var cellInfoTextView: TextView
//    private lateinit var coordinatesTextView: TextView
//    private val PERMISSION_REQUEST_CODE = 1
//    private lateinit var cellInfoDao: CellInfoDao
//    private val cellInfoViewModel: CellInfoViewModel by viewModels()
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        cellInfoDao = MyApplication.database.cellInfoDao()
//        cellInfoTextView = findViewById(R.id.cellInfoTextView)
//
//        val myButton: Button = findViewById(R.id.myButton)
//
//        myButton.setOnClickListener {
//            checkAndRequestPermissions()
//        }
//
//        val viewDataButton: Button = findViewById(R.id.viewDataButton)
//        viewDataButton.setOnClickListener {
//            val intent = Intent(this, DisplayDataActivity::class.java)
//            startActivity(intent)
//        }
//        val viewLocationButton: Button = findViewById(R.id.viewLocation)
//        viewLocationButton.setOnClickListener {
//            val intent = Intent(this, LocationActivity::class.java)
//            startActivity(intent)
//        }
//    }
//
//    private fun checkAndRequestPermissions() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
//            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            // Request permissions
//            ActivityCompat.requestPermissions(this,
//                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE),
//                PERMISSION_REQUEST_CODE)
//        } else {
//            // Permissions already granted
//            displayCellInfo()
//
//        }
//    }
//
//    private fun displayCellInfo() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
//            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
//            val telephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
//            val cellInfoList: List<CellInfo> = telephonyManager.allCellInfo
//
//            val cellInfoStringBuilder = StringBuilder()
//            for (cellInfo in cellInfoList) {
//                Log.d("CellCount: ", "")
//                cellInfoStringBuilder.append(cellInfo.toString()).append("\n\n")
//            }
//
//            cellInfoTextView.text = cellInfoStringBuilder.toString()
//            storeCellInfoInDatabase(cellInfoStringBuilder.toString())
//        } else {
//            cellInfoTextView.text = "Permissions are not granted."
//        }
//    }
//
//    private fun storeCellInfoInDatabase(cellInfo: String) {
//        val cellInfoEntity = CellInfoEntity(info = cellInfo)
////        GlobalScope.launch(Dispatchers.IO) {
////            cellInfoDao.insert(cellInfoEntity)
////        }
//        cellInfoViewModel.insert(cellInfoEntity)
//    }
//
//
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            PERMISSION_REQUEST_CODE -> {
//                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
//                    // Permission granted
//                    Log.d("MainActivity", "Permissions granted")
//                    displayCellInfo()
//                } else {
//                    // Permission denied
//                    Log.d("MainActivity", "Permissions denied")
//                    cellInfoTextView.text = "Permission denied. Cannot display cell info."
//                    coordinatesTextView.text = "Permission denied. Cannot display coordinates."
//                }
//                return
//            }
//        }
//    }
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val buttonGatherData: Button = findViewById(R.id.button_gather_data)
    val buttonViewData: Button = findViewById(R.id.button_view_data)
    val buttonExit: Button = findViewById(R.id.button_exit)

    buttonGatherData.setOnClickListener {
        val intent = Intent(this, GatherDataActivity::class.java)
        startActivity(intent)
    }

    buttonViewData.setOnClickListener {
        val intent = Intent(this, ViewDataActivity::class.java)
        startActivity(intent)
    }

    buttonExit.setOnClickListener {
        finishAffinity()
    }
}

}





