package com.se.smsbackend.Repository;

import com.se.smsbackend.Entity.Assignment;
import com.se.smsbackend.Entity.EssayQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EssayQuestionRepo extends JpaRepository<EssayQuestion, Integer> {
public List<EssayQuestion> findByAssignmentEssayQ(Assignment assignment);
public EssayQuestion findById(int id);
}
