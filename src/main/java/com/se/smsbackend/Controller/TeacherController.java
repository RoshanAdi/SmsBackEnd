package com.se.smsbackend.Controller;

import com.se.smsbackend.Entity.*;
import com.se.smsbackend.Repository.*;
import com.se.smsbackend.Service.SubjectMarksService;
import com.se.smsbackend.Service.TeacherService;
import com.se.smsbackend.Service.TeacherServiceImpl;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.persistence.NonUniqueResultException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static com.se.smsbackend.Site.Utility.getSiteURL;

@RestController
@RequestMapping("/")
public class TeacherController {
    @Autowired
    TeacherRepo teacherRepo;
    @Autowired
    TeacherService teacherService;
    @Autowired
    TeacherServiceImpl teacherServiceImpl;
    @Autowired
    SubjectRepo subjectRepo;
    @Autowired
    AssignmentRepo assignmentRepo;
    @Autowired
    McqRepo mcqRepo;
    @Autowired
    EssayQuestionRepo essayQuestionRepo;
    @Autowired
    EssayAsnwersRepo essayAsnwersRepo;
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    AssignmentsMarksRepository marksRepository;
    @Autowired
    SubjectMarksService studentMarksService;
    @Autowired
    ReportRepo reportRepo;
    @Autowired
    SubjectMarksRepo subjectMarksRepo;

    @PostMapping(value = "/teacher/register")
    public String saveTeacher(@RequestBody Teacher teacher, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException, NonUniqueResultException {
        try {
            if (teacherRepo.findByUsername(teacher.getUsername()) != null) {   //neglected unverified accounts
                return "This Email already registered";
            } else {
                teacherService.saveTeacher(teacher, getSiteURL(request));
                return "Successfully submitted. Please check your Emails ";
            }
        }
        catch (NullPointerException e){
            teacherService.saveTeacher(teacher, getSiteURL(request));
        }
        return null;
    }

    @GetMapping("/verifyT")

    public ModelAndView verifyUser(@Param("code") String code) {
        System.out.println("clicked the verification link the code is " + code);
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
            teacher1.setUsername(teacher.getUsername());
            teacher1.setFirstName(teacher.getFirstName());
            teacher1.setAddress(teacher.getAddress());
            teacher1.setFullName(teacher.getFullName());
            teacher1.setLastName(teacher.getLastName());
            teacher1.setRole(teacher.getRole());
            teacher1.setnIc(teacher.getnIc());
            teacher1.setTp(teacher.getTp());
            teacher1.setSubjects(teacher.getSubjects());
            return teacher1;
        } catch (NoSuchElementException | NullPointerException e) {
            return null;
        }
    }

