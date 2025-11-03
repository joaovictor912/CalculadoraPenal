package org.example.calculadorapenal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.calculadorapenal.ui.screens.*
import org.example.calculadorapenal.ui.theme.AppTheme

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
        
        composable(Screen.CalculoVEC.route) {
            CalculoVECScreen(navController = navController)
        }
        
        composable(Screen.CalculoExecucaoPenal.route) {
            CalculoExecucaoPenalScreen(navController = navController)
        }
        
        composable(Screen.ResultadoExecucao.route) {
            ResultadoExecucaoPenalScreen(navController = navController)
        }
        
        composable(Screen.ResultadoVEC.route) {
            ResultadoVECScreen(navController = navController)
        }

        composable(Screen.Sobre.route) {
            SobreScreen(navController = navController)
        }
    }
}

