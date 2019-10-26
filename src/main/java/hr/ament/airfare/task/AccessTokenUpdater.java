package hr.ament.airfare.task;

import hr.ament.airfare.configuration.AuthzConfig;
import hr.ament.airfare.oauth2.Token;
import hr.ament.swagairfare.client.api.FlightOffersApi;
import hr.ament.swagairfare.client.invoker.ApiClient;
import hr.ament.swagairfare.client.invoker.auth.OAuth;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

@Component
@Log4j2
public class AccessTokenUpdater {
    private final ApiClient apiClient;

    private final FlightOffersApi flightOffersApi;

    private final AuthzConfig authzConfig;

    private static final Form form;

    static {
        form = new Form();
        form.param("grant_type", "client_credentials");
    }

    public AccessTokenUpdater(ApiClient apiClient, FlightOffersApi flightOffersApi, AuthzConfig authzConfig) {
        this.apiClient = apiClient;
        this.flightOffersApi = flightOffersApi;
        this.authzConfig = authzConfig;
    }

    @Scheduled(fixedRate = 1700000)
    public void updateToken() {
        Client client = ClientBuilder.newClient();

        WebTarget webTarget = client.target("https://test.api.amadeus.com/v1/security/oauth2/token");

        form.param("client_id", authzConfig.getClientId());
        form.param("client_secret", authzConfig.getSecret());

        Token token = webTarget
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.form(form), Token.class);

        OAuth airfareAuth = (OAuth) apiClient.getAuthentication("airfare_auth");
        airfareAuth.setAccessToken(token.getAccessToken());

        log.info("Set access token!");

        flightOffersApi.setApiClient(apiClient);

    }

}
