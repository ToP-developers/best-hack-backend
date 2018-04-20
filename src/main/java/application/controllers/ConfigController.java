package application.controllers;

import application.requests.Command;
import application.services.ConfigService;
import application.utils.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/api/config")
@CrossOrigin
public class ConfigController {
    private final ConfigService configService;

    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    @PostMapping(path = "/create", consumes = Constants.JSON, produces = Constants.JSON)
    public ResponseEntity create(@RequestBody Command[] commands, HttpServletRequest request) {
        return configService.create(commands, request);
    }

    @GetMapping(path = "/get", consumes = Constants.JSON, produces = Constants.JSON)
    public ResponseEntity get(HttpServletRequest request) {
        return configService.get(request);
    }

}
