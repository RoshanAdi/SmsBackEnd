package com.se.smsbackend.Service;

import com.se.smsbackend.Dto.StudentDto;
import com.se.smsbackend.Entity.Student;
import com.se.smsbackend.Repository.StudentRepo;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service(value = "userService")
@Component
public class StudentServiceImpl implements UserDetailsService, StudentService2 {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepo.findByUsername(username);
        System.out.println("username "+student.getUsername()+" pass "+student.getPassword());
        if(student == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(student.getUsername(), student.getPassword(), getAuthority(student));
    }

    private Set<SimpleGrantedAuthority> getAuthority(Student student) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

            authorities.add(new SimpleGrantedAuthority(student.getRole()));
        System.out.println("printing generated authorities "+authorities);
        return authorities;
    }




    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();
        studentRepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Student findOne(String username) {
        return studentRepo.findByUsername(username);
    }

    @Override
    public Student saveUser(StudentDto student, String siteURL) throws MessagingException, UnsupportedEncodingException {
        System.out.println("trying to save username and pass "+student.getPassword());
        Student nStudent = student.getStudentFromDto();
        System.out.println("printing role "+nStudent.getRole());
        nStudent.setRole("Student");

        student.setEnabled(false);
        String randomCode = RandomString.make(64);
        student.setVerificationCode(randomCode);
        sendVerificationEmail(student, siteURL);
        System.out.println("password"+nStudent.getPassword()+"verification"+nStudent.getVerificationCode());
        student.setFirstName(null);


        return nStudent;
    }
    private void sendVerificationEmail(StudentDto student, String siteURL) throws MessagingException, UnsupportedEncodingException {
        Student nStudent = student.getStudentFromDto();
        String toAddress = nStudent.getUsername();
        System.out.println(nStudent.getUsername());
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

        content = content.replace("[[name]]", nStudent.getFullName());
        String verifyURL = siteURL + "/verify?code=" + nStudent.getVerificationCode();
        System.out.println(nStudent.getVerificationCode());


        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);
        studentRepo.save(nStudent);


    }
    public boolean verify(String verificationCode) {
        Student student = studentRepo.findByVerificationCode(verificationCode);
        System.out.println("verifying...");
        System.out.println("verification code received "+verificationCode);
        System.out.println("verfication code of the saved stud"+student.getVerificationCode());

        if (student == null || student.isEnabled()) {
            return false;
        }
        else {
             student.setEnabled(true);

            System.out.println("enabling as true...");
            student.setPassword(bcryptEncoder.encode(student.getPassword()));
            student.setRole("Student");
            studentRepo.save(student);




            return true;
        }

    }
}
