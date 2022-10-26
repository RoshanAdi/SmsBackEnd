package com.se.smsbackend.Controller;

import com.se.smsbackend.Entity.Teacher;
import com.se.smsbackend.Repository.TeacherRepo;
import com.se.smsbackend.Service.TeacherService;
import com.se.smsbackend.Service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.persistence.NonUniqueResultException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.NoSuchElementException;

import static com.se.smsbackend.Site.Utility.getSiteURL;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class TeacherController {
 @Autowired
    TeacherRepo teacherRepo;
 @Autowired
    TeacherService teacherService;
 @Autowired
    TeacherServiceImpl teacherServiceImpl;

    @PostMapping(value="/teacher/register" )
    public String saveTeacher(@RequestBody Teacher teacher, HttpServletRequest request)throws MessagingException, UnsupportedEncodingException, NonUniqueResultException {
        if (teacherRepo.findByUsername(teacher.getUsername())!=null) {   //neglected unverified accounts
            return "This Email already registered";
        } else {
            teacherService.saveTeacher(teacher, getSiteURL(request));
            return "Successfully submitted. Please check your Emails ";
        }

    }

    @GetMapping("/verifyT")

    public ModelAndView verifyUser(@Param("code") String code) {
        System.out.println("clicked the verification link the code is "+code);
        if (teacherServiceImpl.verify(code)) {
            return new ModelAndView("redirect:" + "http://localhost:4200/regSuccess");

        } else {
            return new ModelAndView("redirect:" + "http://localhost:4200/regFail");
        }
    }

    @PreAuthorize("hasRole('Teacher')")
    @GetMapping("/Teacher/{name}")
    public Teacher get(@PathVariable String name) {
        try {
            Teacher teacher = teacherRepo.findByUsername(name);
            Teacher teacher1 = new Teacher();
            teacher1.setUsername(teacher.getUsername()) ;
            teacher1.setFirstName(teacher.getFirstName());
            teacher1.setAddress(teacher.getAddress());
            teacher1.setFullName(teacher.getFullName());
            teacher1.setLastName(teacher.getLastName());
            teacher1.setRole(teacher.getRole());
            teacher1.setnIc(teacher.getnIc());
            teacher1.setTp(teacher.getTp());
            return teacher1;
        } catch (NoSuchElementException | NullPointerException e) {
            return null;
        }    }
    @PreAuthorize("hasRole('Teacher')")
    @PutMapping("/Teacher/{Username}")
    public ResponseEntity<?> update(@RequestBody Teacher teacher, @PathVariable String Username , HttpServletRequest request)  {
        Teacher existTeacher = teacherRepo.findByUsername(Username);
        existTeacher.setAddress(teacher.getAddress());
        existTeacher.setFullName(teacher.getFullName());
        existTeacher.setTp(teacher.getTp());
        existTeacher.setFirstName(teacher.getFirstName());
        existTeacher.setnIc(teacher.getnIc());
               teacherRepo.save(existTeacher);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
