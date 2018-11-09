package ua.demo.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.demo.config.AppProperties;
import ua.demo.security.service.AuthTokenService;
import ua.demo.service.LogicService;

@Service
@Log4j2
public class LogicServiceImpl implements LogicService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppProperties appProperties;

    @Autowired
    private AuthTokenService authTokenService;

    @Override
    public void handle() {
        log.debug("Starting the communication with another service ...");
        String host = this.appProperties.getServiceServer();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.authTokenService.getAccessClientToken());

        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> result = this.restTemplate.exchange(host + "/ping", HttpMethod.GET, request, String.class);
        log.debug("Handling a response from server with status: {}", result.getStatusCode());
        log.debug("Result from server: {}", result.getBody());
    }
}
