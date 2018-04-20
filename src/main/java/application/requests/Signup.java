package application.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

public class Signup extends Signin {
    private String email;
    private String name;

    @JsonCreator
    public Signup(@JsonProperty("login") @NotNull String login,
                  @JsonProperty("password") @NotNull String password,
                  @JsonProperty("email") @NotNull String email,
                  @JsonProperty("name") @NotNull String name) {
        super(login, password);
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
