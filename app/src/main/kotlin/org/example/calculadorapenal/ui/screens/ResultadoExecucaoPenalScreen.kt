package org.example.calculadorapenal.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.example.calculadorapenal.model.ResultadoExecucaoPenal
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

    // Datas principais
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Início (informado): ${res.dataInicioCumprimento.format(formatter)}")
            Text("Início efetivo: ${res.dataInicioEfetivo.format(formatter)}")
            if (res.diasDetracao > 0) Text("Detração: ${res.diasDetracao} dia(s)")
        }
    }

    // Progressões
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Progressão ao Semiaberto: ${res.dataProgressaoSemiaberto?.format(formatter) ?: "—"}")
            Text("Progressão ao Aberto: ${res.dataProgressaoAberto?.format(formatter) ?: "—"}")
        }
    }

    // Livramento
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            if (res.livramentoVedado) {
                Text("Livramento Condicional: VEDADO")
            } else {
                Text("Livramento Condicional: ${res.dataLivramentoCondicional?.format(formatter) ?: "—"} (${res.fracaoLivramento})")
            }
        }
    }

    // Detalhamento
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Detalhamento:")
            Spacer(Modifier.height(8.dp))
            Text(res.detalhamento)
        }
    }
}
