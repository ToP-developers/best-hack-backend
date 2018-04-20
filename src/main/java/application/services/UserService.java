package application.services;

import application.requests.Signin;
import application.requests.Signup;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Transactional
public class UserService {

    public ResponseEntity signup(Signup signup, HttpServletRequest request, HttpServletResponse response){
        return null;
    }

    public ResponseEntity signin(Signin signup, HttpServletRequest request, HttpServletResponse response){
        return null;
    }

    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
