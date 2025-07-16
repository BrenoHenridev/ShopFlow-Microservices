Visão Geral
Plataforma de e-commerce escalável baseada em microsserviços com:
✔ Catálogo de produtos
✔ Processamento de pedidos
✔ API Gateway unificado
✔ Service Discovery automático

Como Executar
Pré-requisitos:
Java 17+
Maven 3.8+
Docker 20.10+

Clone o repositório:
git clone https://github.com/seu-usuario/shopflow-microservices.git
cd shopflow-microservices

Construa os projetos:
mvn clean package -DskipTests

Inicie com Docker:
docker-compose up -d

Serviços:

Serviço/ Porta/ URL de Acesso
Service Discovery - 8761 - http://localhost:8761
API Gateway - 8700 - http://localhost:8700
Produto Service - 8100 -  http://localhost:8100/api
Pedido Service - 8200 - http://localhost:8200/api

Endpoints Principais

Produto Service
GET /api/produtos  
POST /api/produtos  
GET /api/produtos/{id}  

Pedido Service:

POST /api/pedidos  
GET /api/pedidos/{id}  

Configuração:
Variáveis de Ambiente
DB_URL=jdbc:postgresql://db-produto:5432/produto_db  
DB_USER=admin  
DB_PASSWORD=secret  

Estrutura do Projeto:

shopflow-microservices/  
├── api-gateway  
├── service-discovery  
├── produto-service  
├── pedido-service  
├── docker-compose.yml  
└── README.md  
