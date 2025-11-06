package org.example.calculadorapenal.model

import java.time.LocalDate
import java.time.format.DateTimeFormatter

enum class TipoCrime {
    COMUM,
    VIOLENCIA_GRAVE_AMEACA,
    HEDIONDO,
    HEDIONDO_MORTE
}


enum class StatusApenado {
    PRIMARIO,
    REINCIDENTE
}

data class DadosExecucaoPenal(
    val penaAnos: Int = 0,
    val penaMeses: Int = 0,
    val penaDias: Int = 0,
    val dataInicioCumprimento: LocalDate,
    val diasDetracao: Int = 0,
    val tipoCrime: TipoCrime = TipoCrime.COMUM,
    val statusApenado: StatusApenado = StatusApenado.PRIMARIO
)

data class ResultadoExecucaoPenal(
    val penaTotalDias: Int,
    val dataInicioCumprimento: LocalDate,
    val dataInicioEfetivo: LocalDate,
    val diasDetracao: Int,
    val tipoCrime: TipoCrime,
    val statusApenado: StatusApenado,

    val dataProgressaoSemiaberto: LocalDate?,
    val percentualProgressaoSemiaberto: Double,
    val diasParaProgressaoSemiaberto: Int,

    val dataProgressaoAberto: LocalDate?,
    val percentualProgressaoAberto: Double,
    val diasParaProgressaoAberto: Int,

    val dataLivramentoCondicional: LocalDate?,
    val fracaoLivramento: String,
    val diasParaLivramento: Int,
    val livramentoVedado: Boolean,

    val detalhamento: String
)

data class ContatoUsuario(
    val nomeCompleto: String = "",
    val whatsapp: String = "",
    val email: String = "",
    val numeroProcesso: String = ""
)

object ExecucaoPenalCalculator {

    fun calcular(dados: DadosExecucaoPenal): ResultadoExecucaoPenal {
        val penaTotalDias = (dados.penaAnos * 365) + (dados.penaMeses * 30) + dados.penaDias
        val dataInicioEfetivo = dados.dataInicioCumprimento.minusDays(dados.diasDetracao.toLong())

        val (percentualSemiaberto, percentualAberto) = obterPercentuaisProgressao(
            dados.tipoCrime, 
            dados.statusApenado
        )

        val diasParaSemiaberto = (penaTotalDias * percentualSemiaberto).toInt()
        val diasParaAberto = (penaTotalDias * percentualAberto).toInt()

        val dataProgressaoSemiaberto = dataInicioEfetivo.plusDays(diasParaSemiaberto.toLong())
        val dataProgressaoAberto = dataInicioEfetivo.plusDays(diasParaAberto.toLong())

        val (fracaoLivramento, livramentoVedado) = obterFracaoLivramento(
            dados.tipoCrime, 
            dados.statusApenado
        )

        val diasParaLivramento = if (!livramentoVedado) {
            when (fracaoLivramento) {
                "1/3" -> penaTotalDias / 3
                "1/2" -> penaTotalDias / 2
                "2/3" -> (penaTotalDias * 2) / 3
                else -> 0
            }
        } else {
            0
        }

        val dataLivramentoCondicional = if (!livramentoVedado) {
            dataInicioEfetivo.plusDays(diasParaLivramento.toLong())
        } else {
            null
        }

        val detalhamento = gerarDetalhamento(
            penaTotalDias, 
            dados, 
            percentualSemiaberto, 
            percentualAberto,
            fracaoLivramento,
            livramentoVedado
        )

        return ResultadoExecucaoPenal(
            penaTotalDias = penaTotalDias,
            dataInicioCumprimento = dados.dataInicioCumprimento,
            dataInicioEfetivo = dataInicioEfetivo,
            diasDetracao = dados.diasDetracao,
            tipoCrime = dados.tipoCrime,
            statusApenado = dados.statusApenado,
            dataProgressaoSemiaberto = dataProgressaoSemiaberto,
            percentualProgressaoSemiaberto = percentualSemiaberto,
            diasParaProgressaoSemiaberto = diasParaSemiaberto,
            dataProgressaoAberto = dataProgressaoAberto,
            percentualProgressaoAberto = percentualAberto,
            diasParaProgressaoAberto = diasParaAberto,
            dataLivramentoCondicional = dataLivramentoCondicional,
            fracaoLivramento = fracaoLivramento,
            diasParaLivramento = diasParaLivramento,
            livramentoVedado = livramentoVedado,
            detalhamento = detalhamento
        )
    }

    private fun obterPercentuaisProgressao(
        tipoCrime: TipoCrime, 
        status: StatusApenado
    ): Pair<Double, Double> {
        return when (tipoCrime) {
            TipoCrime.COMUM -> {
                when (status) {
                    StatusApenado.PRIMARIO -> Pair(0.16, 0.16)
                    StatusApenado.REINCIDENTE -> Pair(0.20, 0.20)
                }
            }
            TipoCrime.VIOLENCIA_GRAVE_AMEACA -> {
                when (status) {
                    StatusApenado.PRIMARIO -> Pair(0.25, 0.25)
                    StatusApenado.REINCIDENTE -> Pair(0.30, 0.30)
                }
            }
            TipoCrime.HEDIONDO -> {
                when (status) {
                    StatusApenado.PRIMARIO -> Pair(0.40, 0.40)
                    StatusApenado.REINCIDENTE -> Pair(0.60, 0.60)
                }
            }
            TipoCrime.HEDIONDO_MORTE -> {
                when (status) {
                    StatusApenado.PRIMARIO -> Pair(0.50, 0.50)
                    StatusApenado.REINCIDENTE -> Pair(0.70, 0.70)
                }
            }
        }
    }

