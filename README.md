# 🎬 Movieflix

Aplicação Spring Boot para gerenciamento de filmes e séries.

## 📋 Descrição

Movieflix é uma aplicação backend desenvolvida com Spring Boot que fornece uma API REST para gerenciamento de catálogo de filmes e séries.

## 🚀 Tecnologias

- **Java 17+**
- **Spring Boot 3.4.12**
  - Spring Data JPA
  - Spring Security
  - Spring Web
  - Spring Validation
- **PostgreSQL** - Banco de dados
- **Flyway** - Migrações de banco de dados
- **Lombok** - Redução de código boilerplate
- **Maven** - Gerenciamento de dependências

## 📦 Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- PostgreSQL 12+ (ou Docker)
- Docker (opcional, para rodar PostgreSQL em container)

## ⚙️ Configuração

### 1. Banco de Dados

#### Opção A: PostgreSQL com Docker (Recomendado)

#### Opção B: PostgreSQL Local

Crie um banco de dados chamado `movieflix`:

### 2. Variáveis de Ambiente

Configure as seguintes variáveis de ambiente:

```properties
DB_URL=jdbc:postgresql://localhost:5432/movieflix
DB_USERNAME=postgres
DB_PASSWORD=sua_senha
JWT_SECRET=secret
```

#### No IntelliJ IDEA:
1. Run → Edit Configurations
2. Environment variables → Adicione as variáveis acima

## 🏃 Como Executar

### Via Maven

```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

### Via IDE

Execute a classe `MovieflixApplication.java`

### Via JAR

```bash
# Compilar
.\mvnw.cmd clean package

# Executar
java -jar target/movieflix-0.0.1-SNAPSHOT.jar
```

### Arquitetura

O projeto segue uma arquitetura em camadas:

```
src/main/java/br/com/larrydev/movieflix/
├── config/         # Configurações do Spring e Security
├── controller/     # Controllers REST
├── entity/         # Entidades JPA
├── repository/     # Repositórios Spring Data
├── service/        # Regras de negócio
├── exception/      # Exceções customizadas
└── mapper/         # Conversão entre DTOs e entidades
```

### 🌐 Endpoints

A aplicação estará disponível em: `http://localhost:8080`

### 📚 Documentação da API (Swagger/OpenAPI)

Acesse a documentação interativa da API através do Swagger UI:

- **Swagger UI:** `http://localhost:8080/swagger/swagger-ui/index.html`
- **OpenAPI Spec:** `http://localhost:8080/api/api-docs`

O Swagger fornece uma interface interativa para explorar e testar todos os endpoints da API.