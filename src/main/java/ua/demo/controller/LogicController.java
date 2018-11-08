package ua.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.demo.service.LogicService;

@RestController
public class LogicController {
    @Autowired
    private LogicService logicService;

    @GetMapping("/handle")
    public void handle() {
        logicService.handle();
    }
}
