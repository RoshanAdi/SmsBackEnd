package com.se.smsbackend.Repository;

import com.se.smsbackend.Entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepo extends JpaRepository<Assignment, Integer> {
    public Assignment findById(int id);
}
