package cl.dgac.seguros.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    // Lee la URL desde el application.properties
    @Value("${drones.base-url:http://dgac-ms-drones}")
    private String dronesBaseUrl;

    @Bean
    @LoadBalanced // <--- Fundamental para que Eureka resuelva los nombres
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient webClientDrones(WebClient.Builder builder) {
        // Construye el WebClient inyectándole la URL base directamente
        return builder.baseUrl(dronesBaseUrl).build();
    }
}