package application.utils;

import application.exceptions.ValidationExeption;
import application.requests.Signup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final int LOGIN_MIN_LENGTH = 3;
    private static final int LOGIN_MAX_LENGTH = 15;
    private static final int PASSWORD_MIN_LENGTH = 8;
    private static final int PASSWORD_MAX_LENGTH = 24;
    private static final Pattern LOGIN_PATTERN = Pattern.compile("^[A-Za-z0-9_-]+$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$");

    private static boolean checkEmail(@Nullable String email) {
        if (email == null || email.isEmpty()) {
            return false;
        } else {
            final Matcher m = EMAIL_PATTERN.matcher(email);
            return m.matches();
        }
    }

    private static boolean checkPassword(@Nullable String password) {
        return !(password == null
                || password.isEmpty()
                || password.length() > PASSWORD_MAX_LENGTH
                || password.length() < PASSWORD_MIN_LENGTH);
    }

    private static boolean checkLogin(@Nullable String login) {
        if (login == null || login.isEmpty() || login.length() > LOGIN_MAX_LENGTH || login.length() < LOGIN_MIN_LENGTH) {
            return false;
        } else {
            final Matcher m = LOGIN_PATTERN.matcher(login);
            return m.matches();
        }
    }

    public static void checkAdvSignup(@NotNull Signup signup) {
        final Map<String, Boolean> validation = new HashMap<>();
        validation.put("login", checkLogin(signup.getLogin()));
        validation.put("email", checkEmail(signup.getEmail()));
        validation.put("password", checkPassword(signup.getPassword()));
        validation.put("name", signup.getName() != null);
        if (validation.containsValue(false)) {
            throw new ValidationExeption(validation);
        }
    }

    public static Boolean isEmail(@NotNull String loginOrEmail) {
        final Matcher m = EMAIL_PATTERN.matcher(loginOrEmail);
        return m.matches();
    }

}
