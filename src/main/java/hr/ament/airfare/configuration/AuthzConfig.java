package hr.ament.airfare.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
@ConfigurationProperties(prefix = "oauth2")
@Data
public class AuthzConfig {
    private String clientId;
    private String secret;
}
