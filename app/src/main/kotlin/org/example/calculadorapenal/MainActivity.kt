package org.example.calculadorapenal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.example.calculadorapenal.ui.theme.AppTheme
import org.example.calculadorapenal.ui.theme.CalculadoraPenalTheme
import org.example.calculadorapenal.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Switch from splash theme to the app theme as soon as the Activity starts
        setTheme(R.style.Theme_CalculadoraPenal)
        super.onCreate(savedInstanceState)
        setContent {
            var appTheme by remember { mutableStateOf(AppTheme.Dark) }
            CalculadoraPenalTheme(appTheme = appTheme) {
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
