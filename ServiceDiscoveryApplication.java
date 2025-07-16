/* ======================== */
/*   SERVICE DISCOVERY      */
/* ======================== */
package com.shopflow.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Servidor Eureka - Porta 8761
 * Registra todos os microsserviços do sistema
 */
@SpringBootApplication
@EnableEurekaServer
public class ServiceDiscoveryApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceDiscoveryApplication.class, args);
    }
}

/* ======================== */
/*      API GATEWAY         */
/* ======================== */
package com.shopflow.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

/**
 * Porta 8700 - Roteamento centralizado
 * Funcionalidades:
 * - Balanceamento de carga
 * - Circuit Breaker
 * - Autenticação
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}

/* ======================== */
/*   FILTRO DE AUTENTICAÇÃO */
/* ======================== */
package com.shopflow.gateway.filters;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * Validação simples de token JWT
 * (Substituir por OAuth2 em produção)
 */
@Component
public class AuthenticationFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (!"Bearer valid-token".equals(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }
}

/* ======================== */
/*   MICROSSERVIÇO PRODUTOS */
/* ======================== */
package com.shopflow.produto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Porta 8100 - Catálogo de produtos
 * Banco de dados: MySQL/PostgreSQL
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories
@EntityScan
public class ProdutoServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProdutoServiceApplication.class, args);
    }
}

/* ======================== */
/*   MICROSSERVIÇO PEDIDOS  */
/* ======================== */
package com.shopflow.pedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Porta 8200 - Processamento de pedidos
 * Comunica com microsserviço de produtos
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PedidoServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PedidoServiceApplication.class, args);
    }
}
