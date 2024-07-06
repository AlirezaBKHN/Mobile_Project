package com.example.mobile_project

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.launch

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private lateinit var title: TextView
    private var cellType: String = ""

    private val mainViewModel: MainViewModel by viewModels {
        CellInfoViewModelFactory((application as MyApplication).mainRepository)
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        cellType = intent.getStringExtra("cellType") ?: ""
        title = findViewById(R.id.gen)
        title.text = cellType
        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermission()
            return
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val userLocation = LatLng(location.latitude, location.longitude)
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 12f))
                }
            }

        lifecycleScope.launch {
            when (cellType) {
                "GSM" -> {
                    val gsmCells = mainViewModel.getAllGsmCells()
                    gsmCells.forEach { gsmCell ->
                        addCellMarker(LatLng(gsmCell.lat, gsmCell.lon), gsmCell.toString(), gsmCell.rxLev)
                    }
                }
                "UMTS" -> {
                    val umtsCells = mainViewModel.getAllUmtsCells()
                    umtsCells.forEach { umtsCell ->
                        addCellMarker(LatLng(umtsCell.lat, umtsCell.lon), umtsCell.toString(), umtsCell.RSCP)
                    }
                }
                "LTE" -> {
                    val lteCells = mainViewModel.getAllLteCells()
                    lteCells.forEach { lteCell ->
                        addCellMarker(LatLng(lteCell.lat, lteCell.lon), lteCell.toString(), lteCell.RSRP)
                    }
                }
            }
        }
    }

    private fun addCellMarker(position: LatLng, title: String, signalStrength: Int) {
        // Signal strength categories in dBm
        val markerColor = when {
            signalStrength >= -70 -> BitmapDescriptorFactory.HUE_GREEN    // Excellent
            signalStrength in -90..-71 -> BitmapDescriptorFactory.HUE_YELLOW // Good
            else -> BitmapDescriptorFactory.HUE_RED                        // Poor
        }

        googleMap.addMarker(
            MarkerOptions()
                .position(position)
                .title(title)
                .icon(BitmapDescriptorFactory.defaultMarker(markerColor))
        )
    }

    private fun requestLocationPermission() {
        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                onMapReady(googleMap)
            } else {
                Toast.makeText(this, "Location permission is required", Toast.LENGTH_SHORT).show()
            }
        }
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }
}
