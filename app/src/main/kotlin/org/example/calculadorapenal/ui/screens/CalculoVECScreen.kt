package org.example.calculadorapenal.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.example.calculadorapenal.model.*
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculoVECScreen(navController: NavController) {
    var valorSubtraido by remember { mutableStateOf("") }
    var percentualRecuperado by remember { mutableStateOf("") }
    var gravidadeSelecionada by remember { mutableStateOf(GravidadeVEC.MODERADA) }
    var temAntecedentes by remember { mutableStateOf(false) }
    var temReincidencia by remember { mutableStateOf(false) }
    var expandedGravidade by remember { mutableStateOf(false) }
    var resultadoVEC by remember { mutableStateOf<ResultadoVEC?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cálculo de VEC") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
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
            // Cabeçalho explicativo
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
                        "VEC - Valor Econômico do Crime\n\nCalcule o valor econômico do delito para fundamentação da pena de multa e reparação de danos.",
                        fontSize = 14.sp,
                        lineHeight = 20.sp
                    )
                }
            }

            // Valor Subtraído
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Valor do Prejuízo",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = valorSubtraido,
                        onValueChange = { valorSubtraido = it },
                        label = { Text("Valor Subtraído (R$)") },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Ex: 10000.00") },
                        leadingIcon = {
                            Icon(Icons.Filled.AttachMoney, contentDescription = "Valor")
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            cursorColor = MaterialTheme.colorScheme.tertiary,
                            focusedLabelColor = MaterialTheme.colorScheme.tertiary
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = percentualRecuperado,
                        onValueChange = { percentualRecuperado = it },
                        label = { Text("Percentual Recuperado (%)") },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Ex: 30") },
                        leadingIcon = {
                            Icon(Icons.Filled.Percent, contentDescription = "Percentual")
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            cursorColor = MaterialTheme.colorScheme.tertiary,
                            focusedLabelColor = MaterialTheme.colorScheme.tertiary
                        )
                    )
                }
            }

            // Gravidade do Crime
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Gravidade do Crime",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    ExposedDropdownMenuBox(
                        expanded = expandedGravidade,
                        onExpandedChange = { expandedGravidade = !expandedGravidade }
                    ) {
                        OutlinedTextField(
                            value = when (gravidadeSelecionada) {
                                GravidadeVEC.MINIMA -> "Mínima"
                                GravidadeVEC.MODERADA -> "Moderada"
                                GravidadeVEC.GRAVE -> "Grave"
                                GravidadeVEC.GRAVISSIMA -> "Gravíssima"
                            },
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Selecione a gravidade") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedGravidade)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                cursorColor = MaterialTheme.colorScheme.tertiary,
                                focusedLabelColor = MaterialTheme.colorScheme.tertiary
                            )
                        )

                        ExposedDropdownMenu(
                            expanded = expandedGravidade,
                            onDismissRequest = { expandedGravidade = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Mínima") },
                                onClick = {
                                    gravidadeSelecionada = GravidadeVEC.MINIMA
                                    expandedGravidade = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Moderada") },
                                onClick = {
                                    gravidadeSelecionada = GravidadeVEC.MODERADA
                                    expandedGravidade = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Grave") },
                                onClick = {
                                    gravidadeSelecionada = GravidadeVEC.GRAVE
                                    expandedGravidade = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Gravíssima") },
                                onClick = {
                                    gravidadeSelecionada = GravidadeVEC.GRAVISSIMA
                                    expandedGravidade = false
                                }
                            )
                        }
                    }
                }
            }

            // Circunstâncias Pessoais
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Circunstâncias Pessoais",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = temAntecedentes,
                            onCheckedChange = { temAntecedentes = it },
                            colors = CheckboxDefaults.colors(
                                checkedColor = MaterialTheme.colorScheme.tertiary,
                                uncheckedColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                checkmarkColor = MaterialTheme.colorScheme.onTertiary
                            )
                        )
                        Text("Possui antecedentes criminais")
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = temReincidencia,
                            onCheckedChange = { temReincidencia = it },
                            colors = CheckboxDefaults.colors(
                                checkedColor = MaterialTheme.colorScheme.tertiary,
                                uncheckedColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                checkmarkColor = MaterialTheme.colorScheme.onTertiary
                            )
                        )
                        Text("Reincidente")
                    }
                }
            }

            // Botão Calcular
            Button(
                onClick = {
                    val valor = valorSubtraido.toDoubleOrNull() ?: 0.0
                    val recuperado = percentualRecuperado.toDoubleOrNull() ?: 0.0

                    val valorEfetivo = valor * (1 - recuperado / 100)
                    val multiplicador = when (gravidadeSelecionada) {
                        GravidadeVEC.MINIMA -> 1.0
                        GravidadeVEC.MODERADA -> 1.5
                        GravidadeVEC.GRAVE -> 2.0
                        GravidadeVEC.GRAVISSIMA -> 2.5
                    }

                    val ajusteAntecedentes = if (temAntecedentes) 1.2 else 1.0
                    val ajusteReincidencia = if (temReincidencia) 1.3 else 1.0

                    val vec = valorEfetivo * multiplicador * ajusteAntecedentes * ajusteReincidencia

                    resultadoVEC = ResultadoVEC(
                        valorEconomico = vec,
                        classificacao = when (gravidadeSelecionada) {
                            GravidadeVEC.MINIMA -> "Crime de gravidade mínima"
                            GravidadeVEC.MODERADA -> "Crime de gravidade moderada"
                            GravidadeVEC.GRAVE -> "Crime grave"
                            GravidadeVEC.GRAVISSIMA -> "Crime gravíssimo"
                        },
                        fundamentacao = buildString {
                            append("Cálculo baseado em:\n")
                            append("• Valor subtraído: ${formatMoeda(valor)}\n")
                            append("• Recuperado: $recuperado%\n")
                            append("• Valor efetivo: ${formatMoeda(valorEfetivo)}\n")
                            append("• Multiplicador gravidade: ${multiplicador}x\n")
                            if (temAntecedentes) append("• Ajuste antecedentes: 1.2x\n")
                            if (temReincidencia) append("• Ajuste reincidência: 1.3x\n")
                        },
                        sugestaoMulta = vec * 0.1
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.onTertiary
                )
            ) {
                Icon(Icons.Filled.Calculate, contentDescription = "Calcular")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Calcular VEC", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            // Resultado
            resultadoVEC?.let { resultado ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Filled.CheckCircle,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.tertiary,
                                modifier = Modifier.size(32.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                "Resultado do Cálculo",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        }

                        HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                        Text(
                            "Valor Econômico do Crime (VEC)",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                        Text(
                            formatMoeda(resultado.valorEconomico),
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.tertiary
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            "Classificação",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                        Text(
                            resultado.classificacao,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            "Fundamentação",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                        Text(
                            resultado.fundamentacao,
                            fontSize = 14.sp,
                            lineHeight = 20.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            "Sugestão de Pena de Multa",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                        Text(
                            formatMoeda(resultado.sugestaoMulta),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                }
            }
        }
    }
}

private fun formatMoeda(valor: Double): String {
    val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    return format.format(valor)
}
