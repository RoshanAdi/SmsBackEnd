
package com.se.smsbackend.Controller;

import com.se.smsbackend.Dto.StudentDto;
import com.se.smsbackend.Entity.*;
import com.se.smsbackend.Repository.*;
import com.se.smsbackend.Security.AuthToken;
import com.se.smsbackend.Security.LoginUser;
import com.se.smsbackend.Security.TokenProvider;
import com.se.smsbackend.Service.SaveEssayAnswerService;
import com.se.smsbackend.Service.StudentService;
import com.se.smsbackend.Service.StudentService2;
import com.se.smsbackend.Service.StudentServiceImpl;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
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
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import static com.se.smsbackend.Site.Utility.getSiteURL;

@RestController
@RequestMapping("/")
public class StudentController {
    @Autowired
    StudentService studentService;

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
    @Autowired
    SubjectRepo subjectRepo;
    @Autowired
    MarksRepository marksRepository;
    @Autowired
    AssignmentRepo assignmentRepo;
    @Autowired
    EssayAsnwersRepo essayAsnwersRepo;
    @Autowired
    SaveEssayAnswerService saveEssayAnswerService;




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
    @GetMapping("/Student/{name}")
    public Student gettt(@PathVariable String name) {
        Student exsiStudent = studentRepo.findByUsername(name);
        exsiStudent.setPassword(null);
        exsiStudent.setVerificationCode(null);

        System.out.println("sending requested student = "+exsiStudent);
        return exsiStudent;
    }
    @PreAuthorize("hasRole('Student')")
   @PutMapping("/Student/{Username}")
    public ResponseEntity<?> update(@RequestBody Student student, @PathVariable String Username , HttpServletRequest request)  {
           Student existStudent = studentRepo.findByUsername(Username);
        System.out.println("printing receved for update = "+student);
           existStudent.setAddress(student.getAddress());
            existStudent.setFullName(student.getFullName());
            existStudent.setTp(student.getTp());
            existStudent.setFirstName(student.getFirstName());
            System.out.println("printing student got for updating details = "+existStudent);
            studentRepo.save(existStudent);
            return new ResponseEntity<>(HttpStatus.OK);

        }

/*
    @DeleteMapping("/student/{id}")
    public void delete(@PathVariable Integer id) {
        studentService.deleteStudent(id);
    }*/

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
    @PutMapping("/Student/Enroll/{subjectID}")
    public ResponseEntity<?> enrollStud( @PathVariable int subjectID) {
        Subject subject = subjectRepo.findById(subjectID);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentStud = authentication.getName();
        Student student = studentRepo.findByUsername(currentStud);
        if (subject.getStudents()==null){
            Set<Student> students = new HashSet<>();
            students.add(student);
            subject.setStudents(students);
            subjectRepo.save(subject);}
        else { subject.getStudents().add(student);
            subjectRepo.save(subject);}
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('Student')")
    @GetMapping("/Student/Subjects/{name}")
    public Student getStudSubjects(@PathVariable String name) {
        try {
            Student student = studentRepo.findByUsername(name);
            Student student1 = new Student();
            student1.setSubjects(student.getSubjects());

            return student1;
        } catch (NoSuchElementException | NullPointerException e) {
            return null;
        }
    }
    @PreAuthorize("hasRole('Student')")
    @PutMapping("/marks/{assigmentID}/{username}")
    public ResponseEntity<?> saveMarks( @PathVariable int assigmentID,@PathVariable String username,@RequestBody Marks marks) {

        Assignment assignment = assignmentRepo.findById(assigmentID);
        Student student = studentRepo.findByUsername(username);
        System.out.println("new marks received = " + marks);
        if(marksRepository.findByMarksupdateId(marks.getMarksupdateId())==null) {
            marks.setStudent(student);
            marks.setAssignment(assignment);
            marks.setAssignmentId(assigmentID);
            marks.setStudentUsername(student.getUsername());
            marks.setAttempt(1);
            marksRepository.save(marks);
        }
        else {
            Marks marksExsist = marksRepository.findByMarksupdateId(marks.getMarksupdateId());
            marksExsist.setMarks(marks.getMarks());
            marksExsist.setAttempt(marksExsist.getAttempt()+1);
            marksRepository.save(marksExsist);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasRole('Student')")
    @GetMapping("/marks/{marksupdateId}")
    public Marks getMarks(@PathVariable String marksupdateId) {
        Marks marks = marksRepository.findByMarksupdateId(marksupdateId);
        System.out.println("marks found from the database = "+marks);
        return marks;
    }

    @PreAuthorize("hasRole('Student')")
    @PostMapping("/EssayQuestions/answerSubmit/{assigmentID}/{username}")
    public void saveEssayAnswers(@PathVariable int assigmentID, @PathVariable String username, @RequestBody String essayAnswers) throws ParseException {
    saveEssayAnswerService.save(assigmentID,username,essayAnswers);
        System.out.println("Received username = "+username);
        System.out.println("Received essayanswers = "+essayAnswers);


   /*    EssayAnswers essayAnswers = new EssayAnswers();
       essayAnswers.setEssayAnswersList(answerString);
       essayAnswers.setUsername(username);
       essayAnswers.setAssigmentID(assigmentID);
        System.out.println("pirnting essayanswer got = "+essayAnswers);
        essayAsnwersRepo.save(essayAnswers);*/
    }


    }


