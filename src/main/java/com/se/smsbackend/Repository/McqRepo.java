package com.se.smsbackend.Repository;

import com.se.smsbackend.Entity.McqQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface McqRepo extends JpaRepository<McqQuestion, Integer> {
    public McqQuestion findById(int id);
}
