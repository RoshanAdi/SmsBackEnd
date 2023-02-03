package com.se.smsbackend.Repository;

import com.se.smsbackend.Entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepo extends JpaRepository<Report,Integer> {
    public  Report findBySubjectId(String subId);
}
