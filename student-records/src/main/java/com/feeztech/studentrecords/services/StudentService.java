package com.feeztech.studentrecords.services;

import com.feeztech.studentrecords.model.StudentEntity;
import com.feeztech.studentrecords.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private StudentRepository studentRepository;

    public List<StudentEntity> getAllStudentRecord() {
        return studentRepository.findAll();
    }


    public StudentEntity getStudentById(int id) {

        return studentRepository.findById(id).orElse(null);
    }


    public StudentEntity addStudentDetails(StudentEntity studentEntity) {

        return studentRepository.save(studentEntity);
    }


    public StudentEntity updateStudentDetails(int id, StudentEntity studentEntity) {

        return studentRepository.save(studentEntity);
    }

    public void deleteStudentDetailsById(int id) {

        studentRepository.deleteById(id);
    }
}
