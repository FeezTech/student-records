package com.feeztech.studentrecords.services;

import com.feeztech.studentrecords.model.StudentEntity;
import com.feeztech.studentrecords.repositories.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements StudentServiceToImplement {


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private final StudentRepository studentRepository;

    @Override
    public List<StudentEntity> getAllStudentRecord() {
        return studentRepository.findAll();
    }

    @Override
    public StudentEntity getStudentById(int id) {

        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public StudentEntity addStudentDetails(StudentEntity studentEntity) {

        return studentRepository.save(studentEntity);
    }

    @Override
    public StudentEntity updateStudentDetails(int id, StudentEntity studentEntity) {

        return studentRepository.save(studentEntity);
    }

    @Override
    public void deleteStudentDetailsById(int id) {

        studentRepository.deleteById(id);
    }

    //FOR SORTING

    @Override
    public List<StudentEntity> getAllStudentRecordsBySorting(String field) {
        return studentRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    //FOR PAGINATION
    @Override
    public Page<StudentEntity> getAllStudentRecordsByPagination(int offset, int pageSize) {
        Page<StudentEntity> studentEntities = studentRepository.findAll(PageRequest.of(offset, pageSize));
        return studentEntities;
    }

    @Override
    public Page<StudentEntity> getAllStudentRecordsByPaginationAndSorting(int offset, int pageSize, String field) {
        Page<StudentEntity> studentEntities = studentRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field
        )));
        return studentEntities;
    }

}
