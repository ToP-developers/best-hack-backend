package application.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieHelper {

    @Nullable
    public static String getCookieValue(@NotNull HttpServletRequest request,
                                        @NotNull String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void addCookie(@NotNull HttpServletResponse response,
                                 @NotNull String cookieValue) {
        Cookie cookie = new Cookie(Constants.COOKIE_NAME, cookieValue);
        cookie.setMaxAge(Constants.COOKIE_MAX_AGE);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static void deleteCookie(@NotNull HttpServletResponse response,
                                    @NotNull String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
