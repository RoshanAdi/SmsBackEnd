package com.se.smsbackend.Repository;

import com.se.smsbackend.Entity.Login;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface LoginRepo extends JpaRepository<Login, Integer> {

}
