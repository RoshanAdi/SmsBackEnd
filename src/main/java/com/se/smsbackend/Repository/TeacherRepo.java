package com.se.smsbackend.Repository;

import com.se.smsbackend.Entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Integer> {
    public Teacher findByUsername(String username);
    @Query("SELECT u FROM Teacher u WHERE u.verificationCode = ?1")
    public Teacher findByVerificationCode(String code);
}
