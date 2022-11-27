package com.se.smsbackend.Controller;

import com.se.smsbackend.Entity.FileDB;
import com.se.smsbackend.Entity.StudentFileDB;
import com.se.smsbackend.Repository.AssignmentRepo;
import com.se.smsbackend.Service.FileStorageService;
import com.se.smsbackend.Service.StudentFileDBService;
import com.se.smsbackend.Site.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/")
public class FileController {

    @Autowired
    private FileStorageService storageService;
    @Autowired
    AssignmentRepo assignmentRepo;

    @Autowired
    StudentFileDBService studentFileDBService;


    @PreAuthorize("hasRole('Teacher')")
    @RequestMapping(value = "/files/upload/{id}", method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseMessage> uploadFile(@PathVariable int id,@RequestParam("file") MultipartFile file ) {
        String message = "";
        try {            storageService.store(file,id);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PreAuthorize("hasAnyRole('Teacher','Student')")
    @GetMapping("/files/download/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        FileDB fileDB = storageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }
/*    @PreAuthorize("hasRole('Teacher')")
    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }*/

    @PreAuthorize("hasRole('Student')")
    @RequestMapping(value = "/files/student/upload/{id}/{username}", method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseMessage> StudentUploadFile(@PathVariable String username,@PathVariable int id,@RequestParam("file") MultipartFile file ) {
        String message = "";
        try {           studentFileDBService.storeFile(file,id,username);
            System.out.println("file name = "+file.getOriginalFilename());

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
    @PreAuthorize("hasAnyRole('Teacher','Student')")
    @GetMapping("/files/student/download/{id}")
    public ResponseEntity<byte[]> getStudentFile(@PathVariable String id) {
        StudentFileDB studentFileDB = studentFileDBService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + studentFileDB.getName() + "\"")
                .body(studentFileDB.getData());
    }
}
