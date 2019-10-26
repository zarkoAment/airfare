package hr.ament.airfare;

import hr.ament.airfare.configuration.AirFareIntegrationConfig;
import hr.ament.airfare.configuration.AuthzConfig;
import hr.ament.airfare.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Import(AirFareIntegrationConfig.class)
@EnableScheduling
@EnableConfigurationProperties(AuthzConfig.class)

public class AirfareApplication {

    @Autowired
    private FlightService flightService;

    public static void main(String[] args) {
        SpringApplication.run(AirfareApplication.class, args);
    }

}
