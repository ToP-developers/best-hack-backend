package application.entities;

import application.requests.Command;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class CommandEntity {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "params")
    private String[] params;

    @Column(name = "command", nullable = false)
    private String command;

    public CommandEntity() {
    }

    public CommandEntity(@NotNull Command command, @NotNull Long userId) {
        this.userId = userId;
        this.url = command.getUrl();
        this.description = command.getDescription();
        this.params = command.getParams();
        this.command = command.getCommand();
    }

}
