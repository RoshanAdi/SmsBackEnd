package com.se.smsbackend.Repository;

import com.se.smsbackend.Entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface AssignmentRepo extends JpaRepository<Assignment, Integer> {
    public  Assignment findById(int id);
    public Assignment findByAssigmentID(int id);
}
