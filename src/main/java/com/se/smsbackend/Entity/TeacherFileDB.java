package com.se.smsbackend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
    @Table(name = "files")
    public class TeacherFileDB {
        @Id
        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name = "uuid", strategy = "uuid2")
        private String id;

        private String name;

        private String type;
    @ManyToOne
    @JoinColumn(name = "AssignmentOfFile", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Assignment assignmentOfFile;


        @Lob
        private byte[] data;

        public TeacherFileDB() {
        }

        public TeacherFileDB(String name, String type, byte[] data) {
            this.name = name;
            this.type = type;
            this.data = data;
        }



    public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public byte[] getData() {
            return data;
        }

        public void setData(byte[] data) {
            this.data = data;
        }

    public Assignment getAssignmentOfFile() {
        return assignmentOfFile;
    }

    public void setAssignmentOfFile(Assignment assignmentOfFile) {
        this.assignmentOfFile = assignmentOfFile;
    }
}