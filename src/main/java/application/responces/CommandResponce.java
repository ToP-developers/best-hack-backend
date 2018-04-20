package application.responces;

import application.entities.CommandEntity;

import javax.validation.constraints.NotNull;
import java.util.List;

public class CommandResponce {
    @NotNull
    private final List<CommandEntity> responce;

    public CommandResponce(@NotNull List<CommandEntity> messageList) {
        this.responce = messageList;
    }

    @NotNull
    public List<CommandEntity> getMessage() {
        return responce;
    }
}
