package com.se.smsbackend.Service;

import com.se.smsbackend.Entity.Teacher;
import com.se.smsbackend.Repository.TeacherRepo;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
@Component
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TeacherRepo teacherRepo;


    @Override
    public Teacher saveTeacher(Teacher teacher, String siteURL) throws MessagingException, UnsupportedEncodingException {
        System.out.println("trying to save username and pass "+teacher.getPassword());
        teacher.setRole("Student");

        teacher.setEnabled(false);
        String randomCode = RandomString.make(64);
        teacher.setVerificationCode(randomCode);
        sendVerificationEmail(teacher, siteURL);
       // System.out.println("password"+nStudent.getPassword()+"verification"+nStudent.getVerificationCode());
        teacher.setFirstName(null);


        return teacher;


}
    private void sendVerificationEmail(Teacher teacher, String siteURL) throws MessagingException, UnsupportedEncodingException {
        //Student nStudent = student.getStudentFromDto();
        String toAddress = teacher.getUsername();
        //System.out.println(nStudent.getUsername());
        String fromAddress = "smsemail83@gmail.com";
        String senderName = "SmsProject";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Dinusha&Roshan.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", teacher.getFullName());
        String verifyURL = siteURL + "/verifyT?code=" + teacher.getVerificationCode();
        //System.out.println(nStudent.getVerificationCode());


        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);
        Teacher teacher1 = teacher;
        teacher.setPassword(bcryptEncoder.encode(teacher1.getPassword()));
        teacherRepo.save(teacher);


    }
    public boolean verify(String verificationCode) {
        Teacher teacher = teacherRepo.findByVerificationCode(verificationCode);
        System.out.println("verifying...");
        System.out.println("verification code received "+verificationCode);
        //System.out.println("verfication code of the saved stud"+teacher.getVerificationCode());

        if (teacher == null || teacher.isEnabled()) {
            return false;
        }
        else {
            teacher.setEnabled(true);

            System.out.println("enabling as true...");

            teacher.setRole("Teacher");    //cannot log until role sets at this point.
            teacherRepo.save(teacher);

            return true;
        }

    }



}
