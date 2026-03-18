# 🎫 Portal Ingresso

O **Portal Ingresso** é um microsserviço desenvolvido para gerenciar e expor o catálogo de eventos (shows, teatros, conferências). Ele serve como a fonte para as informações que alimentam o ecossistema de venda de ingressos.

---

## 🚀 Tecnologias e Ferramentas

* **Linguagem:** Java 21
* **Framework:** Spring Boot 4.x
* **Persistência:** Spring Data JPA / Hibernate
* **Banco de Dados:** PostgreSQL (via Amazon RDS)
* **Mensageria:** Apache Kafka (Confluent Cloud)
* **Containerização:** Docker
* **Orquestração:** Kubernetes (Minikube / AWS EKS)

---

## 🏗️ Arquitetura e Padrões Aplicados

* **Database per Service:** Isolamento total dos dados para garantir independência de deploy.
* **Event-Driven Communication:** Comunicação assíncrona com o `ticket-service` via Kafka para garantir consistência eventual.
* **Observabilidade:** Health Checks configurados via **Spring Actuator** com Liveness e Readiness probes para o Kubernetes.
* **Configuração Externa:** Uso de **ConfigMaps** e **Secrets** do Kubernetes para gestão de variáveis de ambiente e credenciais sensíveis.
