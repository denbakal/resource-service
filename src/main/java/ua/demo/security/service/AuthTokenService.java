package ua.demo.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.demo.config.AppProperties;
import ua.demo.security.pojo.ResponseAuthData;

import java.util.Base64;

@Component
@Log4j2
public class AuthTokenService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppProperties appProperties;

    private String accessToken;

    public String getAccessClientToken() {
        if (this.accessToken == null && verifyToken()) {
            log.debug("[AUTH]: Obtaining a token from auth server ...");
            String host = this.appProperties.getAuthServer();

            String basicAuthData = this.appProperties.getServiceUsername() + ":" + this.appProperties.getServicePassword();
            String basicAuthHeader = "Basic " + Base64.getEncoder().encodeToString(basicAuthData.getBytes());

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", basicAuthHeader);

            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<ResponseAuthData> response = this.restTemplate
                    .exchange(host + "/oauth/token?grant_type=client_credentials", HttpMethod.POST, request, ResponseAuthData.class);

            ResponseAuthData authData = response.getBody();
            this.accessToken = authData == null ? "" : authData.getAccess_token();

            log.debug("[AUTH]: Access token: {}", this.accessToken);
        }

        return accessToken;
    }

    private boolean verifyToken() {
        return true;
    }
}
