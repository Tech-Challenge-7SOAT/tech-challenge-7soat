# Projeto TECH CHALLENGE

## Descrição do Projeto

Este projeto é uma aplicação de gestão de pedidos para um fastfood, desenvolvida utilizando Kotlin e Spring Boot. O objetivo principal é permitir a criação, atualização, visualização e exclusão de pedidos, clientes e produtos. A aplicação utiliza JPA para persistência de dados e oferece uma API RESTful para interações.

## Objetivos

- **Gerenciar Clientes**: Possibilitar o cadastro, consulta, atualização e remoção de clientes.
- **Gerenciar Produtos**: Permitir o cadastro, consulta, atualização e remoção de produtos.
- **Gerenciar Pedidos**: Facilitar o processo de criação, consulta, atualização e remoção de pedidos.
- **Validação de Dados**: Implementar validações, como verificação de CPF já cadastrado.
- **Manter a integridade dos dados**: Garantir que todas as referências sejam salvas corretamente no banco de dados.
- **Fornecer uma API RESTful**: Permitir a integração com outras aplicações via endpoints HTTP.

## Tecnologias Utilizadas

- **Kotlin**: Linguagem principal de desenvolvimento.
- **Spring Boot**: Framework para simplificar a criação de aplicações Java.
- **JPA/Hibernate**: Para persistência e gerenciamento de dados.
- **Maven**: Gerenciamento de dependências e build.
- **Swagger**: Documentação da API REST.

## Como Iniciar o Projeto Localmente

### Pré-requisitos

- **Java JDK 17**: Necessário para rodar o Spring Boot.
- **Maven**: Ferramenta de build e gerenciamento de dependências.

### Passos para Configuração e Execução

1. **Clone o Repositório**

   ```sh
   git clone https://github.com/Tech-Challenge-7SOAT/tech-challenge-7soat.git
   cd tech-challenge-7soat                                                                                                           

2. **Construa e Inicie os Containers**
   
   ```sh
   docker-compose up -d --build

Esse comando irá:

- Construir a imagem Docker da aplicação.
- Construir a imagem Docker do banco de dados Postgres, já criando o schema.
- Iniciar os containers definidos no docker-compose.yml.

3. **Acesse a Aplicação**

A aplicação estará disponível em: http://localhost:8080

## Documentação da API

A documentação da API, gerada pelo Swagger, pode ser acessada em: https://localhost:8080/swagger-ui/index.html

