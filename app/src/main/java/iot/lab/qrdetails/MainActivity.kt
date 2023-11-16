package iot.lab.qrdetails

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import iot.lab.qrdetails.core.scanner.CustomBarCodeScanner
import iot.lab.qrdetails.presentation.navigation.NavGraph
import iot.lab.qrdetails.core.theme.QRDetailsTheme
import iot.lab.qrdetails.presentation.viewmodel.CodeScannerViewModel

@SuppressLint("CustomSplashScreen")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QRDetailsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    // View Model Variable
                    val viewModel = viewModel<CodeScannerViewModel>()

                    // Setting the Scanner in the View Model
                    viewModel.setScanner(CustomBarCodeScanner(LocalContext.current))

                    // Nav controller variable along with the NavGraph of the App
                    val navHostController = rememberNavController()
                    NavGraph(
                        navController = navHostController,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}