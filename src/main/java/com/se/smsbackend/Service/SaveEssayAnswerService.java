package com.se.smsbackend.Service;

import com.se.smsbackend.Entity.EssayAnswers;
import com.se.smsbackend.Entity.AssignmentMarks;
import com.se.smsbackend.Repository.*;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Component
public class SaveEssayAnswerService {
    @Autowired
    EssayAsnwersRepo essayAsnwersRepo;
    @Autowired
    EssayQuestionRepo essayQuestionRepo;
    @Autowired
    AssignmentRepo assignmentRepo;
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    AssignmentsMarksRepository marksRepository;

    public void save(int AssignmentId, String username, String answerString) throws ParseException, ArrayIndexOutOfBoundsException,NullPointerException {

/*        if (essayAsnwersRepo.findByUpdateId(username + AssignmentId) == null) {
 *//*           if (marksRepository.findByMarksupdateId(username + AssignmentId)==null){*//*
            Marks marks = new Marks();
            marks.setAssignmentId(AssignmentId);
            marks.setMarksupdateId(username + AssignmentId);
            marks.setAttempt(2);
            marksRepository.save(marks);*//*}
            else {
                Marks marks = marksRepository.findByMarksupdateId(username + AssignmentId);
            int attempt = marks.getAttempt();
            marks.setAttempt(attempt+1);
            marksRepository.save(marks);
            }*//*
            JSONObject obj2 = new JSONObject(answerString);
            Set<String> keys = obj2.keySet();
            List<String> stringsList = new ArrayList<>(keys);


            for (int i = 0; i < obj2.length(); i++) {
                int question = Integer.parseInt(stringsList.get(i));
                String answer = obj2.getString(String.valueOf(question));
                EssayAnswers essayAnswers = new EssayAnswers();
                essayAnswers.setAnswer(answer);
                essayAnswers.setQuestion(essayQuestionRepo.findById(question));
                essayAnswers.setStudent(studentRepo.findByUsername(username));
                essayAnswers.setAssigmentID(String.valueOf(AssignmentId));
                essayAnswers.setUsername(username);
                essayAnswers.setUpdateId(username + AssignmentId);
                essayAsnwersRepo.save(essayAnswers);
                System.out.println("Value = " + essayAnswers);
            }

        } else {*/
            if (marksRepository.findByMarksupdateId(username + AssignmentId)==null){
                AssignmentMarks marks = new AssignmentMarks();
                marks.setAssignmentId(AssignmentId);
                marks.setMarksupdateId(username + AssignmentId);
                marks.setAttempt(2);
                marks.setAssignment(assignmentRepo.findByAssigmentID(AssignmentId));
                marksRepository.save(marks);
            }
            else {
                AssignmentMarks marks = marksRepository.findByMarksupdateId(username + AssignmentId);
                marks.setAttempt(marks.getAttempt() + 1);
                marksRepository.save(marks);
            }
             List<EssayAnswers> essayAnswersList= essayAsnwersRepo.findByUpdateId(username + AssignmentId);
            for (EssayAnswers essayAnswers : essayAnswersList) {
                essayAsnwersRepo.deleteById(essayAnswers.getId());
            }
            JSONObject obj2 = new JSONObject(answerString);
            Set<String> keys = obj2.keySet();
            List<String> stringsList = new ArrayList<>(keys);


            for (int i = 0; i < obj2.length(); i++) {
                int question = Integer.parseInt(stringsList.get(i));
                String answer = obj2.getString(String.valueOf(question));
                EssayAnswers essayAnswers = new EssayAnswers();
                essayAnswers.setAnswer(answer);
                essayAnswers.setQuestion(essayQuestionRepo.findById(question));
                essayAnswers.setStudent(studentRepo.findByUsername(username));
                essayAnswers.setAssigmentID(String.valueOf(AssignmentId));
                essayAnswers.setUsername(username);
                essayAnswers.setUpdateId(username + AssignmentId);
                essayAsnwersRepo.save(essayAnswers);
                System.out.println("Value = " + essayAnswers);
            }
        }

    }
/*
}*/
