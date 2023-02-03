package com.se.smsbackend.Service;

import com.se.smsbackend.Entity.Assignment;
import com.se.smsbackend.Entity.Student;
import com.se.smsbackend.Entity.StudentFileDB;
import com.se.smsbackend.Repository.AssignmentRepo;
import com.se.smsbackend.Repository.StudentFileDBRepository;
import com.se.smsbackend.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.stream.Stream;

@Service
@Transactional
public class StudentFileDBService {

    @Autowired
    AssignmentRepo assignmentRepo;
    @Autowired
    StudentFileDBRepository studentFileDBRepository;
    @Autowired
    StudentRepo studentRepo;

    public StudentFileDB storeFile(MultipartFile file, int id, String username) throws IOException {
        String fileName = file.getOriginalFilename();
        StudentFileDB studentFileDB = new StudentFileDB()/*(fileName, file.getContentType(), file.getBytes())*/;
        studentFileDB.setName(fileName);
        studentFileDB.setType(file.getContentType());
        studentFileDB.setData(file.getBytes());
        studentFileDB.setUsername(username);
        Assignment assignment = assignmentRepo.findById(id);
        Student student = studentRepo.findByUsername(username);
        System.out.println(id+"printing assignement for file save"+assignment);
        studentFileDB.setAssignment(assignment);
        studentFileDB.setStudent(student);
        return studentFileDBRepository.save(studentFileDB);
    }
    public StudentFileDB getFile(String id) {
        return studentFileDBRepository.findById(id).get();
    }

    public Stream<StudentFileDB> getAllFiles() {
        return studentFileDBRepository.findAll().stream();
    }
}
