package com.se.smsbackend.Repository;

import com.se.smsbackend.Entity.StudentFileDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentFileDBRepository extends JpaRepository<StudentFileDB, String> {
}
