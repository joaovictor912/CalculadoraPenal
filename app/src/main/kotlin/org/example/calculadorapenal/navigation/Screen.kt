package org.example.calculadorapenal.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object CalculoRemicao : Screen("calculo_remicao")
    object CalculoExecucaoPenal : Screen("calculo_execucao_penal")
    object ResultadoExecucao : Screen("resultado_execucao")
    object ResultadoRemicao : Screen("resultado_remicao")
    object Sobre : Screen("sobre")
}
