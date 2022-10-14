package com.se.smsbackend.Service;

import com.se.smsbackend.Dto.StudentDto;
import com.se.smsbackend.Entity.Student;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;
@Service
@Component
public interface StudentService2 {
    Student saveUser(StudentDto student, String x) throws MessagingException, UnsupportedEncodingException;
    List<Student> findAll();
    Student findOne(String username);
}
