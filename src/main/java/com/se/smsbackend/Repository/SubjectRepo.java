package com.se.smsbackend.Repository;

import com.se.smsbackend.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepo extends JpaRepository <Subject, Integer> {
    public Subject findBySubjectName(String SubjectName);
    public  Subject findById(int id);
}
