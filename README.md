## Calculadora Penal (Android)

Calculadora Penal é um aplicativo Android desenvolvido para auxiliar profissionais do direito e operadores do sistema penitenciário no cálculo de execução penal e em estimativas relacionadas ao Valor Econômico do Crime (VEC).

Este repositório contém o código-fonte do aplicativo Android (Kotlin + Jetpack Compose) e instruções para build e execução.

## Principais funcionalidades

- Cálculo de execução penal: estimativas de progressão de regime, livramento condicional e datas relevantes a partir de dados de pena, início, detração e natureza do crime.
- Cálculo do Valor Econômico do Crime (VEC) a partir dos parâmetros informados.
- Tela de Sobre com referências legais e aviso sobre o uso dos resultados.

## Estrutura resumida do projeto

```text
app/
  src/main/
    AndroidManifest.xml
    kotlin/... (código fonte em Kotlin)
    res/ (recursos: layouts, imagens, strings)
```

## Como executar

### Requisitos de desenvolvimento

- Android Studio (versão compatível com o Android Gradle Plugin usado no projeto)
- JDK 17
- Android SDK com as APIs alvo e mínimas definidas no `build.gradle` (ex.: API 24+)

### Executando pelo Android Studio

1. Abra a pasta do projeto no Android Studio.
2. Aguarde o sync do Gradle e a indexação.
3. Conecte um dispositivo Android ou inicie um emulador.
4. Clique em Run > Run 'app'.

### Executando pela linha de comando (Windows PowerShell)

No diretório raiz do projeto execute:

```powershell
.
\gradlew.bat clean; \gradlew.bat assembleDebug
# Para instalar no dispositivo/emulador conectado:
\gradlew.bat installDebug
```

> Observação: em PowerShell, use `\\gradlew.bat` ou `./gradlew.bat` conforme preferir.

## Requisitos do dispositivo

- Mínimo: Android 7.0 (API 24)
- Recomendado: Android 14 (API 34)
- Orientação da UI: retrato

## Base legal

As funcionalidades deste aplicativo foram concebidas com base na legislação brasileira aplicável, entre outras referências:

- Código Penal Brasileiro (Decreto-Lei nº 2.848/1940)
- Lei de Execução Penal (LEP)
- Artigos relevantes do Código Penal relacionados a regimes de cumprimento e livramento condicional

As referências acima são indicativas; para aplicação prática, consulte sempre a legislação atualizada e a jurisprudência pertinente.

## Aviso legal

Este software fornece estimativas e ferramentas de apoio à análise jurídica. Não substitui a avaliação de um profissional habilitado. Os resultados disponibilizados pelo aplicativo devem ser verificados por advogados ou especialistas antes de qualquer uso oficial ou decisório.

## Contribuição

Contribuições são bem-vindas. Para contribuir:

1. Abra uma issue descrevendo o bug ou feature desejada.
2. Crie uma branch com a sua modificação: `feature/nome-da-feature`.
3. Abra um pull request apontando para `main`.

Siga as boas práticas de commits e escreva testes quando aplicável.

## Licença

Este projeto está disponível sob a licença MIT — veja o arquivo `LICENSE` para detalhes. Se preferir outro modelo de licença, entre em contato com os autores.

## Autores

- João Victor Pessoa de Lima dos Anjos
- João Paulo Bonaguiro Ramirez

## Contato

Para dúvidas, sugestões ou problemas, abra uma issue neste repositório ou entre em contato com os autores.

---

Última atualização: 2025
