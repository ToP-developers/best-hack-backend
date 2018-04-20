package application.responces;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class MessageResponce implements Serializable {
    @NotNull
    private String message;

    public MessageResponce(@NotNull String message) {
        this.message = message;
    }

    @NotNull
    public String getMessage() {
        return message;
    }
}
