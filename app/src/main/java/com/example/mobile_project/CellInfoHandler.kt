package com.example.mobile_project

import android.os.Build
import android.telephony.*
import androidx.annotation.RequiresApi

data class GSMCellInfo(
    val rxLev: Int,
    val C1: Int,
    val C2: Int,
    val PLMN: String,
    val LAC: String,
)

data class UMTSCellInfo(
    val RSCP: Int,
    val ECN0: Int,
    val PLMN: String,
    val LAC: String,
)

data class LTECellInfo(
    val RSRP: Int,
    val RSRQ: Int,
    val CINR: Int,
    val PLMN: String,
    val TAC: String
)
class CellInfoHandler {

    val gsmCells = mutableListOf<GSMCellInfo>()
    val umtsCells = mutableListOf<UMTSCellInfo>()
    val lteCells = mutableListOf<LTECellInfo>()

    @RequiresApi(Build.VERSION_CODES.R)
    fun handleCellInfo(cellInfos: List<CellInfo>) {
        cellInfos.forEach { cellInfo ->
            when (cellInfo) {
                is CellInfoGsm -> {
                    val gsmCell = GSMCellInfo(
                        rxLev = cellInfo.cellSignalStrength.dbm,
                        C1 = cellInfo.cellIdentity.lac,
                        C2 = cellInfo.cellIdentity.cid,
                        PLMN = cellInfo.cellIdentity.mccString + cellInfo.cellIdentity.mncString,
                        LAC = cellInfo.cellIdentity.lac.toString()
                    )
                    gsmCells.add(gsmCell)
                }
                is CellInfoWcdma -> {
                    val umtsCell = UMTSCellInfo(
                        RSCP = cellInfo.cellSignalStrength.dbm,
                        ECN0 = cellInfo.cellSignalStrength.ecNo,
                        PLMN = cellInfo.cellIdentity.mccString + cellInfo.cellIdentity.mncString,
                        LAC = cellInfo.cellIdentity.lac.toString()
                    )
                    umtsCells.add(umtsCell)
                }
                is CellInfoLte -> {
                    val lteCell = LTECellInfo(
                        RSRP = cellInfo.cellSignalStrength.rsrp,
                        RSRQ = cellInfo.cellSignalStrength.rsrq,
                        CINR = cellInfo.cellSignalStrength.rssnr,
                        PLMN = cellInfo.cellIdentity.mccString + cellInfo.cellIdentity.mncString,
                        TAC = cellInfo.cellIdentity.tac.toString()
                    )
                    lteCells.add(lteCell)
                }
            }
        }
    }

    fun printCellInfo() {
        println("GSM Cells: $gsmCells")
        println("UMTS Cells: $umtsCells")
        println("LTE Cells: $lteCells")
    }
    fun getGSMCells(): MutableList<GSMCellInfo> {
        return gsmCells
    }
    fun getUMTSCells(): MutableList<UMTSCellInfo>{
        return  umtsCells
    }
    fun getLTECells() : MutableList<LTECellInfo>{
        return lteCells
    }
}
