package com.se.smsbackend.Service;

import com.se.smsbackend.Entity.Assignment;
import com.se.smsbackend.Entity.FileDB;
import com.se.smsbackend.Repository.AssignmentRepo;
import com.se.smsbackend.Repository.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.stream.Stream;

@Service
@Transactional
public class FileStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;
    @Autowired
    AssignmentRepo assignmentRepo;

    public FileDB store(MultipartFile file, int id) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
        Assignment assignment = assignmentRepo.findById(id);
        System.out.println(id+"printing assignement for file save"+assignment);
fileDB.setAssignmentOfFile(assignment);
        return fileDBRepository.save(fileDB);
    }

    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

}