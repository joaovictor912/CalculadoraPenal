package org.example.calculadorapenal.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object CalculoVEC : Screen("calculo_vec")
    object CalculoExecucaoPenal : Screen("calculo_execucao_penal")
    object ResultadoExecucao : Screen("resultado_execucao")
    object ResultadoVEC : Screen("resultado_vec")
    object Sobre : Screen("sobre")
}
