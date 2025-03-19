package com.feeztech.studentrecords.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "StudentRecords")
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        private long id;

        @NotBlank(message = "Name cannot be blank")
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        @Column(name = "NAME")
        private String name;


        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @Column(name = "EMAIL", unique = true)
        private String email;

        @NotNull(message = "Age is required")
        @Column(name = "AGE")
        private int age;

        @NotBlank(message = "Course cannot be blank")
        @Column(name = "COURSE")
        private String course;

}
