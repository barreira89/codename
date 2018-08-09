package integration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

@Configuration
class IntegrationBase extends Specification {

    @Value('${app.host:localhost}')
    String appHost

    @Value('${app.port:8080}')
    int appPort

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate()
    }
}
