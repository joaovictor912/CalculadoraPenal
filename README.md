# Calculadora Penal (Android)

Calculadora Penal é um aplicativo Android desenvolvido para auxiliar profissionais do direito e operadores do sistema penitenciário no cálculo de execução penal e remição de pena.

Este repositório contém o código-fonte do aplicativo Android desenvolvido em **Kotlin** com **Jetpack Compose** para interface moderna e responsiva.

## Principais funcionalidades

### Cálculo de Execução Penal
Calcula automaticamente as datas previstas para:
- **Progressão de Regime** (aberto, semiaberto, fechado)
- **Livramento Condicional**
- Estimativas baseadas no tipo de crime e status do apenado

**Parâmetros considerados:**
- Pena total (anos, meses e dias)
- Data de início do cumprimento
- Tempo de detração (prisão provisória)
- Tipo de crime (comum, violência/grave ameaça, hediondo, hediondo com morte)
- Status do apenado (primário ou reincidente)

### Cálculo de Remição de Pena
Calcula dias de pena remidos com base em:
- **Trabalho**: 3 dias trabalhados = 1 dia de pena remido
- **Estudo**: 12 horas de estudo = 1 dia de pena remido

**Recursos adicionais:**
- Campos opcionais de contato (nome, WhatsApp, e-mail, processo)
- Envio automático de mensagem para advogado via WhatsApp
- Detalhamento completo dos cálculos
- Orientações sobre próximos passos

### Tela "Sobre"
- Informações sobre o aplicativo
- Referências legais
- Avisos sobre uso responsável dos resultados

## Design e Interface

- **Tema Escuro** otimizado para uso prolongado
- **Material Design 3** com cores institucionais:
  - Azul (Primary/Secondary): `#000C50`
  - Laranja (Tertiary/Accent): `#D9944A`
- **Splash Screen** profissional com logo do escritório
- Interface intuitiva com validações em tempo real

## Estrutura do projeto

```text
app/
  src/main/
    kotlin/org/example/calculadorapenal/
      MainActivity.kt              # Ponto de entrada + Splash Screen
      SplashActivity.kt           # Atividade de splash (legado)
      model/
        CalculoExecucaoPenalModel.kt    # Lógica de execução penal
        CalculoRemicaoModel.kt          # Lógica de remição
      navigation/
        AppNavigation.kt          # Configuração de rotas
        Screen.kt                 # Definição de telas
      ui/
        screens/
          HomeScreen.kt                     # Tela inicial
          CalculoExecucaoPenalScreen.kt    # Entrada de dados (execução)
          ResultadoExecucaoPenalScreen.kt  # Resultados (execução)
          CalculoRemicaoScreen.kt          # Entrada de dados (remição)
          ResultadoRemicaoScreen.kt        # Resultados (remição)
          SobreScreen.kt                   # Informações do app
        theme/
          Color.kt              # Definições de cores
          Theme.kt              # Tema Material3
          Type.kt               # Tipografia
    res/
      drawable/
        splash_logo.jpg         # Logo do escritório
        splash_background.xml   # Background da splash
      values/
        colors.xml              # Paleta de cores
        strings.xml             # Textos do app
        themes.xml              # Temas Android
```

## Como executar

### Requisitos de desenvolvimento

- **Android Studio** Hedgehog (2023.1.1) ou superior
- **JDK 17** ou superior
- **Android SDK**:
  - Mínimo: API 24 (Android 7.0)
  - Alvo: API 34 (Android 14)
  - Compile SDK: 34

### Executando pelo Android Studio

1. Clone o repositório:
   ```bash
   git clone https://github.com/joaovictor912/CalculadoraPenal.git
   ```

2. Abra o projeto no Android Studio

3. Aguarde o sync do Gradle (pode demorar alguns minutos na primeira vez)

4. Conecte um dispositivo Android via USB (com depuração USB ativada) ou inicie um emulador

5. Clique em **Run > Run 'app'** ou pressione `Shift+F10`

### Executando pela linha de comando (Windows PowerShell)

No diretório raiz do projeto:

