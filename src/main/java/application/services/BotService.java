package application.services;

import application.dao.command.CommandDAO;
import application.dao.user.UserDAO;
import application.entities.CommandEntity;
import application.entities.UserEntity;
import application.exceptions.WrongTokenExeption;
import application.requests.Command;
import application.requests.Message;
import application.responces.MessageResponce;
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
                if (message.contains(flag)){
                    return new ResponseEntity<>(new MessageResponce(command.getUrl()), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(new MessageResponce("not found"), HttpStatus.OK);
    }
}
