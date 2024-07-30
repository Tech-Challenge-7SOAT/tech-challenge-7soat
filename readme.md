# Projeto TECH CHALLENGE

## Descrição do Projeto

Este projeto é uma aplicação de gestão de pedidos para um fastfood para atender o Tech Challenge da FIAP, desenvolvida utilizando Kotlin e Spring Boot. O objetivo principal é permitir a criação, atualização, visualização e exclusão de pedidos, clientes e produtos. A aplicação utiliza JPA para persistência de dados e oferece uma API RESTful para interações.

## Objetivos

- **Gerenciar Clientes**: Possibilitar o cadastro, consulta, atualização e remoção de clientes.
- **Gerenciar Produtos**: Permitir o cadastro, consulta, atualização e remoção de produtos.
- **Gerenciar Pedidos**: Facilitar o processo de criação, consulta, atualização e remoção de pedidos.
- **Validação de Dados**: Implementar validações, como verificação de CPF já cadastrado.
- **Manter a integridade dos dados**: Garantir que todas as referências sejam salvas corretamente no banco de dados.
- **Fornecer uma API RESTful**: Permitir a integração com outras aplicações via endpoints HTTP.
- **Orquetração de Conteiners**: Utilizar o Minikube para orquestrar a execução da aplicação em containers.

## Tecnologias Utilizadas

- **Kotlin**: Linguagem principal de desenvolvimento.
- **Spring Boot**: Framework para simplificar a criação de aplicações Java.
- **JPA/Hibernate**: Para persistência e gerenciamento de dados.
- **Maven**: Gerenciamento de dependências e build.
- **Swagger**: Documentação da API REST.
- **PostgressSQL**: Para facilitar a execução da aplicação em containers.
- **Docker**: Para facilitar a execução da aplicação em containers.
- **MiniKube**: Para orquestrar a execução da aplicação em containers.

