package com.se.smsbackend.Repository;

import com.se.smsbackend.Dto.StudentDetailProjection;
import com.se.smsbackend.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDetailsRepo extends JpaRepository<Student , Integer> {
    StudentDetailProjection findByUsername(String username);


}
