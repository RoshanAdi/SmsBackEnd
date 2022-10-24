package com.se.smsbackend.Service;

import com.se.smsbackend.Entity.Teacher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
@Component
@Service
public interface TeacherService {
    Teacher saveTeacher(Teacher teacher, String siteURL) throws MessagingException, UnsupportedEncodingException;
}
