package com.ira.quizplatform.repository;

import com.ira.quizplatform.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long> {

    void deleteByName(String name);
    Student findByName(String name);
}
