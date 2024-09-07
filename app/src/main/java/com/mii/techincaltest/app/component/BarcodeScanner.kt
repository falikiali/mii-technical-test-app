package com.mii.techincaltest.app.component

import android.content.Context
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import kotlinx.coroutines.tasks.await

class BarcodeScanner(private val context: Context) {
    private val options = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
        .build()

    private val scanner = GmsBarcodeScanning.getClient(context, options)

    suspend fun startScan(): String? {
        return try {
            scanner.startScan().await().rawValue.toString()
        } catch (e: Exception) {
            null
        }
    }
}