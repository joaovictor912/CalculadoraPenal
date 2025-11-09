package org.example.calculadorapenal.model


data class DadosRemicao(
    val diasTrabalhados: Int = 0,
    val horasEstudo: Int = 0,
    val nomeCompleto: String = "",
    val whatsapp: String = "",
    val email: String = "",
    val numeroProcesso: String = ""
)

data class ResultadoRemicao(
    val diasTrabalhados: Int,
    val horasEstudo: Int,
    val diasRemidosPorTrabalho: Int,
    val diasRemidosPorEstudo: Int,
    val totalDiasRemidos: Int,
    val contatoUsuario: ContatoUsuario? = null
)

/**
 * Calculadora de Remição de Pena
 * 
 * Regras de Negócio:
 * - Trabalho: A cada 3 dias trabalhados = 1 dia de pena remido
 * - Estudo: A cada 12 horas de estudo = 1 dia de pena remido
 */
object RemicaoCalculator {
    
    /**
     * Calcula a remição por trabalho
     * @param diasTrabalhados Total de dias trabalhados
     * @return Dias de pena remidos
     */
    fun calcularRemicaoPorTrabalho(diasTrabalhados: Int): Int {
        return diasTrabalhados / 3
    }
    
    /**
     * Calcula a remição por estudo
     * @param horasEstudo Total de horas de estudo
     * @return Dias de pena remidos
     */
    fun calcularRemicaoPorEstudo(horasEstudo: Int): Int {
        return horasEstudo / 12
    }
    
    /**
     * Calcula o total de remição (trabalho + estudo)
     * @param dadosRemicao Dados de entrada
     * @return Resultado completo do cálculo
     */
    fun calcular(dadosRemicao: DadosRemicao): ResultadoRemicao {
        val diasRemidosPorTrabalho = calcularRemicaoPorTrabalho(dadosRemicao.diasTrabalhados)
        val diasRemidosPorEstudo = calcularRemicaoPorEstudo(dadosRemicao.horasEstudo)
        val totalDiasRemidos = diasRemidosPorTrabalho + diasRemidosPorEstudo
        
        return ResultadoRemicao(
            diasTrabalhados = dadosRemicao.diasTrabalhados,
            horasEstudo = dadosRemicao.horasEstudo,
            diasRemidosPorTrabalho = diasRemidosPorTrabalho,
            diasRemidosPorEstudo = diasRemidosPorEstudo,
            totalDiasRemidos = totalDiasRemidos,
            contatoUsuario = if (dadosRemicao.nomeCompleto.isNotBlank() && dadosRemicao.whatsapp.isNotBlank()) {
                ContatoUsuario(
                    nomeCompleto = dadosRemicao.nomeCompleto,
                    whatsapp = dadosRemicao.whatsapp,
                    email = dadosRemicao.email,
                    numeroProcesso = dadosRemicao.numeroProcesso
                )
            } else null
        )
    }
}

/**
 * Store para manter o último resultado calculado
 */
object ResultadoRemicaoStore {
    var ultimoResultado: ResultadoRemicao? = null
}
