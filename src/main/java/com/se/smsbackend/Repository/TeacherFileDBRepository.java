package com.se.smsbackend.Repository;

import com.se.smsbackend.Entity.TeacherFileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherFileDBRepository extends JpaRepository<TeacherFileDB, String> {

}