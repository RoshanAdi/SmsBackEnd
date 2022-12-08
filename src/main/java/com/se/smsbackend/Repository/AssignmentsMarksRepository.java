package com.se.smsbackend.Repository;

import com.se.smsbackend.Entity.AssignmentMarks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentsMarksRepository extends JpaRepository<AssignmentMarks, String> {

    public AssignmentMarks findByMarksupdateId(String id);
}
