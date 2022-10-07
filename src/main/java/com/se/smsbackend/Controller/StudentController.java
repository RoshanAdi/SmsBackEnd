package com.se.smsbackend.Controller;

import com.se.smsbackend.Entity.Student;
import com.se.smsbackend.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/student")

    public Student postMap(@RequestBody Student student){
        System.out.println(student);
        studentService.saveStudent(student);

        return student;
    }
    @GetMapping("/")
    public Student GetStud(){
        return null;
    }

}
