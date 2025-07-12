# 🧪 Detalhamento dos Testes Automatizados

Este documento descreve os testes implementados no projeto `produto-api`.

---

## 📦 Estrutura de Testes

src/
└── test/
└── java/
└── com/
└── exemplo/
└── produto_api/
├── service/
│ ├── CategoriaServiceTest.java
│ └── ProdutoServiceTest.java
├── controller/
│ ├── CategoriaControllerTest.java
│ ├── CategoriaControllerWebMvcTest.java
│ └── ProdutoControllerWebMvcTest.java


---

## ✅ Camada de Serviço (`Service`)

### `CategoriaServiceTest`

- Criação de categoria com nome válido
- Lançamento de exceção ao tentar criar categoria duplicada

### `ProdutoServiceTest`

- Criação de produto com dados válidos
- Lançamento de exceção se a categoria não existir
- Busca de produto por ID (positivo e negativo)

---

## ✅ Camada de Controller (`WebMvcTest` e `SpringBootTest`)

### `CategoriaControllerTest` (com `@SpringBootTest`)

- Testa os endpoints reais com banco de dados limpo
- POST, GET, PUT, DELETE

### `CategoriaControllerWebMvcTest` (com `@WebMvcTest`)

- Teste unitário da camada controller usando mocks
- Simulação rápida sem acesso ao banco de dados real

### `ProdutoControllerWebMvcTest`

- Criação de produto
- Consulta por ID
- Atualização
- Exclusão
- Validação de entradas inválidas

---

## 🧰 Tecnologias de Teste

- **JUnit 5** – estrutura principal de testes
- **Mockito** – simulação de dependências com mocks
- **MockMvc** – simulação de requisições HTTP
- **AssertJ / Hamcrest** – expressões de verificação
- **Spring Boot Test** – integração com o contexto real da aplicação

---

## 📝 Observações

- Os testes são escritos com clareza e responsabilidade para facilitar a manutenção e confiabilidade da API.
- O uso de mocks permite testar cada camada isoladamente.
- Futuramente, testes de integração amplos com `Testcontainers` ou `H2` podem ser adicionados.

---
