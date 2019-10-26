package hr.ament.airfare.configuration;

import hr.ament.swagairfare.client.api.FlightOffersApi;
import hr.ament.swagairfare.client.invoker.ApiClient;
import hr.ament.swagairfare.client.invoker.auth.OAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AirFareIntegrationConfig {

    @Bean
    public FlightOffersApi flightOffersApi() {
        ApiClient apiClient = new ApiClient();

        OAuth airfareAuth = (OAuth) apiClient.getAuthentication("airfare_auth");
        airfareAuth.setAccessToken("AZk3L4WHFsiq4rc5jWbPlWDg9zAJ");

        FlightOffersApi flightOffersApi = new FlightOffersApi();
        flightOffersApi.setApiClient(apiClient);

        return flightOffersApi;
    }
}
