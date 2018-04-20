package application.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Command {
    private String url;
    private String[] description;
    private String command;
    private String[] params;

    @JsonCreator
    public Command(@JsonProperty("url") String url,
                   @JsonProperty("description") String[] description,
                   @JsonProperty("command") String command,
                   @JsonProperty("params") String[] params) {
        this.url = url;
        this.description = description;
        this.command = command;
        this.params = params;
    }

    public String getUrl() {
        return url;
    }

    public String[] getDescription() {
        return description;
    }

    public String getCommand() {
        return command;
    }

    public String[] getParams() {
        return params;
    }
}
