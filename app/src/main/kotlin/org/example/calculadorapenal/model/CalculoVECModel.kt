package org.example.calculadorapenal.model

data class CalculoVEC(
    val valorSubtraido: Double = 0.0,
    val percentualRecuperado: Double = 0.0,
    val gravidade: GravidadeVEC = GravidadeVEC.MODERADA,
    val antecedentes: Boolean = false,
    val reincidencia: Boolean = false
)

enum class GravidadeVEC {
    MINIMA,
    MODERADA,
    GRAVE,
    GRAVISSIMA
}

data class ResultadoVEC(
    val valorEconomico: Double,
    val classificacao: String,
    val fundamentacao: String,
    val sugestaoMulta: Double
)
