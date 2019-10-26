package hr.ament.airfare.configuration;

import hr.ament.swagairfare.client.api.FlightOffersApi;
import hr.ament.swagairfare.client.invoker.ApiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AirFareIntegrationConfig {

    @Bean
    public FlightOffersApi flightOffersApi() {
        return new FlightOffersApi();
    }

    @Bean
    public ApiClient apiClient() {
        return new ApiClient();
    }

}
