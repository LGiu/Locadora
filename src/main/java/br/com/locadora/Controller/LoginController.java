package br.com.locadora.Controller;

import br.com.locadora.Model.Login;
import br.com.locadora.Service.LoginService;
import br.com.locadora.Util.Token;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/logon", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Token> logon(@RequestBody Login login) {
        return new ResponseEntity<>(loginService.logon(login), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/logoff", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Token> logoff(@RequestBody Login login) {
        return new ResponseEntity<>(loginService.logoff(login), HttpStatus.OK);
    }
}