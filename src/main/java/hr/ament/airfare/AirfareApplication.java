package hr.ament.airfare;

import hr.ament.airfare.configuration.AirFareIntegrationConfig;
import hr.ament.airfare.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AirFareIntegrationConfig.class)
public class AirfareApplication {

    @Autowired
    private FlightService flightService;

    public static void main(String[] args) {
        SpringApplication.run(AirfareApplication.class, args);
    }

}
