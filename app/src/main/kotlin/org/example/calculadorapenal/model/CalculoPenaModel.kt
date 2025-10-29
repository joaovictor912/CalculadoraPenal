package org.example.calculadorapenal.model

data class Crime(
    val nome: String = "",
    val artigo: String = "",
    val penaMinima: Int = 0,
    val penaMaxima: Int = 0, 
    val multa: Double = 0.0
)

data class CalculoPena(
    val crime: Crime,
    val circunstanciasJudiciais: CircunstanciasJudiciais,
    val atenuantes: List<String> = emptyList(),
    val agravantes: List<String> = emptyList(),
    val causasAumento: List<CausaEspecial> = emptyList(),
    val causasDiminuicao: List<CausaEspecial> = emptyList(),
    val regimeInicial: RegimePrisional = RegimePrisional.SEMIABERTO
)

data class CircunstanciasJudiciais(
    val culpabilidade: Int = 0,
    val antecedentes: Int = 0,
    val condutaSocial: Int = 0,
    val personalidade: Int = 0,
    val motivos: Int = 0,
    val circunstancias: Int = 0,
    val consequencias: Int = 0,
    val comportamentoVitima: Int = 0
)

data class CausaEspecial(
    val descricao: String,
    val fracao: String,
    val tipo: TipoCausa
)

enum class TipoCausa {
    AUMENTO,
    DIMINUICAO
}

enum class RegimePrisional {
    FECHADO,
    SEMIABERTO,
    ABERTO
}

data class ResultadoCalculo(
    val penaBase: Int, // em meses
    val penaProvisoria: Int,
    val penaDefinitiva: Int,
    val regimePrisional: RegimePrisional,
    val substituicaoPossivel: Boolean,
    val sursisPossivel: Boolean,
    val detalhamento: String
)
