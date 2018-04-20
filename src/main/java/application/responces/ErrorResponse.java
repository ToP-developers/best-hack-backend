package application.responces;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public class ErrorResponse {
    @NotNull
    private final ArrayList<String> messageList;

    public ErrorResponse(@NotNull ArrayList<String> messageList) {
        this.messageList = messageList;
    }

    @NotNull
    public ArrayList<String> getMessage() {
        return messageList;
    }
}
