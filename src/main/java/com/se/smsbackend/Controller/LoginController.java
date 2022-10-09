package com.se.smsbackend.Controller;
import com.se.smsbackend.Entity.Login;
import com.se.smsbackend.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/")
public class LoginController {
    @Autowired
    LoginService loginService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")

    public Login postMap(@RequestBody Login login) {
        loginService.saveLogin(login);
        return login;
    }
    @GetMapping("/login")
    public List<Login> loginList(){
        return loginService.listAllLogins();
    }
    @PutMapping("/login/{id}")
    public ResponseEntity<?> update(@RequestBody Login login, @PathVariable Integer id) {
        try {
            Login existLogin = loginService.getLogin(id);
            login.setLoginId(id);
            loginService.saveLogin(login);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/login/{id}")
    public void delete(@PathVariable Integer id) {
        loginService.deleteLogin(id);
    }
}