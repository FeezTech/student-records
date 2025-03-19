package com.feeztech.studentrecords.repositories;

import com.feeztech.studentrecords.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository <StudentEntity, Integer> {

}
