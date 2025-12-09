# 📨 Projeto de Mensageria

**Instrutor:** Luan Rocha  

## 📘 Descrição
Este projeto é um exemplo prático de mensageria utilizando **Apache Kafka**.  
Ele simula um fluxo completo de **recepção, validação e pagamento de boletos**, onde a comunicação entre os serviços ocorre de forma **assíncrona** por meio de tópicos Kafka.

O objetivo é demonstrar:
- Como produzir e consumir mensagens;
- Como utilizar Avro + Schema Registry;
- Como orquestrar serviços com Docker;
- Como garantir desacoplamento entre microsserviços.

---

## 🚀 Tecnologias Utilizadas
- Java 17  
- Spring Boot  
- Apache Kafka  
- Apache Avro  
- Schema Registry  
- Confluent Control Center  
- Docker / Docker Compose  
- Banco de dados H2  

---

## 🏗 Arquitetura do Projeto

A solução é dividida em três aplicações principais:

1. **📥 Recepção de boletos (Producer)**  
   - Recebe boletos via API REST  
   - Converte o payload para Avro  
   - Publica no tópico **boletos-recebidos**

2. **🔍 Validação de boletos (Consumer → Producer)**  
   - Consome mensagens do tópico **boletos-recebidos**  
   - Realiza validações de negócio  
   - Publica no tópico **boletos-validados**

3. **💰 Pagamento de boletos (Consumer)**  
   - Consome mensagens do tópico **boletos-validados**  
   - Atualiza o status do boleto como **pago**  
   - Persiste os dados no banco H2

### 🔎 Diagrama da Arquitetura

![Arquitetura](arquitetura.gif)

---

## ▶️ Como rodar o projeto

### 1️⃣ Subir o ambiente Kafka com Docker
No diretório onde está o arquivo `docker-compose.yml`:

```bash
docker compose up -d
```
2️⃣ Verificar se os containers estão rodando
```bash
docker ps
```

# Você deve ver:

- Kafka

- Zookeeper

- Schema Registry

- Control Center

3️⃣ Rodar cada aplicação Spring Boot

Em cada módulo:
```bash
./mvnw spring-boot:run
```

Ou iniciar pela IDE (IntelliJ/Eclipse).

🔧 Endpoints Principais
📥 API de Recepção de Boletos (Producer)

POST /api/boletos

Body (JSON):
```bash

{
  "codigo": "12345",
  "valor": 250.00,
  "vencimento": "2025-01-10"
}
```
🗃 Tópicos Kafka utilizados

🧪 Testes e Observações

- Você pode testar o fluxo utilizando:

- Postman (envio de boletos)

- Confluent Control Center (visualizar mensagens)

- Logs das aplicações (processamento)

📄 Licença

- Projeto desenvolvido para fins educacionais.
