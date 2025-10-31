package org.example.calculadorapenal.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SobreScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sobre o Aplicativo") },
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
            // Sobre o App
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Default.Gavel,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        tint = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        "Calculadora Penal",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Text(
                        "Versão 1.0",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            // Descrição
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Sobre",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        "Sistema auxiliar para cálculo de dosimetria da pena e valor econômico do crime (VEC), desenvolvido por Alunos do Insituto Mauá de Tenologia para auxiliar profissionais do Direito na aplicação do método trifásico previsto no artigo 68 do Código Penal Brasileiro.",
                        fontSize = 14.sp,
                        lineHeight = 20.sp
                    )
                }
            }

            // Funcionalidades
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Funcionalidades",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    FuncionalidadeItem(
                        icone = Icons.Default.Calculate,
                        titulo = "Dosimetria da Pena",
                        descricao = "Cálculo completo das três fases da dosimetria conforme art. 68 do CP"
                    )

                    FuncionalidadeItem(
                        icone = Icons.Default.AttachMoney,
                        titulo = "Cálculo de VEC",
                        descricao = "Determinação do Valor Econômico do Crime para fundamentação de multa"
                    )

                    FuncionalidadeItem(
                        icone = Icons.Default.Assessment,
                        titulo = "Análise de Benefícios",
                        descricao = "Verificação automática de possibilidade de sursis e substituição"
                    )
                }
            }

            // Base Legal
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Base Legal",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        "• Código Penal Brasileiro (Decreto-Lei nº 2.848/1940)\n" +
                                "• Art. 59 - Circunstâncias judiciais\n" +
                                "• Art. 68 - Método trifásico\n" +
                                "• Arts. 61 a 67 - Agravantes e atenuantes\n" +
                                "• Art. 33 - Regimes prisionais\n" +
                                "• Art. 44 - Penas restritivas de direitos\n" +
                                "• Art. 77 - Suspensão condicional da pena",
                        fontSize = 14.sp,
                        lineHeight = 20.sp
                    )
                }
            }

            // Aviso Legal
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
                        Icons.Default.Warning,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(24.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            "Aviso Legal",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.error
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            "Este aplicativo é uma ferramenta auxiliar e não substitui a análise jurídica especializada. Os cálculos devem ser revisados por profissional habilitado. O desenvolvedor não se responsabiliza por decisões tomadas com base exclusiva nos resultados apresentados.",
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            color = MaterialTheme.colorScheme.onErrorContainer
                        )
                    }
                }
            }

            // Rodapé
            Text(
                "© 2025 - Calculadora Penal\nTodos os direitos reservados",
                fontSize = 12.sp,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }
    }
}

@Composable
fun FuncionalidadeItem(
    icone: androidx.compose.ui.graphics.vector.ImageVector,
    titulo: String,
    descricao: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            icone,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                titulo,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                descricao,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
