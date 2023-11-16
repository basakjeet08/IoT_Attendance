package iot.lab.qrdetails

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import iot.lab.qrdetails.navigation.NavGraph
import iot.lab.qrdetails.theme.QRDetailsTheme

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

                    // Nav controller variable along with the NavGraph of the App
                    val navHostController = rememberNavController()
                    NavGraph(navController = navHostController)
                }
            }
        }
    }
}