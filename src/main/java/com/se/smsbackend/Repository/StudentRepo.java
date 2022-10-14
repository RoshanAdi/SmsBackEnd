package com.se.smsbackend.Repository;

import com.se.smsbackend.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface StudentRepo extends JpaRepository<Student, Integer> {
    //public List<Student> findByFullNameContaining(String name);
    @Query("SELECT u FROM Student u WHERE u.verificationCode = ?1")
    public Student findByVerificationCode(String code);
    public Student findByUsername(String username);


}



