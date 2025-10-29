package org.example.calculadorapenal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.calculadorapenal.ui.screens.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        
        composable(Screen.CalculoPena.route) {
            CalculoPenaScreen(navController = navController)
        }
        
        composable(Screen.CalculoVEC.route) {
            CalculoVECScreen(navController = navController)
        }
        
        composable(Screen.Resultado.route) {
            ResultadoScreen(navController = navController)
        }
        
        composable(Screen.Sobre.route) {
            SobreScreen(navController = navController)
        }
    }
}
