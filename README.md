<h1 align="center">🎓 Academic System</h1>

<p align="center">
  Sistema de gestão acadêmica em <strong>Java</strong> com controle de acesso por papéis,
  persistência plugável (TXT/XML/JSON), interfaces de linha de comando e gráfica (JavaFX),
  testes automatizados e pipeline completo de CI/CD.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-25-ED8B00?style=flat-square&logo=openjdk&logoColor=white">
  <img src="https://img.shields.io/badge/JavaFX-25-1f8ac0?style=flat-square">
  <img src="https://img.shields.io/badge/Maven-C71A36?style=flat-square&logo=apachemaven&logoColor=white">
  <img src="https://img.shields.io/badge/JUnit_5-25A162?style=flat-square&logo=junit5&logoColor=white">
  <img src="https://img.shields.io/badge/Mockito-78A641?style=flat-square">
  <img src="https://img.shields.io/badge/JaCoCo-ECD53F?style=flat-square">
  <img src="https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=docker&logoColor=white">
  <img src="https://img.shields.io/badge/CI%2FCD-GitHub_Actions-2088FF?style=flat-square&logo=githubactions&logoColor=white">
  <img src="https://img.shields.io/badge/Licença-MIT-8b5cf6?style=flat-square">
</p>

---

## 📖 Sobre

**Academic System** é um projeto acadêmico desenvolvido para a disciplina de **Programação Orientada a Objetos** (Bacharelado em Ciência da Computação — UNIFAL-MG). A proposta tem **poucos requisitos funcionais de propósito** e concentra o desafio na **qualidade de engenharia ao redor**: orientação a objetos bem aplicada, padrões de projeto, segurança, persistência intercambiável, logging com auditoria, testes automatizados e DevOps.

O sistema gerencia **turmas** e **avaliações**, com acesso controlado por dois papéis (**Administrador** e **Professor**), e roda tanto em **linha de comando** quanto em **interface gráfica JavaFX** — reutilizando exatamente a mesma lógica de negócio.

## ✨ Funcionalidades

- 🔐 **Controle de acesso por papéis (RBAC)** — Administrador e Professor, com permissões declarativas.
- 🏫 **Gestão de turmas** (Administrador) e **avaliações** (Professor), com validação de domínio.
- 💾 **Persistência plugável** — salvar em **TXT**, **XML** ou **JSON**, escolhido em tempo de execução.
- 📊 **Relatórios** — avaliações por turma, composição de pesos e configuração de persistência.
- 🖥️ **Duas interfaces** — CLI interativa com menu dinâmico por papel **e** GUI em JavaFX.
- 📝 **Logging e auditoria** — log de aplicação + trilha de auditoria separada (login, acessos negados, persistências).
- ✅ **Suíte de testes** automatizados com relatório de cobertura.
- 🐳 **Docker** e **CI/CD** completos.

## 🏛️ Arquitetura

Arquitetura **em camadas** com responsabilidades bem separadas (alta coesão, baixo acoplamento). A interface nunca conversa direto com o domínio — passa pelo *controller*, que **autoriza e delega**:

```
  View (CLI / JavaFX)
        │
        ▼
  Controller  ──►  autoriza (Security)  ──►  nega ou prossegue
        │
        ▼
  Service (regra de negócio)
        │
        ▼
  Repository (Strategy: TXT / XML / JSON)  ──►  arquivo
        │
        ▼
  Model (domínio)  ◄── Validation (Bean Validation)
```

A **mesma lógica** (controller + services) atende a CLI e a GUI — a interface gráfica foi adicionada sem alterar uma linha de regra de negócio.

## 🧩 Padrões & conceitos aplicados

| Conceito / Padrão | Onde | Em uma frase |
| :--- | :--- | :--- |
| **Singleton** | `AcademicSystem` | Fonte única de verdade do estado em runtime (eager, thread-safe). |
| **Repository + Strategy** | `ClassRepository` + impl. TXT/XML/JSON | A persistência é uma estratégia intercambiável; o domínio não conhece o formato (OCP). |
| **RBAC** | `AuthorizationService` + `SystemOperation` | Matriz de permissões como **fonte única** — usada na checagem de acesso **e** na renderização do menu. |
| **GRASP Controller** | `AcademicSystemController` | Coordena e delega; concentra a autorização antes de chamar os services. |
| **Herança / Polimorfismo / Abstração** | `Assessment` (abstrata) → `Exam`, `Seminar`, `Assignment`, `PracticalAssignment` | Atributos comuns na superclasse; cada subclasse resolve `getType()` polimorficamente. |
| **Hierarquias de exceção** | pacote `exception` | Três famílias (domínio, entrada, segurança), tratadas em pontos únicos. |
| **Bean Validation** | anotações no `model` + `DomainValidator` | Regras declarativas no próprio domínio. |
| **Separação lógica × apresentação** | `report` + `ReportService` | Geradores produzem texto a partir do domínio; CLI imprime, JavaFX exibe. |

