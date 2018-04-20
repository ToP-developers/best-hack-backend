package application.dao.command;

import application.entities.CommandEntity;
import application.requests.Command;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface CommandDAO {
    void create(@NotNull Command[] commands, @NotNull Long id);

    List<CommandEntity> get(Long id);
}
