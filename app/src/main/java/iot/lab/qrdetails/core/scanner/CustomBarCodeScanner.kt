package iot.lab.qrdetails.core.scanner

import android.content.Context
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import java.lang.Exception


/**
 * This class provides a implementation for scanning for the bar codes
 *
 * @param context This is the context of the activity of which we are using to launch this code scanner
 */
class CustomBarCodeScanner(context: Context) {


    /**
     * This variable contains all the options for the Bar Code Scanner
     */
    private val barCodeOptions: GmsBarcodeScannerOptions = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
        .enableAutoZoom()
        .allowManualInput()
        .build()


    /**
     * This is the Scanner Variable which is used to start scanning
     */
    private val scanner: GmsBarcodeScanner = GmsBarcodeScanning.getClient(context, barCodeOptions)


    /**
     * This function starts scanning for the Bar Code and checks if the scan is a success or failure
     *
     * @param onSuccess This function is invoked when the scan is a success and the scanner gets a
     * barcode
     * @param onCancelled This function is invoked when the scan is cancelled by the user himself
     * @param onFailure This function is invoked when the scan is a failure
     */
    fun startScanner(
        onSuccess: (String) -> Unit,
        onCancelled: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {

        // Starting Scan
        scanner.startScan()

            // Success Listener
            .addOnSuccessListener { barCode ->
                onSuccess(barCode.rawValue ?: "")
            }

            // Cancel Listener
            .addOnCanceledListener {
                onCancelled()
            }

            // Failure Listener
            .addOnFailureListener {
                onFailure(it)
            }
    }
}