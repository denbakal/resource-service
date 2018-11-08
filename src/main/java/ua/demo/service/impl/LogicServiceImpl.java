package ua.demo.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.demo.config.AppProperties;
import ua.demo.service.LogicService;

@Service
@Log4j2
public class LogicServiceImpl implements LogicService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppProperties appProperties;

    @Override
    public void handle() {
       log.debug("Starting the communication with another service ...");
       String host = this.appProperties.getServiceServer() + "/ping";
       ResponseEntity<String> result = this.restTemplate.getForEntity(host, String.class);
       log.debug("Handling a response from server with status: {}", result.getStatusCode());
       log.debug("Result from server: {}", result.getBody());
    }
}
