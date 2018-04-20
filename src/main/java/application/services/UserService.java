package application.services;

import application.dao.UserDAO;
import application.entities.UserEntity;
import application.exceptions.WrongAuthExeption;
import application.requests.Signin;
import application.requests.Signup;
import application.responces.ErrorResponse;
import application.responces.MessageResponce;
import application.utils.Constants;
import application.utils.CookieHelper;
import application.utils.Messages;
import application.utils.Validator;
import application.exceptions.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Service
@Transactional
public class UserService {
    @NotNull
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    @NotNull
    private final UserDAO userDAO;
    @NotNull
    private final ShaPasswordEncoder encoder;

    public UserService(@NotNull UserDAO userDAO, @NotNull ShaPasswordEncoder encoder) {
        this.userDAO = userDAO;
        this.encoder = encoder;
    }

    public ResponseEntity signup(Signup signup, HttpServletRequest request, HttpServletResponse response) {
        checkAuth(request);
        Validator.checkAdvSignup(signup);
        ArrayList<String> errors = conflictMessage(signup);
        if (errors.isEmpty()) {
            final UserEntity entity = addUser(signup);
            CookieHelper.addCookie(response, entity.getId().toString());
            LOGGER.info("200");
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } else {
            LOGGER.info("409");
            return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.CONFLICT);
        }
    }

    private UserEntity addUser(Signup signup) {
        signup.setPassword(encoder.encodePassword(signup.getPassword(), Constants.SALT));
        return userDAO.addUser(signup);
    }

    public ResponseEntity signin(Signin signup, HttpServletRequest request, HttpServletResponse response) {
        checkAuth(request);
        UserEntity entity = getByLoginOrEmail(signup);
        CookieHelper.addCookie(response, entity.getId().toString());
        LOGGER.info("200");
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) {
        checkUnauth(request);
        CookieHelper.deleteCookie(response, Constants.COOKIE_NAME);
        return new ResponseEntity<>(new MessageResponce(Messages.SUCCESS), HttpStatus.OK);
    }

    @SuppressWarnings("all")
    public ResponseEntity user(HttpServletRequest request) {
        try {
            Long id = Long.parseLong(CookieHelper.getCookieValue(request, Constants.COOKIE_NAME));
            UserEntity entity = userDAO.getUserById(id);
            if (entity == null) {
                throw new BadCookieException();
            }
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } catch (NumberFormatException ex) {
            throw new BadCookieException();
        }
    }

    private UserEntity getByLoginOrEmail(Signin signin) {
        final String loginOrEmail = signin.getLogin();
        final UserEntity user;
        if (Validator.isEmail(loginOrEmail)) {
            user = userDAO.getUserByEmail(loginOrEmail);
        } else {
            user = userDAO.getUserByLogin(loginOrEmail);
        }
        if (user != null && encoder.isPasswordValid(user.getPassword(), signin.getPassword(), Constants.SALT)) {
            return user;
        } else {
            throw new WrongAuthExeption();
        }
    }

    private void checkAuth(HttpServletRequest request) {
        if (CookieHelper.getCookieValue(request, Constants.COOKIE_NAME) != null) {
            throw new AuthorizedExeption();
        }
    }

    @NotNull
    private ArrayList<String> conflictMessage(@NotNull Signup signup) {
        final ArrayList<String> messages = new ArrayList<>();
        if (!checkEmail(signup.getEmail())) {
            messages.add("Email already exist");
        }
        if (!checkLogin(signup.getLogin())) {
            messages.add("Login already exist");
        }
        return messages;
    }

    private boolean checkLogin(@NotNull String login) {
        return userDAO.getUserByLogin(login) == null;
    }

    private boolean checkEmail(@NotNull String email) {
        return userDAO.getUserByEmail(email) == null;
    }

    private void checkUnauth(HttpServletRequest request) {
        if (CookieHelper.getCookieValue(request, Constants.COOKIE_NAME) == null) {
            throw new UnauthorizedExeption();
        }
    }
}
