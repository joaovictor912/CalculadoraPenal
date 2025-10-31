package org.example.calculadorapenal.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.example.calculadorapenal.model.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculoPenaScreen(navController: NavController) {
    var artigo by remember { mutableStateOf("") }
    var penaMinima by remember { mutableStateOf("") }
    var penaMaxima by remember { mutableStateOf("") }
    var culpabilidade by remember { mutableStateOf(0f) }
    var antecedentes by remember { mutableStateOf(0f) }
    var condutaSocial by remember { mutableStateOf(0f) }
    var personalidade by remember { mutableStateOf(0f) }
    var motivos by remember { mutableStateOf(0f) }
    var circunstancias by remember { mutableStateOf(0f) }
    var consequencias by remember { mutableStateOf(0f) }
    var comportamentoVitima by remember { mutableStateOf(0f) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dosimetria da Pena") },
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
            // Informações do Crime
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Dados do Crime",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = artigo,
                        onValueChange = { artigo = it.filter { ch -> ch.isDigit() } },
                        label = { Text("Número do Artigo (ex: 157)") },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Ex: 157") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            cursorColor = MaterialTheme.colorScheme.tertiary,
                            focusedLabelColor = MaterialTheme.colorScheme.tertiary
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = penaMinima,
                            onValueChange = { penaMinima = it },
                            label = { Text("Pena Mínima (meses)") },
                            modifier = Modifier.weight(1f),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                cursorColor = MaterialTheme.colorScheme.tertiary,
                                focusedLabelColor = MaterialTheme.colorScheme.tertiary
                            )
                        )

                        OutlinedTextField(
                            value = penaMaxima,
                            onValueChange = { penaMaxima = it },
                            label = { Text("Pena Máxima (meses)") },
                            modifier = Modifier.weight(1f),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                cursorColor = MaterialTheme.colorScheme.tertiary,
                                focusedLabelColor = MaterialTheme.colorScheme.tertiary
                            )
                        )
                    }
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "1ª Fase - Circunstâncias Judiciais (Art. 59)",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Text(
                        "Avalie de 0 (favorável) a 3 (desfavorável)",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    CircunstanciaSlider("Culpabilidade", culpabilidade) { culpabilidade = it }
                    CircunstanciaSlider("Antecedentes", antecedentes) { antecedentes = it }
                    CircunstanciaSlider("Conduta Social", condutaSocial) { condutaSocial = it }
                    CircunstanciaSlider("Personalidade", personalidade) { personalidade = it }
                    CircunstanciaSlider("Motivos do Crime", motivos) { motivos = it }
                    CircunstanciaSlider("Circunstâncias", circunstancias) { circunstancias = it }
                    CircunstanciaSlider("Consequências", consequencias) { consequencias = it }
                    CircunstanciaSlider("Comportamento da Vítima", comportamentoVitima) { comportamentoVitima = it }
                }
            }

            // 2ª Fase: Agravantes e Atenuantes
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "2ª Fase - Agravantes e Atenuantes",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        "Selecione as circunstâncias aplicáveis:",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    // Exemplo de checkboxes com cores ajustadas (quando forem adicionados)
                    /*
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = false,
                            onCheckedChange = {},
                            colors = CheckboxDefaults.colors(
                                checkedColor = MaterialTheme.colorScheme.tertiary,
                                uncheckedColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                checkmarkColor = MaterialTheme.colorScheme.onTertiary
                            )
                        )
                        Text("Reincidência")
                    }
                    */

                    Text(
                        "• Reincidência\n• Confissão espontânea\n• Menoridade relativa\n• Outras...",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            // 3ª Fase: Causas de Aumento e Diminuição
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "3ª Fase - Causas de Aumento/Diminuição",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        "Causas especiais previstas no tipo penal",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // Botao Calcular
            Button(
                onClick = {
                    // Parse inputs
                    val artNumber = artigo.trim()
                    val penaMin = penaMinima.toIntOrNull() ?: 0
                    val penaMax = penaMaxima.toIntOrNull() ?: 0

                    // 1) Pena base: média simples entre mínima e máxima (em meses)
                    val penaBase = if (penaMin > 0 && penaMax > 0) {
                        (penaMin + penaMax) / 2
                    } else {
                        0
                    }

                    // 2) Soma das circunstâncias (0..24)
                    val somaCircunstancias = (culpabilidade + antecedentes + condutaSocial + personalidade + motivos + circunstancias + consequencias + comportamentoVitima).toInt()

                    // 3) Ajuste: aplicamos 5% por ponto acima/abaixo do ponto médio (12)
                    val ajustePercentual = (somaCircunstancias - 12) * 0.05
                    val fatorAjuste = 1 + ajustePercentual

                    // 4) Pena provisória (aplica o ajuste à pena base)
                    val penaProvisoria = kotlin.math.round(penaBase * fatorAjuste).toInt().coerceAtLeast(0)

                    // 5) Por enquanto não há causas especiais informadas -> pena definitiva = provisória
                    val penaDefinitiva = penaProvisoria

                    // 6) Regime prisional básico (simplificado):
                    val regime = when {
                        penaDefinitiva > 96 -> RegimePrisional.FECHADO
                        penaDefinitiva > 48 -> RegimePrisional.SEMIABERTO
                        else -> RegimePrisional.ABERTO
                    }

                    val substituicaoPossivel = penaDefinitiva <= 48
                    val sursisPossivel = penaDefinitiva <= 24

                    val detalhamento = buildString {
                        append("Artigo: ${if (artNumber.isNotEmpty()) "Art. $artNumber" else "-"}\n")
                        append("Pena mínima: ${penaMin} meses\n")
                        append("Pena máxima: ${penaMax} meses\n")
                        append("Pena base (média): ${penaBase} meses\n")
                        append("Pontuação circunstâncias: $somaCircunstancias (0..24)\n")
                        append("Fator de ajuste aplicado: ${"%.2f".format(fatorAjuste)}x\n")
                        append("Pena provisória: ${penaProvisoria} meses\n")
                        append("Pena definitiva: ${penaDefinitiva} meses\n")
                        append("Regime estimado: $regime\n")
                        append("Substituição por penas restritivas possível: ${if (substituicaoPossivel) "Sim" else "Não"}\n")
                        append("Sursis possível: ${if (sursisPossivel) "Sim" else "Não"}\n")
                    }

                    // Store result in shared repository to be read by ResultadoScreen
                    ResultadoPenaStore.ultimo = ResultadoCalculo(
                        penaBase = penaBase,
                        penaProvisoria = penaProvisoria,
                        penaDefinitiva = penaDefinitiva,
                        regimePrisional = regime,
                        substituicaoPossivel = substituicaoPossivel,
                        sursisPossivel = sursisPossivel,
                        detalhamento = detalhamento
                    )

                    navController.navigate("resultado")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.onTertiary
                )
            ) {
                Icon(Icons.Filled.Calculate, "Calcular")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Calcular Pena", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun CircunstanciaSlider(
    label: String,
    value: Float,
    onValueChange: (Float) -> Unit
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(label, fontSize = 14.sp)
            Text(
                when (value.toInt()) {
                    0 -> "Favorável"
                    1 -> "Neutro"
                    2 -> "Desfavorável"
                    3 -> "Muito Desfavorável"
                    else -> "-"
                },
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )
        }

        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = 0f..3f,
            steps = 2,
            modifier = Modifier.fillMaxWidth(),
            colors = SliderDefaults.colors(
                activeTrackColor = MaterialTheme.colorScheme.secondary,
                inactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant,
                thumbColor = MaterialTheme.colorScheme.tertiary,
                activeTickColor = MaterialTheme.colorScheme.onSecondary,
                inactiveTickColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
    }
}