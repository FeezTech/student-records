package com.feeztech.studentrecords.controller;

import com.feeztech.studentrecords.services.StudentService;
import com.feeztech.studentrecords.model.StudentEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping("/")
@Tag(name = "Student API", description = "Operations related to student records")
public class StudentController {


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    private StudentService studentService;

    @GetMapping("/students")
    @Operation(
            summary = "Get All Students",
            description = "Fetch the list of all student records."
    )
    public ResponseEntity <List <StudentEntity>> getStudentRecord(@RequestParam Optional<Integer> page, @RequestParam Optional<String> sortBy) {
        return new ResponseEntity<>(studentService.getAllStudentRecord(), HttpStatusCode.valueOf(HttpServletResponse.SC_OK));
    }

    @GetMapping("/students/{id}")
    @Operation(summary = "Get Student by ID", description = "Fetch a student record by providing their ID.")
    public ResponseEntity  <StudentEntity> getStudentById(@PathVariable int id) {

        StudentEntity studentEntity = studentService.getStudentById(id);

        if (studentEntity != null) {
            return new ResponseEntity<>(studentEntity, HttpStatusCode.valueOf(HttpServletResponse.SC_OK));
        }
        else
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(HttpServletResponse.SC_FOUND));
    }

    @PostMapping ("/student")
    @Operation(summary = "Add Student", description = "Add a new student record to the system.")
    public ResponseEntity <?> addStudentDetails(@RequestBody StudentEntity studentEntity) {

        try {
            StudentEntity saveDetails = studentService.addStudentDetails(studentEntity);
            return new ResponseEntity<>(saveDetails, HttpStatusCode.valueOf(HttpServletResponse.SC_CREATED));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));
        }
    }

    @PutMapping("/students/{id}")
    @Operation(
            summary = "Update Student",
            description = "Update a student's details by providing their ID."
    )
    public ResponseEntity <String> updateStudentDetails (@PathVariable int id, @RequestBody StudentEntity studentEntity) {
        StudentEntity updateDetails = null;
        try {
            updateDetails = studentService.updateStudentDetails(id, studentEntity);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(HttpServletResponse.SC_BAD_REQUEST));
        }

        if (updateDetails != null)
            return new ResponseEntity<>("Student Details updated successfully", HttpStatusCode.valueOf(HttpServletResponse.SC_OK));
        else
            return new ResponseEntity<>("Update Failed", HttpStatusCode.valueOf(HttpServletResponse.SC_BAD_REQUEST));
    }

    @DeleteMapping("/students/{id}")
    @Operation(
            summary = "Delete Student",
            description = "Delete a student record by providing their ID."
    )
    public ResponseEntity  <String> deleteStudentDetailsById(@PathVariable int id) {

        StudentEntity studentEntity = studentService.getStudentById(id);

        if (studentEntity != null) {
            studentService.deleteStudentDetailsById(id);
            return new ResponseEntity<> ("Student Details Deleted Successfully", HttpStatusCode.valueOf(HttpServletResponse.SC_OK));
        }
        else
            return new ResponseEntity<>("Fail to Delete", HttpStatusCode.valueOf(HttpServletResponse.SC_NOT_FOUND));
    }


}
