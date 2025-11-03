package org.example.calculadorapenal.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.example.calculadorapenal.model.*
import org.example.calculadorapenal.navigation.Screen
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculoVECScreen(navController: NavController) {
    var valorBens by remember { mutableStateOf("") }
    var nomeCompleto by remember { mutableStateOf("") }
    var whatsapp by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var numeroProcesso by remember { mutableStateOf("") }
    var mostrarErro by remember { mutableStateOf(false) }
    var mensagemErro by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cálculo de VEC") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
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
            // Cabeçalho
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AttachMoney,
                        contentDescription = "VEC",
                        modifier = Modifier.size(48.dp),
                        tint = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "Valor Econômico do Crime",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "Cálculo conforme legislação vigente",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Valor dos Bens
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Valor dos Bens",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    OutlinedTextField(
                        value = valorBens,
                        onValueChange = { valorBens = it },
                        label = { Text("Valor (R$) *") },
                        placeholder = { Text("Ex: 10000.00") },
                        leadingIcon = {
                            Icon(Icons.Default.AttachMoney, "Valor")
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }
            }

            // Dados de Contato
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Dados de Contato",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    
                    Text(
                        text = "Preencha os dados abaixo para receber orientação jurídica",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    OutlinedTextField(
                        value = nomeCompleto,
                        onValueChange = { nomeCompleto = it },
                        label = { Text("Nome Completo *") },
                        leadingIcon = {
                            Icon(Icons.Default.Person, "Nome")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = whatsapp,
                        onValueChange = { whatsapp = it },
                        label = { Text("WhatsApp *") },
                        placeholder = { Text("(00) 00000-0000") },
                        leadingIcon = {
                            Icon(Icons.Default.Phone, "WhatsApp")
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("E-mail (opcional)") },
                        leadingIcon = {
                            Icon(Icons.Default.Email, "E-mail")
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = numeroProcesso,
                        onValueChange = { numeroProcesso = it },
                        label = { Text("Número do Processo (opcional)") },
                        leadingIcon = {
                            Icon(Icons.Default.Article, "Processo")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Text(
                        text = "* Campos obrigatórios",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            // Mensagem de Erro
            if (mostrarErro) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Error,
                            contentDescription = "Erro",
                            tint = MaterialTheme.colorScheme.error
                        )
                        Text(
                            text = mensagemErro,
                            color = MaterialTheme.colorScheme.onErrorContainer,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            // Botão Calcular
            Button(
                onClick = {
                    mostrarErro = false
                    
                    // Validações
                    when {
                        valorBens.isBlank() -> {
                            mostrarErro = true
                            mensagemErro = "Por favor, informe o valor dos bens"
                        }
                        nomeCompleto.isBlank() -> {
                            mostrarErro = true
                            mensagemErro = "Por favor, informe seu nome completo"
                        }
                        whatsapp.isBlank() -> {
                            mostrarErro = true
                            mensagemErro = "Por favor, informe seu WhatsApp"
                        }
                        else -> {
                            try {
                                val valor = valorBens.replace(",", ".").toDouble()
                                if (valor <= 0) {
                                    mostrarErro = true
                                    mensagemErro = "O valor deve ser maior que zero"
                                } else {
                                    val dados = DadosVEC(
                                        valorBens = valor,
                                        nomeCompleto = nomeCompleto.trim(),
                                        whatsapp = whatsapp.trim(),
                                        email = email.trim(),
                                        numeroProcesso = numeroProcesso.trim()
                                    )
                                    
                                    val resultado = VECCalculator.calcular(dados)
                                    ResultadoVECStore.ultimoResultado = resultado
                                    navController.navigate(Screen.ResultadoExecucao.route)
                                }
                            } catch (e: Exception) {
                                mostrarErro = true
                                mensagemErro = "Valor inválido. Use apenas números e ponto/vírgula para decimais"
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Calculate,
                    contentDescription = "Calcular",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Calcular VEC",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
