package Entity;

import javax.persistence.*;

@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int AttendanceID;
    private String AttendanceDate;
    private String AttendanceStatus;

    @ManyToOne
    private Student studentForAttendance;


    public Student getStudentForAttendance() {
        return studentForAttendance;
    }

    public void setStudentForAttendance(Student studentForAttendance) {
        this.studentForAttendance = studentForAttendance;
    }

    public int getAttendanceID() {
        return AttendanceID;
    }

    public void setAttendanceID(int attendanceID) {
        AttendanceID = attendanceID;
    }

    public String getAttendanceDate() {
        return AttendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {
        AttendanceDate = attendanceDate;
    }

    public String getAttendanceStatus() {
        return AttendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        AttendanceStatus = attendanceStatus;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "AttendanceID=" + AttendanceID +
                ", AttendanceDate='" + AttendanceDate + '\'' +
                ", AttendanceStatus='" + AttendanceStatus + '\'' +
                '}';
    }
}
