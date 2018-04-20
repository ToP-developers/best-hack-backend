package application.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

public class Signin {
    @NotNull
    private final String login;
    @NotNull
    private String password;

    @JsonCreator
    public Signin(@JsonProperty("login") @NotNull String login,
                  @JsonProperty("password") @NotNull String password) {
        this.login = login;
        this.password = password;
    }

    @NotNull
    public String getLogin() {
        return login;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }
}
