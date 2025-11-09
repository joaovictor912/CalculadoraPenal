package org.example.calculadorapenal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.example.calculadorapenal.R
import org.example.calculadorapenal.ui.theme.AppTheme
import org.example.calculadorapenal.ui.theme.CalculadoraPenalTheme
import org.example.calculadorapenal.ui.theme.BackgroundDark
import org.example.calculadorapenal.navigation.AppNavigation
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var appTheme by remember { mutableStateOf(AppTheme.Dark) }
            var showSplash by remember { mutableStateOf(true) }

            CalculadoraPenalTheme(appTheme = appTheme) {
                if (showSplash) {
                    // SPLASH SCREEN: Tela inicial com logo grande ajustável
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(BackgroundDark),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.splash_logo),
                            contentDescription = "Logo Cespedes Lourenço",
                            modifier = Modifier.fillMaxWidth(0.95f),
                            contentScale = ContentScale.Fit
                        )

                        LaunchedEffect(Unit) {
                            delay(2000) // 2 segundos
                            showSplash = false
                        }
                    }
                } else {
                    // APP PRINCIPAL: Home e demais telas
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        AppNavigation(
                            appTheme = appTheme,
                            onToggleTheme = {
                                appTheme = if (appTheme == AppTheme.Dark) AppTheme.Light else AppTheme.Dark
                            }
                        )
                    }
                }
            }
        }
    }
}
