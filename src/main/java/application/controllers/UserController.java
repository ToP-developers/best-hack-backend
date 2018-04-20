package application.controllers;

import application.requests.Signin;
import application.requests.Signup;
import application.services.UserService;
import application.utils.Constants;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/api")
public class UserController {
    @NotNull
    private final UserService userService;
    @NotNull
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public UserController(@NotNull UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/signup", consumes = Constants.JSON, produces = Constants.JSON)
    public ResponseEntity signup(@RequestBody Signup body, HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("/api/advertiser/signup");
        return userService.signup(body, request, response);
    }

    @PostMapping(path = "/signin", consumes = Constants.JSON, produces = Constants.JSON)
    public ResponseEntity signin(@RequestBody Signin body, HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("/api/advertiser/signup");
        return userService.signin(body, request, response);
    }

    @GetMapping(path = "/logout", consumes = Constants.JSON, produces = Constants.JSON)
    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("/api/advertiser/signup");
        return userService.logout(request, response);
    }

}
