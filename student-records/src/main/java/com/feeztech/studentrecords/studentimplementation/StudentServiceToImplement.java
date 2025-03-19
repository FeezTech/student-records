package com.feeztech.studentrecords.services;

import com.feeztech.studentrecords.model.StudentEntity;
import org.springframework.data.domain.Page;

import java.util.List;


public interface StudentServiceToImplement {

    List<StudentEntity> getAllStudentRecord();
    StudentEntity getStudentById(int id);
    StudentEntity addStudentDetails(StudentEntity studentEntity);
    StudentEntity updateStudentDetails(int id, StudentEntity studentEntity);
    void deleteStudentDetailsById(int id);

    // FOR SORTING AND PAGINATION
    List<StudentEntity> getAllStudentRecordsBySorting(String field);
    Page<StudentEntity> getAllStudentRecordsByPagination(int offset, int pageSize);

    Page<StudentEntity> getAllStudentRecordsByPaginationAndSorting(int offset, int pageSize, String field);
}
