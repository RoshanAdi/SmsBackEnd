package Entity;

import javax.persistence.OneToOne;

public class Exam {
    private int ExamId;
    private String ExamTitle;
    private String ExamDate;

    @OneToOne
    private Subject SubjectForExam;

    public Subject getSubjectForExam() {
        return SubjectForExam;
    }

    public void setSubjectForExam(Subject subjectForExam) {
        SubjectForExam = subjectForExam;
    }

    public int getExamId() {
        return ExamId;
    }

    public void setExamId(int examId) {
        ExamId = examId;
    }

    public String getExamTitle() {
        return ExamTitle;
    }

    public void setExamTitle(String examTitle) {
        ExamTitle = examTitle;
    }

    public String getExamDate() {
        return ExamDate;
    }

    public void setExamDate(String examDate) {
        ExamDate = examDate;
    }
}
