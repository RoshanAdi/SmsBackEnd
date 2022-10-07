package com.se.smsbackend.Controller;

import com.se.smsbackend.Entity.Student;
import com.se.smsbackend.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
    @GetMapping("/student")
    public List<Student> studList(){
        return studentService.listAllStudents();
    }

    @GetMapping("/student/{name}")
    public List<Student> get(@PathVariable String name) {
        try {
            List<Student> student= studentService.getByNameContaining(name);
            System.out.println(name);
            System.out.println(student);
            return student;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<?> update(@RequestBody Student student, @PathVariable Integer id) {
        try {
            Student existStudent = studentService.getStudent(id);
            student.setStudentId(id);
            studentService.saveStudent(student);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/student/{id}")                //how to limit curd opp to respective user??
    public void delete(@PathVariable Integer id) {
        studentService.deleteStudent(id);
    }
}
