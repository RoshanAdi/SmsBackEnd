
package com.se.smsbackend.Controller;

import com.se.smsbackend.Dto.StudentDetailProjection;
import com.se.smsbackend.Dto.StudentDto;
import com.se.smsbackend.Entity.Student;
import com.se.smsbackend.Repository.StudentDetailsRepo;
import com.se.smsbackend.Repository.StudentRepo;
import com.se.smsbackend.Security.AuthToken;
import com.se.smsbackend.Security.LoginUser;
import com.se.smsbackend.Security.TokenProvider;
import com.se.smsbackend.Service.StudentService;
import com.se.smsbackend.Service.StudentService2;
import com.se.smsbackend.Service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.persistence.NonUniqueResultException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.NoSuchElementException;

import static com.se.smsbackend.Site.Utility.getSiteURL;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    StudentDetailsRepo studentDetailsRepo;
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    StudentServiceImpl studentServiceImpl;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;
    @Autowired
    StudentService2 studentService2;




    @PostMapping(value="/student/register" )
    public String saveUser( @RequestBody StudentDto student, HttpServletRequest request)throws MessagingException, UnsupportedEncodingException, NonUniqueResultException {
        if (studentRepo.findByUsername(student.getUsername())!=null) {   //neglected unverified accounts
            return "This Email already registered";
        } else {
            studentService2.saveUser(student, getSiteURL(request));
            return "Successfully submitted. Please check your Emails ";
        }

    }


    @PreAuthorize("hasRole('Student')")
    @GetMapping("/student")
    public List<Student> studList(){
        return studentService.listAllStudents();
    }
    @PreAuthorize("hasRole('Student')")
    @GetMapping("/student/{name}")
    public StudentDetailProjection get(@PathVariable String name) {
        try {
            return studentDetailsRepo.findByUsername(name);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

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




   @PreAuthorize("hasRole('Student')")
    @GetMapping(value="/students")
    public String adminPing(){
        return "Only Admins Can Read This";
    }

   /* @PreAuthorize("hasRole('Student')")
    @GetMapping("/student")
    public void getAllheaders(@RequestHeader Map<String,String> headers){
        System.out.println("going to print headers = "+headers);
        headers.forEach((key,value) ->{
            System.out.println("Header Name: "+key+" Header Value: "+value);
        });
    }*/




}





