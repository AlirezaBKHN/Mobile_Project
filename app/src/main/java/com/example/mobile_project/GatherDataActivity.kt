package com.example.mobile_project

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.example.mobile_project.cellData.*
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch

class GatherDataActivity : AppCompatActivity() {

    private val cellInfoViewModel: MainViewModel by viewModels {
        CellInfoViewModelFactory((application as MyApplication).mainRepository)
    }

    private val cellInfoHandler = CellInfoHandler()
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var gsmInfoList: LinearLayout
    private lateinit var umtsInfoList: LinearLayout
    private lateinit var lteInfoList: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gather_data)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val buttonGatherInfo: Button = findViewById(R.id.button_gather_info)

        gsmInfoList = findViewById(R.id.gsm_info_list)
        umtsInfoList = findViewById(R.id.umts_info_list)
        lteInfoList = findViewById(R.id.lte_info_list)

        buttonGatherInfo.setOnClickListener {
            gatherCellInfo()
        }

//        loadCellInfo()
    }

    private fun loadCellInfo(gsm: MutableList<GSMCellInfo>, umts: MutableList<UMTSCellInfo>, lte: MutableList<LTECellInfo>) {
        lifecycleScope.launch {
//            val gsmCells = cellInfoViewModel.getAllGsmCells()
//            val umtsCells = cellInfoViewModel.getAllUmtsCells()
//            val lteCells = cellInfoViewModel.getAllLteCells()

            gsmInfoList.removeAllViews()
            umtsInfoList.removeAllViews()
            lteInfoList.removeAllViews()

            gsm.forEach { gsmCell ->
                gsmInfoList.addView(createTextView("GSM Cell: $gsmCell"))
            }

            umts.forEach { umtsCell ->
                umtsInfoList.addView(createTextView("UMTS Cell: $umtsCell"))
            }

            lte.forEach { lteCell ->
                lteInfoList.addView(createTextView("LTE Cell: $lteCell"))
            }
        }
    }

    private fun gatherCellInfo() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermission()
            return
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val lat = location.latitude
                    val lon = location.longitude

                    val telephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
                    val cellInfos = telephonyManager.allCellInfo
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        cellInfoHandler.handleCellInfo(cellInfos)
                    }

                    storeCellInfoWithLocation(lat, lon)
                } else {
                    Toast.makeText(this, "Unable to get location", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun storeCellInfoWithLocation(lat: Double, lon: Double) {
        lifecycleScope.launch {
            val gsmcells = cellInfoHandler.getGSMCells()
            val umtscells= cellInfoHandler.getUMTSCells()
            val  ltecells= cellInfoHandler.getLTECells()
            gsmcells.forEach { gsmCell ->
                val gsmCellEntity = GsmCellEntity(
                    rxLev = gsmCell.rxLev,
                    C1 = gsmCell.C1,
                    C2 = gsmCell.C2,
                    PLMN = gsmCell.PLMN,
                    LAC = gsmCell.LAC,
                    lat = lat,
                    lon = lon
                )
                cellInfoViewModel.insertGsmCell(gsmCellEntity)
            }

            umtscells.forEach { umtsCell ->
                val umtsCellEntity = UmtsCellEntity(
                    RSCP = umtsCell.RSCP,
                    ECN0 = umtsCell.ECN0,
                    PLMN = umtsCell.PLMN,
                    LAC = umtsCell.LAC,
                    lat = lat,
                    lon = lon
                )
                cellInfoViewModel.insertUmtsCell(umtsCellEntity)
            }

            ltecells.forEach { lteCell ->
                val lteCellEntity = LteCellEntity(
                    RSRP = lteCell.RSRP,
                    RSRQ = lteCell.RSRQ,
                    CINR = lteCell.CINR,
                    PLMN = lteCell.PLMN,
                    TAC = lteCell.TAC,
                    lat = lat,
                    lon = lon
                )
                cellInfoViewModel.insertLteCell(lteCellEntity)
            }

            loadCellInfo(gsmcells,umtscells,ltecells)  // Reload cell info after storing new data
        }
    }

    private fun requestLocationPermission() {
        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                gatherCellInfo()
            } else {
                Toast.makeText(this, "Location permission is required", Toast.LENGTH_SHORT).show()
            }
        }
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun createTextView(text: String): TextView {
        return TextView(this).apply {
            this.text = text
            this.textSize = 16f
            this.setPadding(8, 8, 8, 8)
        }
    }
}
