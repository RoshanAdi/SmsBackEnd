package com.se.smsbackend.Service;

import com.se.smsbackend.Entity.Student;
import com.se.smsbackend.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;
    public List<Student> listAllStudents() {
        return studentRepo.findAll();
    }

    public void saveStudent(Student student) {
        studentRepo.save(student);
    }

    public Student getStudent(Integer id) {
        return studentRepo.findById(id).get();
    }

    public void deleteStudent(Integer id) {
        studentRepo.deleteById(id);
    }
}