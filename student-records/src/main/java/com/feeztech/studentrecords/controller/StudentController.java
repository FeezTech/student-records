package com.feeztech.studentrecords.controller;


import com.feeztech.studentrecords.model.StudentEntity;
import com.feeztech.studentrecords.services.StudentServiceToImplement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/")
@Tag(name = "Student API", description = "Operations related to student records")
public class StudentController {


    public StudentController(StudentServiceToImplement studentServiceToImplement) {
        this.studentServiceToImplement = studentServiceToImplement;
    }

    private final StudentServiceToImplement studentServiceToImplement;


    @GetMapping("/students")
    @Operation(
            summary = "Get All Students",
            description = "Fetch the list of all student records."
    )
    public ResponseEntity <List <StudentEntity>> getStudentRecord() {

        return new ResponseEntity<>(studentServiceToImplement.getAllStudentRecord(), HttpStatusCode.valueOf(HttpServletResponse.SC_OK));
    }

    @GetMapping("/students/{id}")
    @Operation(summary = "Get Student by ID", description = "Fetch a student record by providing their ID.")
    public ResponseEntity  <StudentEntity> getStudentById(@PathVariable int id) {

        StudentEntity studentEntity = studentServiceToImplement.getStudentById(id);

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
            StudentEntity saveDetails = studentServiceToImplement.addStudentDetails(studentEntity);
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
            updateDetails = studentServiceToImplement.updateStudentDetails(id, studentEntity);
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

        StudentEntity studentEntity = studentServiceToImplement.getStudentById(id);

        if (studentEntity != null) {
            studentServiceToImplement.deleteStudentDetailsById(id);
            return new ResponseEntity<> ("Student Details Deleted Successfully", HttpStatusCode.valueOf(HttpServletResponse.SC_OK));
        }
        else
            return new ResponseEntity<>("Fail to Delete", HttpStatusCode.valueOf(HttpServletResponse.SC_NOT_FOUND));
    }

    @GetMapping("/students/sort/{field}")
    public ResponseEntity <List<StudentEntity>> getStudentRecordsBySorting(@PathVariable String field) {
        List<StudentEntity> allStudentRecords = studentServiceToImplement.getAllStudentRecordsBySorting(field);
        return new ResponseEntity<>(allStudentRecords, HttpStatusCode.valueOf(HttpServletResponse.SC_ACCEPTED));
    }

    @GetMapping("/students/sort/{offset}/{pageSize}")
    public ResponseEntity<Page<StudentEntity>> getStudentRecordsByPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<StudentEntity> studentRecordsByPagination = studentServiceToImplement.getAllStudentRecordsByPagination(offset, pageSize);
        return new ResponseEntity<>(studentRecordsByPagination, HttpStatusCode.valueOf(HttpServletResponse.SC_CREATED));
    }

    @GetMapping("/students/sortandpagination/{offset}/{pageSize}/{field}")
    public ResponseEntity<Page<StudentEntity>> getStudentRecordsByPaginationAndSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
        Page<StudentEntity> studentRecordsByPagination = studentServiceToImplement.getAllStudentRecordsByPaginationAndSorting(offset, pageSize, field);
        return new ResponseEntity<>(studentRecordsByPagination, HttpStatusCode.valueOf(HttpServletResponse.SC_CREATED));
    }

}
