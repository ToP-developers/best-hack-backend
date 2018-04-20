package application.services;

import application.requests.Command;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class ConfigService {

    public ResponseEntity create(Command[] commands, HttpServletRequest request) {
        return null;
    }
}
