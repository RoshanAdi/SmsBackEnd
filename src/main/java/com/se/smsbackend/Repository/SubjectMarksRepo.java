package com.se.smsbackend.Repository;

import com.se.smsbackend.Entity.SubjectMarks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectMarksRepo extends JpaRepository<SubjectMarks, Integer> {
    public SubjectMarks findByMarksUpdateId(String str);
    public List<SubjectMarks> findBySubjectId(String id);

}
