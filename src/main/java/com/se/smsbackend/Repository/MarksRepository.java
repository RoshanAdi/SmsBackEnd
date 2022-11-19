package com.se.smsbackend.Repository;

import com.se.smsbackend.Entity.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarksRepository extends JpaRepository<Marks, String> {

    public Marks findByMarksupdateId(String id);
}
