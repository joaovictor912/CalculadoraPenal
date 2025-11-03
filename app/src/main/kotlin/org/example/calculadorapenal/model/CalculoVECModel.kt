package org.example.calculadorapenal.model

data class DadosVEC(
    val valorBens: Double = 0.0,
    val nomeCompleto: String = "",
    val whatsapp: String = "",
    val email: String = "",
    val numeroProcesso: String = ""
)

data class ResultadoVEC(
    val valorBens: Double,
    val valorVEC: Double,
    val contatoUsuario: ContatoUsuario? = null
)

object VECCalculator {
    
    fun calcular(dadosVEC: DadosVEC): ResultadoVEC {
        val valorVEC = dadosVEC.valorBens * 3
        
        return ResultadoVEC(
            valorBens = dadosVEC.valorBens,
            valorVEC = valorVEC,
            contatoUsuario = if (dadosVEC.nomeCompleto.isNotBlank() && dadosVEC.whatsapp.isNotBlank()) {
                ContatoUsuario(
                    nomeCompleto = dadosVEC.nomeCompleto,
                    whatsapp = dadosVEC.whatsapp,
                    email = dadosVEC.email,
                    numeroProcesso = dadosVEC.numeroProcesso
                )
            } else null
        )
    }
}

object ResultadoVECStore {
    var ultimoResultado: ResultadoVEC? = null
}
