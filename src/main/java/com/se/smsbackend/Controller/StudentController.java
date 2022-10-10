package com.se.smsbackend.Controller;
import com.se.smsbackend.Entity.Student;
import com.se.smsbackend.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.NoSuchElementException;

import static com.se.smsbackend.Site.Utility.getSiteURL;

@RestController
@RequestMapping("/")
public class StudentController {
    @Autowired
    StudentService studentService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/student")
    public String postMap(@RequestBody Student student, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        System.out.println("Post mapping working  = "+student);
        studentService.saveStudent(student, getSiteURL(request));

        return "register_success";
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
    public ResponseEntity<?> update(@RequestBody Student student, @PathVariable Integer id , HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        try {
            Student existStudent = studentService.getStudent(id);
            student.setStudentId(id);
            studentService.saveStudent(student,getSiteURL(request));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/student/{id}")
    public void delete(@PathVariable Integer id) {
        studentService.deleteStudent(id);
    }


    @GetMapping("/verify")
    public ModelAndView verifyUser(@Param("code") String code) {
        System.out.println("clicked the verification link");
        if (studentService.verify(code)) {
            return new ModelAndView("redirect:" + "http://localhost:4200/regSuccess");
        } else {
            return new ModelAndView("redirect:" + "http://localhost:4200/regFail");
        }
    }

}