## Video de Demonstração da Arquitetura
- **[Infraestrutura]**: https://youtu.be/N3vMcbQbMwI
- **[API's]**: https://www.youtube.com/watch?v=z9F2ppayEC4

## Como Iniciar o Projeto Localmente

### Pré-requisitos

Antes de iniciar o projeto, você precisa instalar algumas ferramentas:

- **[Java JDK 17]**: Necessário para rodar o Spring Boot.
- **[Maven]**: Ferramenta de build e gerenciamento de dependências.
- **[Minikube]**: https://minikube.sigs.k8s.io/docs/start/
- **[Kubectl]**: https://kubernetes.io/docs/tasks/tools/
- **[Docker]**: https://docs.docker.com/get-docker/

### Passos para Configuração e Execução

1. **Clone o Repositório na Branch "Master"**

   ```sh
   git clone https://github.com/Tech-Challenge-7SOAT/tech-challenge-7soat.git
   cd tech-challenge-7soat                                                                                                           

2. **Inicie o Minikube**

   ```bash
   minikube start

3. **Navegue até a pasta /terraform**

   ```bash
   cd terraform

4. **Execute os seguintes comandos do Terraform**

   ```bash
   terraform init
   terraform plan
   terraform apply --auto-approve

5. **Executar os comandos do Minikube**

   ```bash
   minikube dashboard
   minikube service fastfood-api -n fastfood-api
   
6. **Realizar teste de carga**

   ```bash
   k6 run --vus 10 --duration 30s k6.js

7. **Acesse a Aplicação**

A aplicação estará disponível no endereço gerado pelo comando "minikube service fastfood-api -n fastfood-api".

## Documentação da API

### A documentação da API, gerada pelo Swagger, pode ser acessada em: 
- https://{SERVICE-IP:PORT}/swagger-ui/index.html;
- [Arquivo "swagger.yaml" no diretório "docs".](https://github.com/Tech-Challenge-7SOAT/tech-challenge-7soat/blob/master/docs/swagger.yaml)

## Desenho da Infraestrutura
- [Arquivo "infraestrutura.jpeg" no diretório "docs"](https://github.com/Tech-Challenge-7SOAT/tech-challenge-7soat/blob/master/docs/infraestrutura.jpeg)

## Collection para Testes
- [Arquivo "postman_collection.json" no diretório "docs"](https://github.com/Tech-Challenge-7SOAT/tech-challenge-7soat/blob/master/docs/postman-collection.json)

# Guia de Utilização

## Product

### 1.1) (POST /products/product) - Criação de um novo produto 

Este endpoint é usado para criar um novo produto. O corpo da solicitação deve conter as informações do produto. Se o tipo de categoria for inválido ou se não for possível encontrar um produto usando a categoria fornecida, uma resposta de erro será retornada.

**Outros endpoints para "Product" podem ser utilizados conforme necessário, desde que o produto tenha sido criado anteriormente.**

- **(GET /products/category) Obtenção de produtos por categoria:**
  Este endpoint é usado para obter uma lista de produtos com base na categoria especificada. Se o tipo de categoria for inválido ou se não for possível encontrar um produto usando a categoria fornecida, uma resposta de erro será retornada.

- **(GET /products) Obtenção de todos os produtos:**
  Este endpoint é usado para obter uma lista de todos os produtos. Se não for possível encontrar produtos, uma resposta de erro será retornada.

- **(PATCH /products/product/{id}) Edição de um produto:**
  Este endpoint é usado para editar um produto existente. O corpo da solicitação deve conter as informações atualizadas do produto. Se não for possível encontrar um produto usando o ID fornecido, uma resposta de erro será retornada.

- **(DELETE /products/product/{id}) Exclusão de um produto:**
  Este endpoint é usado para excluir um produto existente. Se não for possível encontrar um produto usando o ID fornecido, uma resposta de erro será retornada.

---

## Customer

### 2.1) (POST /customers/customer) - Criação de um novo cliente 

Este endpoint é usado para criar um novo cliente. O corpo da solicitação deve conter as informações do cliente. Se o CPF fornecido já existir no banco de dados, uma resposta de erro será retornada.

**Outros endpoints para "Customer" podem ser utilizados conforme necessário, desde que o cliente tenha sido criado anteriormente.**

- **(GET /customers/customer/{cpf}) Obtenção de um cliente existente pelo CPF:**
  Este endpoint é usado para obter as informações de um cliente específico pelo CPF. Se o CPF fornecido não existir no banco de dados, uma resposta de erro será retornada.

- **(GET /customers) Obtenção de todos os clientes:**
  Este endpoint é usado para obter uma lista de todos os clientes no banco de dados.

---

## Order

### 3.1) (POST /orders/order) - Criar um novo pedido 

Este endpoint é usado para criar um novo pedido. O corpo da solicitação deve conter as informações do pedido. O CPF é necessário e se não for fornecido, ou se o cliente com o CPF fornecido não existir, ou se o produto com os IDs fornecidos não for encontrado, uma resposta de erro será retornada.

**Outros endpoints para "Order" podem ser utilizados conforme necessário, desde que o cliente, produto e pedido tenham sido criados anteriormente.**

- **(GET /orders/order/{id}) Obter um pedido pelo ID:**
  Este endpoint é usado para obter um pedido específico pelo seu ID. Se o ID for inválido ou se o pedido não for encontrado, uma resposta de erro será retornada.

- **(PATCH /orders/order) Editar um pedido:**
  Este endpoint é usado para editar um pedido existente. O corpo da solicitação deve conter as informações atualizadas do pedido. Se o pedido já estiver finalizado ou se o pagamento tiver sido recusado, ou se o pedido não for encontrado, ou se o cliente com o CPF fornecido não existir, ou se os produtos com os IDs fornecidos não forem encontrados, uma resposta de erro será retornada.

- **(DELETE /orders/order/{id}) Excluir um pedido:**
  Este endpoint é usado para excluir um pedido existente pelo seu ID. Se o ID for inválido ou se o pedido não for encontrado, uma resposta de erro será retornada.

- **(GET /orders) Obter todos os pedidos:**
  Este endpoint é usado para obter uma lista de todos os pedidos, excluindo os pedidos de status "FINALIZADO". Os pedidos são ordenados por status "Pronto > Em Preparação > Recebido" e pela ordem de criação. Se não houver pedidos, uma resposta de erro será retornada.

- **(GET /orders/order/{id}/status) Obter o status de um pedido:**
  Este endpoint é usado para obter o status de um pedido específico pelo seu ID. Se o pedido não for encontrado, uma resposta de erro será retornada.

---

## Checkout

### 4.1) (POST /checkout) - Realizar o checkout do carrinho de compras 

Este endpoint é usado para finalizar a compra do carrinho de compras. O corpo da solicitação deve conter as informações do carrinho de compras. Se ocorrer um erro ao realizar o checkout, uma resposta de erro será retornada.

---

## Order Payment Webhook

### 5.1) (POST /webhook/payment) - Receber webhook de pagamento 

Este endpoint é usado para receber webhooks de pagamento. Em caso de pagamento com sucesso, o status do pedido é atualizado para "RECEBIDO". Em caso de pagamento recusado, o status é atualizado para "PAGAMENTO_RECUSADO". O corpo da solicitação deve conter as informações do pagamento. Se o pedido não for encontrado, uma resposta de erro será retornada.


