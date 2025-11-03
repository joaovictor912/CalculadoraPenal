package org.example.calculadorapenal.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.example.calculadorapenal.model.*
import org.example.calculadorapenal.navigation.Screen
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculoExecucaoPenalScreen(navController: NavController) {
    var penaAnos by remember { mutableStateOf("") }
    var penaMeses by remember { mutableStateOf("") }
    var penaDias by remember { mutableStateOf("") }
    var dataInicio by remember { mutableStateOf("") }
    var diasDetracao by remember { mutableStateOf("") }
    var tipoCrimeSelecionado by remember { mutableStateOf(TipoCrime.COMUM) }
    var statusSelecionado by remember { mutableStateOf(StatusApenado.PRIMARIO) }
    var expandedTipoCrime by remember { mutableStateOf(false) }
    var expandedStatus by remember { mutableStateOf(false) }
    var mostrarErro by remember { mutableStateOf(false) }
    var mensagemErro by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cálculo de Execução Penal") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Voltar")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Informações
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        Icons.Filled.Info,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        "Calcule as datas previstas para progressão de regime e livramento condicional conforme a Lei de Execução Penal.",
                        fontSize = 14.sp,
                        lineHeight = 20.sp
                    )
                }
            }

            // Pena Total
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Pena Total",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = penaAnos,
                            onValueChange = { if (it.all { c -> c.isDigit() }) penaAnos = it },
                            label = { Text("Anos") },
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            placeholder = { Text("0") },
                                colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                cursorColor = MaterialTheme.colorScheme.tertiary,
                                focusedLabelColor = MaterialTheme.colorScheme.tertiary
                            )
                        )

                        OutlinedTextField(
                            value = penaMeses,
                            onValueChange = { if (it.all { c -> c.isDigit() } && (it.toIntOrNull() ?: 0) < 12) penaMeses = it },
                            label = { Text("Meses") },
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            placeholder = { Text("0") },
                                colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                cursorColor = MaterialTheme.colorScheme.tertiary,
                                focusedLabelColor = MaterialTheme.colorScheme.tertiary
                            )
                        )

                        OutlinedTextField(
                            value = penaDias,
                            onValueChange = { if (it.all { c -> c.isDigit() } && (it.toIntOrNull() ?: 0) < 31) penaDias = it },
                            label = { Text("Dias") },
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            placeholder = { Text("0") },
                                colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                cursorColor = MaterialTheme.colorScheme.tertiary,
                                focusedLabelColor = MaterialTheme.colorScheme.tertiary
                            )
                        )
                    }
                }
            }

            // Data de Início e Detração
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Data de Início do Cumprimento",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = dataInicio,
                        onValueChange = { dataInicio = it },
                        label = { Text("Data (DD/MM/AAAA)") },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("01/01/2024") },
                        leadingIcon = {
                            Icon(Icons.Filled.CalendarToday, "Data")
                        },
                            colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            cursorColor = MaterialTheme.colorScheme.tertiary,
                            focusedLabelColor = MaterialTheme.colorScheme.tertiary
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = diasDetracao,
                        onValueChange = { if (it.all { c -> c.isDigit() }) diasDetracao = it },
                        label = { Text("Tempo de Detração (dias)") },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("0") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        leadingIcon = {
                            Icon(Icons.Filled.Schedule, "Detração")
                        },
                        supportingText = {
                            Text("Tempo já cumprido em prisão provisória", fontSize = 12.sp)
                        },
                            colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            cursorColor = MaterialTheme.colorScheme.tertiary,
                            focusedLabelColor = MaterialTheme.colorScheme.tertiary
                        )
                    )
                }
            }

            // Tipo de Crime
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Tipo de Crime",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    ExposedDropdownMenuBox(
                        expanded = expandedTipoCrime,
                        onExpandedChange = { expandedTipoCrime = !expandedTipoCrime }
                    ) {
                        OutlinedTextField(
                            value = when (tipoCrimeSelecionado) {
                                TipoCrime.COMUM -> "Comum"
                                TipoCrime.VIOLENCIA_GRAVE_AMEACA -> "Violência ou Grave Ameaça"
                                TipoCrime.HEDIONDO -> "Hediondo"
                                TipoCrime.HEDIONDO_MORTE -> "Hediondo com Resultado Morte"
                            },
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Selecione o tipo") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedTipoCrime)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                cursorColor = MaterialTheme.colorScheme.tertiary,
                                focusedLabelColor = MaterialTheme.colorScheme.tertiary
                            )
                        )

                        ExposedDropdownMenu(
                            expanded = expandedTipoCrime,
                            onDismissRequest = { expandedTipoCrime = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Comum") },
                                onClick = {
                                    tipoCrimeSelecionado = TipoCrime.COMUM
                                    expandedTipoCrime = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Violência ou Grave Ameaça") },
                                onClick = {
                                    tipoCrimeSelecionado = TipoCrime.VIOLENCIA_GRAVE_AMEACA
                                    expandedTipoCrime = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Hediondo") },
                                onClick = {
                                    tipoCrimeSelecionado = TipoCrime.HEDIONDO
                                    expandedTipoCrime = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Hediondo com Resultado Morte") },
                                onClick = {
                                    tipoCrimeSelecionado = TipoCrime.HEDIONDO_MORTE
                                    expandedTipoCrime = false
                                }
                            )
                        }
                    }
                }
            }

            // Status do Apenado
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Status do Apenado",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    ExposedDropdownMenuBox(
                        expanded = expandedStatus,
                        onExpandedChange = { expandedStatus = !expandedStatus }
                    ) {
                        OutlinedTextField(
                            value = when (statusSelecionado) {
                                StatusApenado.PRIMARIO -> "Primário"
                                StatusApenado.REINCIDENTE -> "Reincidente"
                            },
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Selecione o status") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedStatus)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                cursorColor = MaterialTheme.colorScheme.tertiary,
                                focusedLabelColor = MaterialTheme.colorScheme.tertiary
                            )
                        )

                        ExposedDropdownMenu(
                            expanded = expandedStatus,
                            onDismissRequest = { expandedStatus = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Primário") },
                                onClick = {
                                    statusSelecionado = StatusApenado.PRIMARIO
                                    expandedStatus = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Reincidente") },
                                onClick = {
                                    statusSelecionado = StatusApenado.REINCIDENTE
                                    expandedStatus = false
                                }
                            )
                        }
                    }
                }
            }

            // Mensagem de erro
            if (mostrarErro) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            Icons.Filled.Error,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            mensagemErro,
                            color = MaterialTheme.colorScheme.onErrorContainer
                        )
                    }
                }
            }

            // Botão Calcular
            Button(
                onClick = {
                    try {
                        val anos = penaAnos.toIntOrNull() ?: 0
                        val meses = penaMeses.toIntOrNull() ?: 0
                        val dias = penaDias.toIntOrNull() ?: 0
                        val detracao = diasDetracao.toIntOrNull() ?: 0

                        if (anos == 0 && meses == 0 && dias == 0) {
                            mostrarErro = true
                            mensagemErro = "Informe a pena total"
                            return@Button
                        }

                        if (dataInicio.isBlank()) {
                            mostrarErro = true
                            mensagemErro = "Informe a data de início do cumprimento"
                            return@Button
                        }

                        // Parse da data
                        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                        val dataInicioLocal = try {
                            LocalDate.parse(dataInicio, formatter)
                        } catch (e: Exception) {
                            mostrarErro = true
                            mensagemErro = "Data inválida. Use o formato DD/MM/AAAA"
                            return@Button
                        }

                        mostrarErro = false

                        val dados = DadosExecucaoPenal(
                            penaAnos = anos,
                            penaMeses = meses,
                            penaDias = dias,
                            dataInicioCumprimento = dataInicioLocal,
                            diasDetracao = detracao,
                            tipoCrime = tipoCrimeSelecionado,
                            statusApenado = statusSelecionado
                        )

                        val resultado = ExecucaoPenalCalculator.calcular(dados)
                        ResultadoExecucaoPenalStore.salvar(resultado)
                        
                        navController.navigate(Screen.ResultadoExecucao.route)
                    } catch (e: Exception) {
                        mostrarErro = true
                        mensagemErro = "Erro ao calcular: ${e.message}"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary
                )
            ) {
                Icon(Icons.Filled.Calculate, "Calcular")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Calcular", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}


object ResultadoExecucaoPenalStore {
    var ultimo: ResultadoExecucaoPenal? = null
        private set

    fun salvar(resultado: ResultadoExecucaoPenal) {
        ultimo = resultado
    }
}
