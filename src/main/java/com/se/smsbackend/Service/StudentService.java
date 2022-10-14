package com.se.smsbackend.Service;

import com.se.smsbackend.Entity.Student;
import com.se.smsbackend.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class StudentService {

@Autowired
private JavaMailSender mailSender;
    @Autowired
    private StudentRepo studentRepo;

    public List<Student> listAllStudents() {
        return studentRepo.findAll();
    }

  /*  public void saveStudent(StudentDto student, String siteURL) throws MessagingException, UnsupportedEncodingException {
        System.out.println("Trying to save student");
        student.setEnabled(false);
        String randomCode = RandomString.make(64);
        student.setVerificationCode(randomCode);
        studentRepo.save(student);

        System.out.println(student);

            System.out.println("trying......");

            sendVerificationEmail(student, siteURL);


    }*/
   /* private void sendVerificationEmail(StudentDto student, String siteURL) throws MessagingException, UnsupportedEncodingException {
        String toAddress = student.getUsername();
        System.out.println(student.getUsername());
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

        content = content.replace("[[name]]", student.getFullName());
        String verifyURL = siteURL + "/verify?code=" + student.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);


    }*/

    public Student getStudent(Integer id) {
        return studentRepo.findById(id).get();
    }

    //public List<Student> getByNameContaining(String name) {return studentRepo.findByFullNameContaining(name);}

    public void deleteStudent(Integer id) {
        studentRepo.deleteById(id);
    }
 /*   public boolean verify(String verificationCode) {
        Student student = studentRepo.findByVerificationCode(verificationCode);
        System.out.println("verifying...");

        if (student == null || student.isEnabled()) {
            return false;
        } else {
            student.setVerificationCode(null);
            student.setEnabled(true);



           student.setRole("Student");


            studentRepo.save(student);

            return true;
        }

    }*/
}