package com.se.smsbackend.Repository;

import com.se.smsbackend.Entity.EssayAnswers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EssayAsnwersRepo extends JpaRepository<EssayAnswers,Integer> {
    public List<EssayAnswers> findByAssigmentID(String assignmentID);

    public List<EssayAnswers> findByUpdateId(String id);
}
