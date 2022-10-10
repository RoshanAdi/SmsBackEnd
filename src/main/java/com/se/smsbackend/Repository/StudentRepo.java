package com.se.smsbackend.Repository;

import com.se.smsbackend.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
@Component
public interface StudentRepo extends JpaRepository<Student, Integer> {
    public List<Student> findByNameContaining(String name);
    @Query("SELECT u FROM Student u WHERE u.verificationCode = ?1")
    public Student findByVerificationCode(String code);

}
