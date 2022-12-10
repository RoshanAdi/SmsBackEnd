package com.se.smsbackend.Service;

import com.se.smsbackend.Entity.Report;
import com.se.smsbackend.Entity.Student;
import com.se.smsbackend.Entity.Subject;
import com.se.smsbackend.Entity.SubjectMarks;
import com.se.smsbackend.Repository.ReportRepo;
import com.se.smsbackend.Repository.StudentRepo;
import com.se.smsbackend.Repository.SubjectMarksRepo;
import com.se.smsbackend.Repository.SubjectRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

@Service
public class SubjectMarksService {
    @Autowired
    StudentRepo studentRepo;

    @Autowired
    SubjectRepo subjectRepo;
    @Autowired
    SubjectMarksRepo subjectMarksRepo;
    @Autowired
    ReportRepo reportRepo;


    public List getBySubjectId(int id){ // may be json ignorring.. yess
Subject subject = subjectRepo.findById(id);
List<Student> studentList = subject.getStudents();
               List<Student> newStudentList = new ArrayList<>();
        for (Student stud : studentList) {
            Student student = new Student();
            student.setFullName(stud.getFullName());
            student.setUsername(stud.getUsername());
            student.setStudentId(student.getStudentId());
            newStudentList.add(student);
        }
/*        subject.setStudents(newStudentList);
        subject.setAssigmentList(null);*/
         return newStudentList;
    }


    public void save(int SubjectId, String MarksString) throws  ArrayIndexOutOfBoundsException,NullPointerException {

        Subject subject = subjectRepo.findById(SubjectId);
        System.out.println("received answer string = "+MarksString);
        JSONObject obj2 = new JSONObject(MarksString);
        String outOf = obj2.getString("totalMarks");
        obj2.remove("totalMarks");
        Set<String> keys = obj2.keySet();
            List<String> usernameList = new ArrayList<>(keys);
        HashMap<String, Integer> RankArray = new HashMap<String, Integer>();


        for (int i = 0; i < obj2.length(); i++) {
            String username = usernameList.get(i);
            String mark = obj2.getString(username);
            RankArray.put(username, Integer.valueOf(mark));
            if (subjectMarksRepo.findByMarksUpdateId(username+SubjectId)==null){
            System.out.println("username = "+username+" mark = "+mark);
            SubjectMarks subjectMarks = new SubjectMarks();
            subjectMarks.setMarks(mark);
            subjectMarks.setMarksUpdateId(username+SubjectId);
            subjectMarks.setSubjectId(String.valueOf(SubjectId));
            subjectMarks.setSubject(subject);
            subjectMarks.setStudentName(studentRepo.findByUsername(username).getFullName());
            subjectMarks.setStudent(studentRepo.findByUsername(username));
            subjectMarks.setStudentUsername(username);
                if (outOf==null){
                    subjectMarks.setMarksOutOf(String.valueOf(100));
                }
                else {
                    subjectMarks.setMarksOutOf(outOf);
                }

            subjectMarksRepo.save(subjectMarks);}
            else {
                SubjectMarks subjectMarks=subjectMarksRepo.findByMarksUpdateId(username+SubjectId);
                subjectMarks.setMarks(mark);
                subjectMarksRepo.save(subjectMarks);
            }

        }
        findRank(String.valueOf(SubjectId), RankArray);
        CalStats( RankArray,SubjectId,outOf);
    }

public void findRank(String SubjectId, HashMap RankArray){

    Map<String, Integer> hm1 = sortByValue(RankArray);

    // print the sorted hashmap
    int length = hm1.size();
    int i = 0;
    int j = 0;
    int previousValue=-100;
    int previousPreviousValue=-101;

    for (Map.Entry<String, Integer> en :
            hm1.entrySet()) {  i = i+1;
        System.out.println("Key = " + en.getKey()
                + ", Value = "
                + en.getValue());
        SubjectMarks currentMark = subjectMarksRepo.findByMarksUpdateId(en.getKey()+SubjectId);
        if (previousValue==en.getValue()&&previousValue==previousPreviousValue){
            i=i-1;
            j=j+1;
            currentMark.setStudRank(i-1);


        }
        if (previousValue==en.getValue()&&previousValue!=previousPreviousValue){

            j=0;
            currentMark.setStudRank(i-1);
            previousPreviousValue = previousValue;
        }

        else if (previousValue!=en.getValue()) {
            i=j+i;
            currentMark.setStudRank(i);
          }

        previousValue= en.getValue();
        subjectMarksRepo.save(currentMark);
    }
}
    public static HashMap<String, Integer>  sortByValue(HashMap<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list
                = new LinkedList<Map.Entry<String, Integer> >(
                hm.entrySet());

        // Sort the list using lambda expression
        Collections.sort(
                list,
                Map.Entry.<String, Integer>comparingByValue().reversed());


        // put data from sorted list to hashmap
        HashMap<String, Integer> temp
                = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
        public void CalStats(HashMap RankArray , int subId,String outOf){
        Subject subject = subjectRepo.findById(subId);
        double total = 0;
        int nullParticipantsCounter = 0;
        int below30Counter=0;
        int between30And55Counter=0;
        int between55And65Counter=0;
        int between65And75Counter=0;
        int above75Counter=0;
        int OutOf = 0;
        if (outOf==null){
            OutOf = 100;
        }
        else {
            OutOf = Integer.parseInt(outOf);
        }
        for (Object value : RankArray.values()) {
            System.out.println("out of = "+OutOf);
            System.out.println("marks int value = "+Integer.parseInt(value.toString()));
            double marks = (Integer.parseInt(value.toString()))*100/OutOf;
            System.out.println("marks = "+marks);
            if (Integer.parseInt(value.toString())==0){nullParticipantsCounter=nullParticipantsCounter+1;}
            if (marks>0&&marks<30)
            {below30Counter=below30Counter+1;}
            if (marks>29&&marks<55)
            {between30And55Counter=between30And55Counter+1;}
            if (marks>54&&marks<65)
            {between55And65Counter=between55And65Counter+1;}
            if (marks>64&&marks<75)
            {between65And75Counter=between65And75Counter+1;}
            if (marks>74)
            {above75Counter=above75Counter+1;}

            total= Integer.parseInt(value.toString())+total;
        }

          int noOfParticipants = RankArray.size()-nullParticipantsCounter;
        double average = (total/noOfParticipants)*100/OutOf;
            DecimalFormat numberFormat = new DecimalFormat("#.00");
           System.out.println("average = "+average);
            Report report = new Report();
            report.setSubjectId(String.valueOf(subId));
            report.setSubjectName(subject.getSubjectName());
            report.setAverageMarks(numberFormat.format(average));
            report.setTotalMarks((int) total);
            report.setTotalParticipants(noOfParticipants);
            report.setBelow30(below30Counter);
            report.setNoOfStudBetween30and55(between30And55Counter);
            report.setNoOfStudBetween55and65(between55And65Counter);
            report.setNoOfStudBetween65and75(between65And75Counter);
            report.setNoOfStudAbove75(above75Counter);
            report.setOutOf(OutOf);
            reportRepo.save(report);
    }
}
