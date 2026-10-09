# 🔐 Cofre — Password Manager

**Gerenciador de senhas desenvolvido em Java, com foco em segurança, persistência de dados, arquitetura em camadas e boas práticas de desenvolvimento backend.**

O Cofre é uma aplicação para gerenciamento seguro de credenciais, permitindo armazenar, consultar, atualizar e excluir informações de autenticação, além de gerar senhas criptograficamente seguras.

O projeto explora conceitos fundamentais e avançados do ecossistema Java, desde Programação Orientada a Objetos até criptografia, persistência de dados, testes automatizados e princípios de arquitetura de software.

## 🛠️ Tecnologias e ferramentas

| Tecnologia | Aplicação no projeto |
|---|---|
| **Java** | Linguagem principal |
| **Maven** | Gerenciamento de dependências e build |
| **JDBC** | Comunicação com banco de dados |
| **SQLite** | Persistência local das credenciais |
| **Java Cryptography Architecture (JCA)** | Recursos criptográficos |
| **Java Collections Framework** | Manipulação de coleções |
| **Stream API** | Processamento de dados |
| **JUnit 5** | Testes automatizados |
| **Git e GitHub** | Versionamento e gerenciamento do código |

## 🧠 Conceitos aplicados

### Programação Orientada a Objetos

- Encapsulamento e abstração
- Classes, objetos e interfaces
- Herança e polimorfismo, quando apropriados
- Construtores e modificadores de acesso
- Composição de objetos
- Tratamento de exceções

### Java moderno

- Collections e Generics
- Stream API
- Expressões lambda
- Interfaces funcionais
- Optional
- Manipulação de arquivos
- API de segurança do Java

### Arquitetura e boas práticas

- Arquitetura em camadas
- Separação de responsabilidades
- Repository Pattern
- Service Layer
- Dependency Injection
- Princípios SOLID
- Clean Code
- Baixo acoplamento e alta coesão
- Refatoração e manutenibilidade

### Persistência de dados

- Operações CRUD
- Banco de dados relacional
- SQL
- JDBC
- Prepared Statements
- Mapeamento entre objetos e registros
- Tratamento de conexões e transações

### Segurança da informação

- Criptografia simétrica com AES-256-GCM
- Derivação de chaves a partir de senha mestre
- Salt e gerenciamento de parâmetros criptográficos
- Geração segura de senhas com `SecureRandom`
- Proteção de informações sensíveis
- Validação de entradas
- Princípios de armazenamento seguro de credenciais

### Testes e qualidade

- Testes unitários com JUnit 5
- Testes de operações CRUD
- Validação de regras de negócio
- Testes de casos excepcionais
- Organização de código para testabilidade

## ⚙️ Funcionalidades

- Cadastro e gerenciamento de credenciais
- Consulta e pesquisa de credenciais
- Atualização e exclusão de registros
- Geração de senhas fortes e personalizáveis
- Armazenamento criptografado
- Persistência local em banco de dados
- Autenticação utilizando senha mestre
- Validação de dados
- Interface de linha de comando (CLI)

## 🏗️ Arquitetura

O projeto utiliza uma organização em camadas, visando separar as regras de negócio dos mecanismos de armazenamento e da interface com o usuário.

```text
br.com.cofre
│
├── Main.java
│
├── model/
│   └── Credencial.java
│
├── repository/
│   ├── CredencialRepository.java
│   └── SqliteCredencialRepository.java
│
├── service/
│   ├── CredencialService.java
│   └── GeradorSenhaService.java
│
├── security/
│   ├── CriptografiaService.java
│   └── AutenticacaoService.java
│
├── database/
│   └── DatabaseConnection.java
│
├── exception/
│
└── ui/
```

**Model:** representação das entidades do domínio.

**Repository:** abstração do acesso e da persistência dos dados.

**Service:** implementação das regras de negócio.

**Security:** gerenciamento dos mecanismos de criptografia e autenticação.

**Database:** configuração e gerenciamento da conexão com o banco de dados.

**UI:** interação entre o usuário e a aplicação.

## 🔒 Segurança

A arquitetura de segurança é baseada na proteção das credenciais antes de sua persistência.

As senhas armazenadas são protegidas por criptografia autenticada, utilizando AES-GCM. A chave de criptografia é derivada da senha mestre por meio de um algoritmo de derivação de chaves apropriado.

A geração de senhas utiliza `SecureRandom`, evitando geradores pseudoaleatórios inadequados para aplicações de segurança.

O objetivo é aplicar conceitos de criptografia e autenticação de maneira consistente, utilizando APIs e algoritmos estabelecidos.

## 🎯 Objetivo do projeto

O Cofre foi desenvolvido como projeto de portfólio e estudo prático de **desenvolvimento backend com Java**.

Seu propósito é demonstrar a aplicação de conceitos de orientação a objetos, modelagem de software, persistência, arquitetura, testes e segurança em uma aplicação com regras de negócio e responsabilidades bem definidas.

O desenvolvimento também busca consolidar conhecimentos sobre organização de código, qualidade de software e utilização de recursos da plataforma Java.

---

**Java | Backend Development | OOP | SQL | Cryptography | Software Architecture**
