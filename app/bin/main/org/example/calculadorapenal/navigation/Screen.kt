package org.example.calculadorapenal.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object CalculoPena : Screen("calculo_pena")
    object CalculoVEC : Screen("calculo_vec")
    object Resultado : Screen("resultado")
    object Sobre : Screen("sobre")
}
