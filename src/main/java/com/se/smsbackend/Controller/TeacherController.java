package com.se.smsbackend.Controller;

import com.se.smsbackend.Entity.Teacher;
import com.se.smsbackend.Repository.TeacherRepo;
import com.se.smsbackend.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.persistence.NonUniqueResultException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

import static com.se.smsbackend.Site.Utility.getSiteURL;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class TeacherController {
 @Autowired
    TeacherRepo teacherRepo;
 @Autowired
    TeacherService teacherService;

    @PostMapping(value="/teacher/register" )
    public String saveTeacher(@RequestBody Teacher teacher, HttpServletRequest request)throws MessagingException, UnsupportedEncodingException, NonUniqueResultException {
        if (teacherRepo.findByUsername(teacher.getUsername())!=null) {   //neglected unverified accounts
            return "This Email already registered";
        } else {
            teacherService.saveTeacher(teacher, getSiteURL(request));
            return "Successfully submitted. Please check your Emails ";
        }

    }





}
