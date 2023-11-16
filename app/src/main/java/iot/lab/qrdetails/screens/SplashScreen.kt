package iot.lab.qrdetails.screens

import android.content.res.Configuration
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import iot.lab.qrdetails.R
import iot.lab.qrdetails.theme.QRDetailsTheme
import kotlinx.coroutines.delay

// Preview Function
@Preview("Light")
@Preview(
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
private fun DefaultPreview1() {
    QRDetailsTheme {
        Surface {
            SplashScreen {}
        }
    }
}

@Composable
fun SplashScreen(navigateToHome: () -> Unit) {

    LaunchedEffect(Unit) {
        delay(3000)
        navigateToHome()
    }

    val waveColor = Color(0xFF5195A7).copy(0.125f)

    // Configure how animation feels
    val waveInsertionTime = 1000
    val waveGrowthTime = 3000
    val waveDecayTime = 1000

    // Internal helpers for animation persistence
    var animationProgress by remember { mutableIntStateOf(0) }
    var lastAnimationConstant by remember { mutableLongStateOf(System.currentTimeMillis()) }
    val animationHelper = rememberInfiniteTransition(label = "")
    val animationConstant by animationHelper.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "Animation constant"
    )

    // Delta time calculation
    val newTime = System.currentTimeMillis()
    animationProgress += (newTime - lastAnimationConstant + animationConstant).toInt()
    lastAnimationConstant = newTime

    // Wave animation states
    var lastInsertion by remember { mutableIntStateOf(-waveInsertionTime) }
    val waves by remember { mutableStateOf(mutableListOf<Int>()) }

    // Main UI Logic starts here!
    Box {
        Canvas(modifier = Modifier.fillMaxSize()) {

            // Get bounds for wave radius
            val min = size.width / 4
            val max = size.height / 1.5f

            // If is recording, give birth to new waves
            if (animationProgress - lastInsertion > waveInsertionTime) {
                waves.add(animationProgress)
                lastInsertion = animationProgress
            }

            // Game step for the waves
            waves.forEach {
                val age = animationProgress - it

                // Animation 1: Growth
                if (age < waveGrowthTime) {
                    drawCircle(
                        color = waveColor,
                        radius = min + (max - min) * age / waveGrowthTime,
                        center = center,
                        alpha = 1f
                    )
                }

                // Animation 2: Fade out
                else if (age - waveGrowthTime < waveDecayTime) {
                    drawCircle(
                        color = waveColor,
                        radius = max,
                        center = center,
                        alpha = (1 - (age - waveGrowthTime) * 1f / waveDecayTime)
                    )
                }
            }

            // Dispose off unneeded waves
            waves.removeIf { animationProgress - it > waveGrowthTime + waveDecayTime }
        }

        // Start/Stop Recording button
        Image(
            painter = painterResource(id = R.drawable.iot_logo),
            modifier = Modifier
                .align(Alignment.Center)
                .clip(CircleShape)
                .fillMaxWidth(0.5f)
                .aspectRatio(1f),
            contentDescription = "Innovance version 2 Logo"
        )
    }
}