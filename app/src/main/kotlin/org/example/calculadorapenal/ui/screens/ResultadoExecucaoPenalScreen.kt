package org.example.calculadorapenal.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.example.calculadorapenal.model.ResultadoExecucaoPenal
import org.example.calculadorapenal.R
import androidx.compose.ui.res.stringResource
import android.content.Intent
import android.net.Uri
import java.net.URLEncoder
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultadoExecucaoPenalScreen(navController: NavController) {
    val resultado = ResultadoExecucaoPenalStore.ultimo

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Resultado da Execução Penal") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (resultado == null) {
                Text("Nenhum resultado disponível.")
                Button(onClick = { navController.navigateUp() }) {
                    Text("Voltar")
                }
            } else {
                ResultadoExecucaoContent(resultado)

                val context = LocalContext.current
                Spacer(Modifier.height(12.dp))
                Button(
                    onClick = {
                        val officeRaw = context.getString(R.string.office_whatsapp)
                        val numero = officeRaw.filter { it.isDigit() }.let { d ->
                            var x = d
                            if (x.startsWith("0")) x = x.drop(1)
                            if (!x.startsWith("55")) "55$x" else x
                        }

                        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                        val msg = buildString {
                            append("Olá! Gostaria de falar com um advogado. ")
                            append("Resultado Execução Penal: ")
                            append("Início: ")
                            append(resultado.dataInicioCumprimento.format(formatter))
                            append(", Início efetivo: ")
                            append(resultado.dataInicioEfetivo.format(formatter))
                            append(", Semiaberto: ")
                            append(resultado.dataProgressaoSemiaberto?.format(formatter) ?: "—")
                            append(", Aberto: ")
                            append(resultado.dataProgressaoAberto?.format(formatter) ?: "—")
                            if (!resultado.livramentoVedado) {
                                append(", Livramento: ")
                                append(resultado.dataLivramentoCondicional?.format(formatter) ?: "—")
                                append(" (")
                                append(resultado.fracaoLivramento)
                                append(")")
                            } else {
                                append(", Livramento: VEDADO")
                            }
                        }

                        val url = "https://wa.me/$numero?text=" + URLEncoder.encode(msg, "UTF-8")
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary
                    )
                ) {
                    Icon(Icons.Default.Send, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text(stringResource(id = R.string.cta_falar_advogado))
                }
            }
        }
    }
}

@Composable
private fun ResultadoExecucaoContent(res: ResultadoExecucaoPenal) {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    Text(
        text = "Resumo do Cálculo",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold
    )

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Início (informado): ${res.dataInicioCumprimento.format(formatter)}")
            Text("Início efetivo: ${res.dataInicioEfetivo.format(formatter)}")
            if (res.diasDetracao > 0) Text("Detração: ${res.diasDetracao} dia(s)")
        }
    }

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Progressão ao Semiaberto: ${res.dataProgressaoSemiaberto?.format(formatter) ?: "—"}")
            Text("Progressão ao Aberto: ${res.dataProgressaoAberto?.format(formatter) ?: "—"}")
        }
    }

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            if (res.livramentoVedado) {
                Text("Livramento Condicional: VEDADO")
            } else {
                Text("Livramento Condicional: ${res.dataLivramentoCondicional?.format(formatter) ?: "—"} (${res.fracaoLivramento})")
            }
        }
    }

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Detalhamento:")
            Spacer(Modifier.height(8.dp))
            Text(res.detalhamento)
        }
    }
}
