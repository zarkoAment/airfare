package hr.ament.airfare.oauth2;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    private String type;
    private String username;
    private String application_name;
    @JsonProperty(value = "client_id")
    private String clientId;
    @JsonProperty(value = "token_type")
    private String tokenType;
    @JsonProperty(value = "access_token")
    private String accessToken;
    @JsonProperty(value = "expires_in")
    private int expiresIn;
    private String state;
    private String scope;
}
