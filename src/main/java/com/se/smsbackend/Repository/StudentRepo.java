package com.se.smsbackend.Repository;

import com.se.smsbackend.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface StudentRepo extends JpaRepository<Student, Integer> {

}
