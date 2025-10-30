# Calculadora Penal - Aplicativo Android

## 📱 Descrição

Aplicativo Android desenvolvido em Kotlin para auxiliar profissionais do Direito no cálculo de dosimetria da pena e do Valor Econômico do Crime (VEC).

## 🎯 Funcionalidades

### 1. Dosimetria da Pena
- **1ª Fase**: Cálculo da pena base através das 8 circunstâncias judiciais (Art. 59 do CP)
  - Culpabilidade
  - Antecedentes
  - Conduta social
  - Personalidade do agente
  - Motivos do crime
  - Circunstâncias do crime
  - Consequências do crime
  - Comportamento da vítima

- **2ª Fase**: Aplicação de agravantes e atenuantes
- **3ª Fase**: Causas especiais de aumento e diminuição
- Definição do regime prisional inicial
- Análise de benefícios (sursis e substituição)

### 2. Cálculo de VEC (Valor Econômico do Crime)
- Cálculo do valor econômico do delito
- Consideração de:
  - Valor subtraído
  - Percentual recuperado
  - Gravidade do crime
  - Antecedentes criminais
  - Reincidência\S
- Sugestão de pena de multa

### 3. Telas Verticais
- Interface otimizada para uso em modo retrato
- Layout responsivo com scroll vertical
- Design Material 3

## 🏗️ Arquitetura

O aplicativo foi desenvolvido seguindo os padrões modernos do Android:

- **Linguagem**: Kotlin
- **UI**: Jetpack Compose
- **Navegação**: Navigation Compose
- **Arquitetura**: MVVM (preparado para ViewModels)
- **Design**: Material Design 3

## 📂 Estrutura do Projeto

```
app/
├── src/main/
│   ├── AndroidManifest.xml
│   ├── kotlin/org/example/calculadorapenal/
│   │   ├── MainActivity.kt
│   │   ├── model/
│   │   │   ├── CalculoPenaModel.kt
│   │   │   └── CalculoVECModel.kt
│   │   ├── navigation/
│   │   │   ├── AppNavigation.kt
│   │   │   └── Screen.kt
│   │   └── ui/
│   │       ├── screens/
│   │       │   ├── HomeScreen.kt
│   │       │   ├── CalculoPenaScreen.kt
│   │       │   ├── CalculoVECScreen.kt
│   │       │   ├── ResultadoScreen.kt
│   │       │   └── SobreScreen.kt
│   │       └── theme/
│   │           ├── Color.kt
│   │           ├── Theme.kt
│   │           └── Type.kt
│   └── res/
│       └── values/
│           ├── colors.xml
│           ├── strings.xml
│           └── themes.xml
```

## 🚀 Como Executar

### Pré-requisitos
- Android Studio Hedgehog (2023.1.1) ou superior
- JDK 17
- Android SDK (API 24+)

### Passos
1. Abra o projeto no Android Studio
2. Sincronize o Gradle (Sync Project with Gradle Files)
3. Conecte um dispositivo Android ou inicie um emulador
4. Execute o app (Run > Run 'app')

## 📱 Requisitos do Dispositivo

- **Mínimo**: Android 7.0 (API 24)
- **Recomendado**: Android 14 (API 34)
- **Orientação**: Retrato (Portrait)

## ⚖️ Base Legal

O aplicativo baseia-se na legislação brasileira:

- Código Penal Brasileiro (Decreto-Lei nº 2.848/1940)
- Art. 59 - Fixação da pena
- Art. 68 - Método trifásico
- Arts. 61 a 67 - Agravantes e atenuantes
- Art. 33 - Regime de cumprimento de pena
- Art. 44 - Penas restritivas de direitos
- Art. 77 - Suspensão condicional da pena

## ⚠️ Aviso Legal

Este aplicativo é uma **ferramenta auxiliar** e não substitui a análise jurídica especializada por profissional habilitado. Os cálculos devem ser revisados e validados antes de serem utilizados em procedimentos oficiais.

## 🛠️ Melhorias Futuras

- [ ] Implementar ViewModels para gerenciamento de estado
- [ ] Adicionar persistência de dados (Room Database)
- [ ] Implementar exportação de resultados em PDF
- [ ] Adicionar histórico de cálculos
- [ ] Incluir banco de dados de crimes comuns
- [ ] Implementar compartilhamento de resultados
- [ ] Adicionar modo escuro
- [ ] Testes unitários e de integração

## 📄 Licença

© 2025 - Calculadora Penal. Todos os direitos reservados.

## 👨‍💻 Desenvolvimento

 Kotlin usando Android Jetpack Compose.
