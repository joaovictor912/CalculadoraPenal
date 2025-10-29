package org.example.calculadorapenal.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
                        Icon(Icons.Default.ArrowBack, "Voltar")
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
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Dados do Crime",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    OutlinedTextField(
                        value = artigo,
                        onValueChange = { artigo = it },
                        label = { Text("Artigo do CP") },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Ex: Art. 157, §2º, I") }
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
                            modifier = Modifier.weight(1f)
                        )
                        
                        OutlinedTextField(
                            value = penaMaxima,
                            onValueChange = { penaMaxima = it },
                            label = { Text("Pena Máxima (meses)") },
                            modifier = Modifier.weight(1f)
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
                        color = MaterialTheme.colorScheme.primary
                    )
                    
                    Text(
                        "Avalie de 0 (favorável) a 3 (desfavorável)",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
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
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "2ª Fase - Agravantes e Atenuantes",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    Text(
                        "Selecione as circunstâncias aplicáveis:",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    
                    // Aqui você pode adicionar checkboxes para agravantes/atenuantes
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
                        color = MaterialTheme.colorScheme.primary
                    )
                    
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    Text(
                        "Causas especiais previstas no tipo penal",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            
            // Botão Calcular
            Button(
                onClick = { 
                    navController.navigate("resultado")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(Icons.Default.Calculate, "Calcular")
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
                color = MaterialTheme.colorScheme.primary
            )
        }
        
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = 0f..3f,
            steps = 2,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