```powershell
# Limpar builds anteriores e compilar
.\gradlew.bat clean assembleDebug

# Instalar no dispositivo/emulador conectado
.\gradlew.bat installDebug

# Ou fazer tudo de uma vez
.\gradlew.bat clean installDebug
```

### Executando pela linha de comando (Linux/Mac)

```bash
# Limpar builds anteriores e compilar
./gradlew clean assembleDebug

# Instalar no dispositivo/emulador conectado
./gradlew installDebug

# Ou fazer tudo de uma vez
./gradlew clean installDebug
```

## Requisitos do dispositivo

- **Mínimo**: Android 7.0 (API 24)
- **Recomendado**: Android 14 (API 34)
- **Orientação**: Retrato (portrait)
- **Permissões necessárias**:
  - Internet (para links externos e WhatsApp)
  - Nenhuma permissão sensível é solicitada

## Base legal

As funcionalidades foram desenvolvidas com base na legislação brasileira:

- **Código Penal Brasileiro** (Decreto-Lei nº 2.848/1940)
- **Lei de Execução Penal - LEP** (Lei nº 7.210/1984)
  - Art. 126: Remição pelo trabalho
  - Art. 126-A: Remição pelo estudo
- **Lei de Crimes Hediondos** (Lei nº 8.072/1990)
- Jurisprudência aplicável do STF e STJ

### Regras de remição implementadas

- **Trabalho**: A cada 3 dias trabalhados, remite-se 1 dia da pena
- **Estudo**: A cada 12 horas de frequência escolar (atividade de ensino fundamental, médio, profissionalizante, superior ou de requalificação), remite-se 1 dia da pena

## Aviso legal

**IMPORTANTE**: Este software fornece **estimativas** e **ferramentas de apoio** à análise jurídica.

- NÃO substitui a avaliação de um profissional habilitado
- NÃO possui valor oficial ou decisório
- Os resultados devem ser verificados por advogados ou especialistas
- Consulte sempre a legislação atualizada e jurisprudência pertinente
- Cada caso possui particularidades que podem alterar os cálculos

**Os desenvolvedores não se responsabilizam** por decisões tomadas com base exclusivamente nos resultados do aplicativo.

## Contribuição

Contribuições são bem-vindas! Para contribuir:

1. Faça um **fork** do repositório
2. Crie uma **branch** para sua feature: `git checkout -b feature/nome-da-feature`
3. **Commit** suas mudanças: `git commit -m 'feat: adiciona nova funcionalidade'`
4. **Push** para a branch: `git push origin feature/nome-da-feature`
5. Abra um **Pull Request**

### Padrões de commit

Seguimos o padrão **Conventional Commits**:
- `feat:` nova funcionalidade
- `fix:` correção de bug
- `docs:` alterações na documentação
- `style:` formatação, espaços, etc.
- `refactor:` refatoração de código
- `test:` adição de testes
- `chore:` tarefas de build, configurações, etc.

## Tecnologias utilizadas

- **Kotlin** 1.9.0
- **Jetpack Compose** - UI moderna e declarativa
- **Material Design 3** - Design system do Google
- **Navigation Compose** - Navegação entre telas
- **Gradle** 8.4 - Build system
- **Android SDK 34** - APIs do Android

## Licença

Este projeto está disponível sob a licença **MIT** — veja o arquivo `LICENSE` para detalhes.

## Autores

- **João Victor Pessoa de Lima dos Anjos**
- **João Paulo Bonaguiro Ramirez**

## Contato

Para dúvidas, sugestões ou relatar problemas:
- Abra uma [**issue**](https://github.com/joaovictor912/CalculadoraPenal/issues) neste repositório
- Entre em contato com os autores

## Changelog

### Versão atual (2025)
- Substituição do cálculo de VEC por Cálculo de Remição de Pena
- Implementação de splash screen personalizada
- Tema escuro otimizado com cores institucionais
- Integração com WhatsApp para contato direto
- Validações aprimoradas nos formulários
- Interface totalmente em Material Design 3

---

**Última atualização**: Novembro 2025  
**Versão**: 1.0.0
