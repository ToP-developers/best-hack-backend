package application.services;

import application.dao.command.CommandDAO;
import application.dao.user.UserDAO;
import application.entities.CommandEntity;
import application.entities.UserEntity;
import application.exceptions.WrongTokenExeption;
import application.requests.Message;
import application.responces.MessageResponce;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BotService {
    private final UserDAO userDao;
    private final CommandDAO commandDAO;

    public BotService(UserDAO userDao, CommandDAO commandDAO) {
        this.userDao = userDao;
        this.commandDAO = commandDAO;
    }

    public ResponseEntity message(Message body) {
        UserEntity entity = userDao.getUserByToken(body.getToken());
        if (entity == null) {
            throw new WrongTokenExeption();
        }
        List<CommandEntity> commands = commandDAO.get(entity.getId());
        final String message = body.getMessage();
        for (CommandEntity command : commands) {
            for (String flag : command.getDescription()) {
                if (flag.contains("%")) {
                    if (message.contains(flag.substring(0, flag.indexOf('%')))) {
                        return new ResponseEntity<>(
                                new MessageResponce(
                                        command.getUrl() + '?'
                                                + getParam(flag) + '='
                                                + getValue(message, flag.substring(0, flag.indexOf('%')))
                                ), HttpStatus.OK);
                    }
                } else if (message.contains(flag)) {
                    return new ResponseEntity<>(new MessageResponce(command.getUrl()), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(new MessageResponce("not found"), HttpStatus.OK);
    }

    private String getParam(@NotNull String flag) {
        String tmp = flag.substring(flag.indexOf('%') + 1, flag.length());
        return tmp.substring(0, tmp.indexOf('%'));
    }

    private String getValue(@NotNull String message, @NotNull String flag) {
        String tmp = message.substring(message.indexOf(flag) + flag.length());
        if (tmp.indexOf(' ') > 0) {
            return tmp.substring(0, tmp.indexOf(" "));
        }
        return tmp;
    }
}
