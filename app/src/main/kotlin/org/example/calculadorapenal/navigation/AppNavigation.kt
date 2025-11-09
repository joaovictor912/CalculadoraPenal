package org.example.calculadorapenal.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import org.example.calculadorapenal.R
import org.example.calculadorapenal.ui.screens.*
import org.example.calculadorapenal.ui.theme.AppTheme
import org.example.calculadorapenal.ui.theme.BackgroundDark

@Composable
fun AppNavigation(
    appTheme: AppTheme,
    onToggleTheme: () -> Unit
) {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                navController = navController,
                appTheme = appTheme,
                onToggleTheme = onToggleTheme
            )
        }
        
        composable(Screen.CalculoRemicao.route) {
            CalculoRemicaoScreen(navController = navController)
        }
        
        composable(Screen.CalculoExecucaoPenal.route) {
            CalculoExecucaoPenalScreen(navController = navController)
        }
        
        composable(Screen.ResultadoExecucao.route) {
            ResultadoExecucaoPenalScreen(navController = navController)
        }
        
        composable(Screen.ResultadoRemicao.route) {
            ResultadoRemicaoScreen(navController = navController)
        }

        composable(Screen.Sobre.route) {
            SobreScreen(navController = navController)
        }
    }
}

