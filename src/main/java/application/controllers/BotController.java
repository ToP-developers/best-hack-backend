package application.controllers;

import application.requests.Message;
import application.services.BotService;
import application.utils.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bot")
@CrossOrigin
public class BotController {
    private final BotService botService;

    public BotController(BotService botService) {
        this.botService = botService;
    }

    @PostMapping(path = "/message", consumes = Constants.JSON, produces = Constants.JSON)
    public ResponseEntity message(@RequestBody Message body){
        return botService.message(body);
    }
}