    private fun obterFracaoLivramento(
        tipoCrime: TipoCrime, 
        status: StatusApenado
    ): Pair<String, Boolean> {
        return when (tipoCrime) {
            TipoCrime.COMUM, TipoCrime.VIOLENCIA_GRAVE_AMEACA -> {
                when (status) {
                    StatusApenado.PRIMARIO -> Pair("1/3", false)
                    StatusApenado.REINCIDENTE -> Pair("1/2", false)
                }
            }
            TipoCrime.HEDIONDO -> {
                Pair("2/3", false)
            }
            TipoCrime.HEDIONDO_MORTE -> {
                Pair("Vedado", true)
            }
        }
    }

    private fun gerarDetalhamento(
        penaTotalDias: Int,
        dados: DadosExecucaoPenal,
        percentualSemiaberto: Double,
        percentualAberto: Double,
        fracaoLivramento: String,
        livramentoVedado: Boolean
    ): String {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val anos = penaTotalDias / 365
        val mesesRestantes = (penaTotalDias % 365) / 30
        val diasRestantes = (penaTotalDias % 365) % 30
        
        return buildString {
            append(" CÁLCULO DE EXECUÇÃO PENAL\n\n")
            
            append("DADOS DA PENA:\n")
            append("Pena Total: $anos ano(s), $mesesRestantes mês(es) e $diasRestantes dia(s)\n")
            append("Total em dias: $penaTotalDias dias\n")
            append("Tipo de Crime: ${obterNomeTipoCrime(dados.tipoCrime)}\n")
            append("Status: ${if (dados.statusApenado == StatusApenado.PRIMARIO) "Primário" else "Reincidente"}\n\n")
            
            append("DATAS DE CUMPRIMENTO:\n")
            append("Data de Início: ${dados.dataInicioCumprimento.format(formatter)}\n")
            if (dados.diasDetracao > 0) {
                append("Detração Aplicada: ${dados.diasDetracao} dias\n")
                append("Data de Início Efetivo: ${dados.dataInicioCumprimento.minusDays(dados.diasDetracao.toLong()).format(formatter)}\n")
            }
            append("\n")
            
            append("PROGRESSÃO DE REGIME:\n")
            append("Para Regime Semiaberto:\n")
            append("  • Percentual: ${(percentualSemiaberto * 100).toInt()}%\n")
            append("  • Base Legal: ${obterBaseLegalProgressao(dados.tipoCrime, dados.statusApenado)}\n\n")
            
            append("Para Regime Aberto:\n")
            append("  • Percentual: ${(percentualAberto * 100).toInt()}% (cumulativo)\n")
            append("  • Aplicado após progressão ao semiaberto\n\n")
            
            append(" LIVRAMENTO CONDICIONAL:\n")
            if (livramentoVedado) {
                append("  • VEDADO por lei (Crime hediondo com resultado morte)\n")
                append("  • Base Legal: Art. 83, V do CP\n")
            } else {
                append("  • Fração Necessária: $fracaoLivramento da pena\n")
                append("  • Base Legal: ${obterBaseLegalLivramento(dados.tipoCrime, dados.statusApenado)}\n")
            }
            
            append("\n")
            append(" OBSERVAÇÕES IMPORTANTES:\n")
            append("• As datas são estimativas que dependem de bom comportamento carcerário\n")
            append("• Pode ser necessário exame criminológico\n")
            append("• Remição por trabalho/estudo pode antecipar as datas\n")
            append("• Consulte um advogado para análise completa do caso\n")
        }
    }
    
    private fun obterNomeTipoCrime(tipo: TipoCrime): String = when (tipo) {
        TipoCrime.COMUM -> "Crime Comum"
        TipoCrime.VIOLENCIA_GRAVE_AMEACA -> "Crime com Violência ou Grave Ameaça"
        TipoCrime.HEDIONDO -> "Crime Hediondo"
        TipoCrime.HEDIONDO_MORTE -> "Crime Hediondo com Resultado Morte"
    }
    
    private fun obterBaseLegalProgressao(tipo: TipoCrime, status: StatusApenado): String {
        val percentual = when (tipo) {
            TipoCrime.COMUM -> if (status == StatusApenado.PRIMARIO) "16%" else "20%"
            TipoCrime.VIOLENCIA_GRAVE_AMEACA -> if (status == StatusApenado.PRIMARIO) "25%" else "30%"
            TipoCrime.HEDIONDO -> if (status == StatusApenado.PRIMARIO) "40%" else "60%"
            TipoCrime.HEDIONDO_MORTE -> if (status == StatusApenado.PRIMARIO) "50%" else "70%"
        }
        return "Art. 112 da LEP c/c Lei 13.964/2019 ($percentual)"
    }
    
    private fun obterBaseLegalLivramento(tipo: TipoCrime, status: StatusApenado): String {
        return when (tipo) {
            TipoCrime.COMUM, TipoCrime.VIOLENCIA_GRAVE_AMEACA -> {
                if (status == StatusApenado.PRIMARIO) 
                    "Art. 83, I do CP (1/3 - Réu Primário)"
                else 
                    "Art. 83, II do CP (1/2 - Reincidente)"
            }
            TipoCrime.HEDIONDO -> "Art. 83, V do CP (2/3 - Crime Hediondo)"
            TipoCrime.HEDIONDO_MORTE -> "Art. 83, V do CP (Vedado)"
        }
    }

    fun formatarPena(anos: Int, meses: Int, dias: Int): String {
        val partes = mutableListOf<String>()
        if (anos > 0) partes.add("$anos ano${if (anos > 1) "s" else ""}")
        if (meses > 0) partes.add("$meses mês${if (meses > 1) "es" else ""}")
        if (dias > 0) partes.add("$dias dia${if (dias > 1) "s" else ""}")
        return partes.joinToString(", ")
    }
}
