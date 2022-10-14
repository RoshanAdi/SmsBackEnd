
package com.se.smsbackend.Controller;

import com.se.smsbackend.Dto.StudentDto;
import com.se.smsbackend.Entity.Student;
import com.se.smsbackend.Security.AuthToken;
import com.se.smsbackend.Security.LoginUser;
import com.se.smsbackend.Security.TokenProvider;
import com.se.smsbackend.Service.StudentService;
import com.se.smsbackend.Service.StudentService2;
import com.se.smsbackend.Service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static com.se.smsbackend.Site.Utility.getSiteURL;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    StudentServiceImpl studentServiceImpl;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;
    @Autowired
    StudentService2 studentService2;




    @PostMapping(value="/student/register" )
    public void saveUser( @RequestBody StudentDto student, HttpServletRequest request)throws MessagingException, UnsupportedEncodingException{
        //System.out.println(student2);
        //studentService.saveStudent(student2, getSiteURL(request));
        //studentService2.saveStudent(student, getSiteURL(request));
        //studentService.saveStudent(student, getSiteURL(request));
        studentService2.saveUser(student ,getSiteURL(request));

    }

/*    @PostMapping("/student/register")
    public ModelAndView postMap(@RequestBody Student student, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        System.out.println("Post mapping working  = "+student);
        studentService.saveStudent(student, getSiteURL(request));
        System.out.println("Redirecting" );

        return new ModelAndView("redirect:" + "http://localhost:4200/verify");
    }*/


    @GetMapping("/student")
    public List<Student> studList(){
        return studentService.listAllStudents();
    }

/*    @GetMapping("/student/{name}")
    public List<Student> get(@PathVariable String name) {
        try {
            List<Student> student= studentService.getByNameContaining(name);
            System.out.println(name);
            System.out.println(student);
            return student;
        } catch (NoSuchElementException e) {
            return null;
        }
    }*/

/*    @PutMapping("/student/{id}")
    public ResponseEntity<?> update(@RequestBody StudentDto student, @PathVariable Integer id , HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        try {
            Student existStudent = studentService.getStudent(id);
            student.setStudentId(id);
            studentService.saveStudent(student,getSiteURL(request));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

    @DeleteMapping("/student/{id}")
    public void delete(@PathVariable Integer id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/verify")

    public ModelAndView verifyUser(@Param("code") String code) {
        System.out.println("clicked the verification link the code is "+code);
        if (studentServiceImpl.verify(code)) {
            return new ModelAndView("redirect:" + "http://localhost:4200/regSuccess");

        } else {
            return new ModelAndView("redirect:" + "http://localhost:4200/regFail");
        }
    }


    @PostMapping(value = "/login")
    public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {
        System.out.println("user details got "+loginUser.getUsername()+"  pass "+loginUser.getPassword());
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        System.out.println("sending token to client ");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        System.out.println("sending token to client "+token);
        return ResponseEntity.ok(new AuthToken(token));
    }



/*
    @PreAuthorize("hasRole('Student')")
    @GetMapping(value="/student")
    public String adminPing(){
        return "Only Admins Can Read This";
    }
*/





}

