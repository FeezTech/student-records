package com.feeztech.studentrecords.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @NotBlank(message = "Name cannot be blank")
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        private String name;


        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @Column(unique = true)
        private String email;

        @NotNull(message = "Age is required")
        private int age;

        @NotBlank(message = "Course cannot be blank")
        private String course;

}
