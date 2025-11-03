# Calculadora Penal (Android)

Aplicativo Android em Kotlin para auxiliar no cálculo de Execução Penal e do Valor Econômico do Crime (VEC), com interface moderna em Jetpack Compose.

##  Funcionalidades

- Execução Penal
  - Cálculo de datas de progressão de regime considerando: pena total, data de início, detração, tipo de crime e status do apenado.
  - Estimativa de livramento condicional conforme frações legais aplicáveis.
- VEC (Valor Econômico do Crime)
  - Cálculo do VEC a partir dos dados informados, com apresentação do resultado e dados de contato opcionais.
- Sobre
  - Informações institucionais do app, versão e aviso legal.

##  Estrutura (resumo)

```
app/
  src/main/
    AndroidManifest.xml
    kotlin/org/example/calculadorapenal/
      MainActivity.kt
      navigation/
        AppNavigation.kt
        Screen.kt
      model/
        CalculoExecucaoPenalModel.kt
        CalculoVECModel.kt
      ui/screens/
        HomeScreen.kt
        CalculoVECScreen.kt
        CalculoExecucaoPenalScreen.kt
        ResultadoExecucaoPenalScreen.kt
        SobreScreen.kt
      ui/theme/
        Color.kt
        Theme.kt
        Type.kt
```

## Como executar

### Requisitos
- Android Studio 2023.1.1 (Hedgehog) ou superior
- JDK 17
- Android SDK API 34 (recomendado) e API 24 (mínimo)

### Android Studio
1) Abra a pasta do projeto no Android Studio
2) Aguarde o Sync do Gradle
3) Conecte um dispositivo ou abra um emulador
4) Run > Run 'app'

### Linha de comando (Windows PowerShell)

No diretório raiz do projeto:

```powershell
./gradlew.bat clean
./gradlew.bat assembleDebug
# Com dispositivo/emulador conectado:
./gradlew.bat installDebug
```

##  Requisitos do dispositivo

- Mínimo: Android 7.0 (API 24)
- Recomendado: Android 14 (API 34)
- Orientação: Retrato

## ⚖️ Base legal (referências)

- Código Penal Brasileiro (Decreto-Lei nº 2.848/1940)
- Art. 33 (regimes) e Art. 83 (livramento condicional)
- LEP – Lei de Execução Penal (progressões)

Observação: O módulo de Execução Penal aplica frações legais de forma parametrizada, considerando tipo de crime e status do apenado, e apresenta estimativas de datas de progressão e livramento para apoio à análise.

## Aviso legal

Este aplicativo é uma ferramenta auxiliar e não substitui a análise jurídica especializada. Os resultados devem ser revisados por profissional habilitado antes de qualquer uso oficial.

##  Contribuição

Sinta-se à vontade para abrir issues e pull requests. Sugestões são bem-vindas.

##  Licença

© 2025 — Calculadora Penal. Todos os direitos reservados.