    @PreAuthorize("hasRole('Teacher')")
    @PutMapping("/Teacher/{Username}")
    public ResponseEntity<?> update(@RequestBody Teacher teacher, @PathVariable String Username, HttpServletRequest request) {
        Teacher existTeacher = teacherRepo.findByUsername(Username);
        existTeacher.setAddress(teacher.getAddress());
        existTeacher.setFullName(teacher.getFullName());
        existTeacher.setTp(teacher.getTp());
        existTeacher.setFirstName(teacher.getFirstName());
        existTeacher.setnIc(teacher.getnIc());
        teacherRepo.save(existTeacher);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PreAuthorize("hasRole('Teacher')")
    @PostMapping(value = "/Subject/Create")
    public String saveSubject(@RequestBody Subject subject) throws MessagingException, UnsupportedEncodingException, NonUniqueResultException {
        if (subjectRepo.findBySubjectName(subject.getSubjectName()) != null) {   //neglected unverified accounts

            return "This Subject is already there";
        } else {
            subjectRepo.save(subject);
            return "Successfully created";
        }

    }

    @PreAuthorize("hasAnyRole('Teacher','Student')")
    @GetMapping("/Subject/List")
    public List<Subject> subList() {
        return subjectRepo.findAll();
    }

    @PreAuthorize("hasAnyRole('Teacher','Student')")
    @GetMapping("/Subject/{id}")
    public Subject subject(@PathVariable int id) {

        return subjectRepo.findById(id);
    }

    @PreAuthorize("hasRole('Teacher')")
    @PutMapping("/Subject/Update/{subjectId}")
    public ResponseEntity<?> updateSub(@RequestBody Assignment assignment, @PathVariable int subjectId, HttpServletRequest request) {
        Subject existSubject = subjectRepo.findById(subjectId);
        assignment.setSubjectForAssignment(existSubject);
        assignmentRepo.save(assignment);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasRole('Teacher')")
    @PutMapping("/Subject/edit/{subjectId}")
    public ResponseEntity<?> editSub(@RequestBody Subject subject, @PathVariable int subjectId, HttpServletRequest request) {
        Subject existSubject = subjectRepo.findById(subjectId);
        existSubject.setSubjectName(subject.getSubjectName());
        existSubject.setDescription(subject.getDescription());
        subjectRepo.save(existSubject);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasRole('Teacher')")
    @PutMapping("/Assignment/edit/{assigmentID}")
    public ResponseEntity<?> editAssignment(@RequestBody Assignment assignment, @PathVariable int assigmentID, HttpServletRequest request) {
        Assignment existAssignment = assignmentRepo.findById(assigmentID);
        existAssignment.setAssigmentTitle(assignment.getAssigmentTitle());
        existAssignment.setAssigmentDiscription(assignment.getAssigmentDiscription());
        existAssignment.setAssigmentData(assignment.getAssigmentData());
        existAssignment.setEndTime(assignment.getEndTime());
        existAssignment.setStartTime(assignment.getStartTime());
        existAssignment.setNoOfAttempts(assignment.getNoOfAttempts());
        assignmentRepo.save(existAssignment);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasRole('Teacher')")
    @DeleteMapping("/Assignment/delete/{id}")
    public void deleteAssi(@PathVariable Integer id) {
        assignmentRepo.deleteById(id);
    }
    @PreAuthorize("hasRole('Teacher')")
    @DeleteMapping("/Subject/delete/{id}")
    public void deleteSub(@PathVariable Integer id) {
        subjectRepo.deleteById(id);
    }
    @PreAuthorize("hasRole('Teacher')")
    @PostMapping(value = "/Mcq/create/{AssiId}")
    public String AddMcq(@RequestBody McqQuestion mcqQuestion, @PathVariable int AssiId, HttpServletRequest request) {
        Assignment existAssi = assignmentRepo.findById(AssiId);
        mcqQuestion.setAssignmentQuestions(existAssi);
            mcqRepo.save(mcqQuestion);
         return "saved";

    }
    @PreAuthorize("hasAnyRole('Teacher','Student')")
    @GetMapping("/Assignment/{id}")
    public Assignment Assigment4Mcq(@PathVariable int id) {

        return assignmentRepo.findById(id);
    }
    @PreAuthorize("hasRole('Teacher')")
    @DeleteMapping("/Mcq/delete/{id}")
    public void deleteMcq(@PathVariable Integer id) {
        mcqRepo.deleteById(id);
    }

    @PreAuthorize("hasRole('Teacher')")
    @PutMapping("/Teacher/Enroll/{subjectID}")
    public ResponseEntity<?> enrollTeacher( @PathVariable int subjectID) {
        Subject subject = subjectRepo.findById(subjectID);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentTeacher = authentication.getName();
        Teacher teacher = teacherRepo.findByUsername(currentTeacher);
        if (subject.getTeachers()==null){
            Set<Teacher> teachers = new HashSet<>();
            teachers.add(teacher);
            subject.setTeachers(teachers);
            subjectRepo.save(subject);}
        else { subject.getTeachers().add(teacher);
            subjectRepo.save(subject);}
        System.out.println(teacher.getSubjects());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasRole('Teacher')")
    @PostMapping(value = "/EssayQuestion/create/{AssiId}")
    public String AddEssayQ(@RequestBody EssayQuestion essayQuestion, @PathVariable int AssiId) {
        Assignment existAssi = assignmentRepo.findById(AssiId);
        essayQuestion.setAssignmentEssayQ(existAssi);
        essayQuestionRepo.save(essayQuestion);
        return "saved";

    }

    @PreAuthorize("hasAnyRole('Teacher','Student')")
    @GetMapping("/EssayQuestions/{AssiId}")
    public List<EssayQuestion> QuestionsList( @PathVariable int AssiId) {
        Assignment assignment = assignmentRepo.findById(AssiId);
        return essayQuestionRepo.findByAssignmentEssayQ(assignment);
    }

    @PreAuthorize("hasRole('Teacher')")
    @DeleteMapping("/EssayQuestions/delete/{id}")
    public void deleteEssayQ(@PathVariable Integer id) {
        essayQuestionRepo.deleteById(id);
    }

    @PreAuthorize("hasRole('Teacher')")
    @GetMapping("/EssayAnswers/{AssiId}")
    public List<EssayAnswers> QuestionsAnswerList( @PathVariable String AssiId) {
         return essayAsnwersRepo.findByAssigmentID(AssiId);
    }

    @PreAuthorize("hasRole('Teacher')")
    @PostMapping("/marks/EssayQuestions/{TotalMarks}")
    public ResponseEntity<?> saveMarks(@RequestBody AssignmentMarks marks, @PathVariable String TotalMarks) {

        Assignment assignment = assignmentRepo.findById(marks.getAssignmentId());
        Student student = studentRepo.findByUsername(marks.getStudentUsername());
        AssignmentMarks exsistingMark = marksRepository.findByMarksupdateId(marks.getStudentUsername()+marks.getAssignmentId());
        exsistingMark.setStudent(student);
        exsistingMark.setAssignment(assignment);
        exsistingMark.setAssignmentId(marks.getAssignmentId());
        exsistingMark.setStudentUsername(student.getUsername());
            String var = marks.getMarks();
            String var2 = var+"/"+TotalMarks;
            exsistingMark.setMarks(var2);
            marksRepository.save(exsistingMark);
            System.out.println("new marks received = " + marks.getMarks());

           return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasRole('Teacher')")
    @PostMapping("/marks/File/{TotalMarks}")
    public ResponseEntity<?> saveFileMarks(@RequestBody AssignmentMarks marks1, @PathVariable String TotalMarks) {

        Assignment assignment = assignmentRepo.findById(marks1.getAssignmentId());
        Student student = studentRepo.findByUsername(marks1.getStudentUsername());
        AssignmentMarks marks = marksRepository.findByMarksupdateId(marks1.getStudentUsername()+marks1.getAssignmentId());
        marks.setStudent(student);
        marks.setAssignment(assignment);
        String var = marks1.getMarks();
        String var2 = var+"/"+TotalMarks;
        marks.setMarks(var2);
        marksRepository.save(marks);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasRole('Teacher')")
    @GetMapping("/Subject/SubjectMarks/{id}")
    public List getSubjectForSubjectMarks(@PathVariable int id) {

        return studentMarksService.getBySubjectId(id);
    }

    @PreAuthorize("hasRole('Teacher')")
    @PostMapping("/Subject/MarksSubmit/{SubjectID}")
    public void saveSubMarks(@PathVariable int SubjectID, @RequestBody String marksString) throws ParseException {
        studentMarksService.save(SubjectID,marksString);

    }
    @PreAuthorize("hasAnyRole('Teacher','Student')")
    @GetMapping("/Report")
    public List<Report> getReport() {

        return reportRepo.findAll();
    }
    @PreAuthorize("hasAnyRole('Teacher','Student')")
    @GetMapping("/subjectMarks/{subId}")
    public List<SubjectMarks> getReport(@PathVariable String subId) {

        return subjectMarksRepo.findBySubjectId(subId);
    }

    }