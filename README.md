# 🛠️ JavaKenshinGakusei - Produto API

Bem-vindo(a) ao repositório do **Produto API**, um microsserviço desenvolvido com Spring Boot para gerenciar produtos e categorias, parte do meu aprendizado dedicado em Java.

---

## 🎯 Sobre o projeto

Este projeto é uma aplicação backend que implementa:

- Cadastro e listagem de produtos e categorias
- Validação de dados com Bean Validation
- Persistência com PostgreSQL e Flyway para migrações de banco
- Testes automatizados com JUnit e MockMvc
- Documentação de API interativa com Swagger (springdoc-openapi)

É um projeto pessoal para praticar conceitos essenciais de microsserviços e desenvolvimento Java moderno.

---

## 🧰 Tecnologias usadas

- Java 21  
- Spring Boot 3.4.4  
- Maven 4.0.0  
- PostgreSQL  
- FlywayDB  
- Spring Data JPA  
- JUnit 5 + MockMvc  
- Springdoc OpenAPI (Swagger UI)

---

## ⚙️ Como rodar localmente

### Pré-requisitos

- Java 21 instalado  
- PostgreSQL rodando (com banco criado)  
- Maven 4.0.0+  

### Passos

1. Clone o repositório:

```bash
git clone https://github.com/JavaKenshinGakusei/produto-api.git
cd produto-api
Configure o banco no src/main/resources/application.properties:

properties
spring.datasource.url=jdbc:postgresql://localhost:5432/produtodb
spring.datasource.username=postgres
spring.datasource.password=postgres
Crie o banco de dados no PostgreSQL (caso não exista):

sql
CREATE DATABASE produtodb;
Execute o projeto:

bash
./mvnw spring-boot:run
Acesse a documentação da API via Swagger:

bash
http://localhost:8181/swagger-ui.html


🧪 Testes
Para rodar os testes automatizados:
./mvnw test


🚀 Próximos passos
Implementar CRUD completo de produto e categoria

Desenvolver microsserviços complementares: user-api e pedido-api

Adicionar autenticação com Spring Security e JWT

Dockerizar a aplicação para deploy fácil

Criar integração entre microsserviços via REST

👩‍💻 Sobre mim
Sou a JavaKenshinGakusei, estudante dedicada ao aprendizado de Java e Spring Boot, focada em microsserviços, testes e desenvolvimento backend moderno.

📄 Licença
Este projeto está licenciado sob a licença MIT. Veja o arquivo LICENSE para detalhes.

Obrigado por visitar meu repositório! 🌱