## 🛠️ Stack

`Java 25` · `JavaFX 25` · `Maven` · `Lombok` · `Jakarta Bean Validation` (Hibernate Validator) · `Jackson` (JSON) · `SLF4J` + `Logback` · `JUnit 5` · `Mockito` · `JaCoCo` · `Docker`

## 🚀 Como executar

> **Pré-requisitos:** JDK 25 e Maven 3.9+. (Para JDK 21, troque `<maven.compiler.release>` no `pom.xml` para `21`.)

```bash
cd academic-system

mvn clean verify        # compila, roda os testes e gera a cobertura (JaCoCo)
mvn exec:java           # executa a versão de linha de comando (CLI)
mvn javafx:run          # executa a versão gráfica (JavaFX)

# ou empacote e rode o jar:
mvn clean package
java -jar target/AcademicSystem-1.0-SNAPSHOT.jar
```

**Credenciais de demonstração** (`src/main/resources/users.txt`):

| Usuário | Senha | Papel |
| :--- | :--- | :--- |
| `admin` | `admin123` | Administrador |
| `professor` | `prof123` | Professor |

> Em tempo de execução são criadas as pastas `data/` (arquivos de persistência) e `logs/` (aplicação e auditoria) — ambas ignoradas pelo Git.

### 🐳 Docker

```bash
cd academic-system
docker build -t academic-system .
docker run -it academic-system      # -it é necessário: a CLI é interativa
```

## ✅ Testes & cobertura

Suíte com **13 classes de teste** em **JUnit 5**, cobrindo modelo, validação, segurança (autenticação e autorização), persistência, relatórios, logging e o controller. O **Mockito** é usado para testes de interação — por exemplo, `verify()` prova que o controller delega ao service correto, e que uma operação negada **não chega** a tocar no service.

```bash
mvn clean verify
# relatório de cobertura em: academic-system/target/site/jacoco/index.html
```

## 🔄 CI/CD

Quatro workflows do **GitHub Actions** (com *branch protection* na `main` exigindo PR + checks verdes):

| Workflow | Gatilho | O que faz |
| :--- | :--- | :--- |
| **CI** | push / PR na `main` | Build + testes + publica o relatório JaCoCo como artefato. |
| **PR Validation** | abertura de PR | Garante que build e testes passam antes do merge. |
| **Docker Publish** | push na `main` / tag | Constrói e publica a imagem no GitHub Container Registry (GHCR), versionada por sha/tag. |
| **Release** | tag `v*` | Build + testes e publicação do `.jar` em GitHub Releases. |

## 📂 Estrutura

```
academic-system/
└─ src/main/java/org/example/academic/system/
   ├─ model/         # domínio (Assessment, AcademicClass, User, Role…)
   ├─ repository/    # persistência (Strategy: TXT/XML/JSON)
   ├─ service/       # regras de negócio
   ├─ security/      # autenticação, autorização (RBAC), sessão
   ├─ controller/    # coordenação + autorização
   ├─ validation/    # Bean Validation
   ├─ report/        # geradores de relatório
   ├─ exception/     # hierarquias de exceção
   └─ view/          # CLI + view/gui (telas JavaFX)
```

## 🔮 Melhorias futuras

- Carregar dados persistidos de volta (`load()` na interface de repositório).
- Hash de senha (ex.: BCrypt) em vez de texto plano.
- Gestão de usuários pela aplicação.

## 📜 Licença

Distribuído sob a licença **MIT**. Veja `LICENSE`.

---

<p align="center">
  <sub>Projeto acadêmico · Programação Orientada a Objetos · UNIFAL-MG · por <a href="https://github.com/gabrielhsp-sys">Gabriel Henrique</a></sub>
</p>
