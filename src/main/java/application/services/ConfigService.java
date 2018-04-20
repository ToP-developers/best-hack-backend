package application.services;

import application.dao.command.CommandDAO;
import application.entities.CommandEntity;
import application.requests.Command;
import application.responces.CommandResponce;
import application.responces.MessageResponce;
import application.utils.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Transactional
public class ConfigService {
    private final CommandDAO commandDAO;
    private final UserService userService;

    public ConfigService(CommandDAO commandDAO, UserService userService) {
        this.commandDAO = commandDAO;
        this.userService = userService;
    }

    public ResponseEntity create(Command[] commands, HttpServletRequest request) {
        commandDAO.create(commands, userService.getByCookie(request).getId());
        return new ResponseEntity<>(new MessageResponce(Messages.SUCCESS), HttpStatus.OK);
    }

    public ResponseEntity get(HttpServletRequest request) {
        List<CommandEntity> entityList = commandDAO.get(userService.getByCookie(request).getId());
        return new ResponseEntity<>(new CommandResponce(entityList), HttpStatus.OK);
    }


}
