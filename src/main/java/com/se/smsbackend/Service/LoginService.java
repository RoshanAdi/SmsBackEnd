package com.se.smsbackend.Service;
import com.se.smsbackend.Entity.Login;
import com.se.smsbackend.Repository.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class LoginService {
    @Autowired
    private LoginRepo loginRepo;
    public List<Login> listAllLogins() {
        return loginRepo.findAll();
    }

    public void saveLogin(Login login) {
        loginRepo.save(login);
    }

    public Login getLogin(Integer id) {
        return loginRepo.findById(id).get();
    }

    public void deleteLogin(Integer id) {
        loginRepo.deleteById(id);
    }
}
