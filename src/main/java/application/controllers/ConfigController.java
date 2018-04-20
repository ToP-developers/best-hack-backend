package application.controllers;

import application.requests.Command;
import application.services.ConfigService;
import application.utils.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/api/config")
public class ConfigController {
    private final ConfigService configService;

    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    @PostMapping(path = "/signin", consumes = Constants.JSON, produces = Constants.JSON)
    public ResponseEntity create(@RequestBody Command[] commands, HttpServletRequest request) {
        return configService.create(commands, request);
    }

}
