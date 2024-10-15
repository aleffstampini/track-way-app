# **TrackWayApp - Serviço de Rastreio de Produtos e Cálculo de Frete**

[![Java](https://img.shields.io/badge/Java-17%2B-blue.svg)](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Kafka](https://img.shields.io/badge/Kafka-2.8.0-yellow.svg)](https://kafka.apache.org/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-13.0-blue.svg)](https://www.postgresql.org/)
[![Lombok](https://img.shields.io/badge/Lombok-1.18.22-orange.svg)](https://projectlombok.org/)
[![Docker](https://img.shields.io/badge/Docker-20.10.8-blue.svg)](https://www.docker.com/)
[![WireMock](https://img.shields.io/badge/WireMock-2.31.0-blue.svg)](http://wiremock.org/)
[![Swagger](https://img.shields.io/badge/Swagger-3.0.0-brightgreen.svg)](https://swagger.io/)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](LICENSE)

---

### **Visão Geral do Projeto**

O **TrackWayApp** é uma aplicação desenvolvida para gerenciar produtos, rastrear seus detalhes de envio e calcular os custos de frete. O sistema é baseado em uma arquitetura orientada a eventos, que garante um fluxo desacoplado, escalável e eficiente. Ele utiliza Kafka para mensageria assíncrona e publica eventos para acionar funcionalidades como o cálculo de frete. Além disso, o projeto é estruturado de acordo com os princípios **SOLID**, promovendo um código mais limpo e manutenível.

---

### **Principais Funcionalidades**
- **Criação de Produto**: Criação de produtos via Kafka e armazenamento no banco de dados.
- **Histórico de Produto**: Armazenamento de histórico de movimentações e atualizações do produto.
- **Consulta de Código Postal**: Integração com serviços externos para consulta de detalhes de códigos postais.
- **Cálculo de Frete**: Cálculo do valor do frete e data estimada de entrega baseada no peso do produto e endereço de destino.
- **Arquitetura Orientada a Eventos**: Uso de eventos para acionar cálculos de frete após a criação de produtos.
- **Contêiner Docker**: Facilita a execução da aplicação em ambientes isolados, garantindo portabilidade e consistência.
- **WireMock**: Utilizado para simular serviços externos e facilitar testes sem depender de APIs reais.
- **Swagger**: Implementado para documentação e visualização da API, permitindo fácil acesso aos endpoints disponíveis.

---

### **Arquitetura**
A arquitetura do **TrackWayApp** segue os princípios de **microsserviços** e **event-driven design**. O sistema é desenvolvido com as seguintes tecnologias:

- **Spring Boot**: Framework principal para desenvolvimento de APIs REST.
- **Apache Kafka**: Mensageria assíncrona para comunicação entre serviços.
- **PostgreSQL**: Banco de dados relacional utilizado para armazenar os produtos, históricos e cálculos.
- **Lombok**: Facilita o desenvolvimento ao reduzir a verbosidade do código.
- **Docker**: Utilizado para empacotar a aplicação e suas dependências, permitindo execução em qualquer lugar.
- **WireMock**: Ferramenta de simulação de APIs que permite realizar testes mais eficientes sem depender de serviços externos.
- **Swagger**: Ferramenta para documentação da API que gera uma interface gráfica para interação com os endpoints.

---
### **Desenho de Solução**
![Descrição da imagem](https://i.ibb.co/YTy0dCG/Desenho-de-Solu-o.jpg)
