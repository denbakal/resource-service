package ua.demo.scheduler;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ua.demo.service.LogicService;

@Component
@Log4j2
public class LogicScheduler {
    @Autowired
    private LogicService logicService;

    @Scheduled(cron = "0 * * * * *")
    public void sendData() {
        log.debug("Sending data by schedule ...");
        this.logicService.handle();
    }
}
