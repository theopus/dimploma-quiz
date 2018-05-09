package com.ira.quizplatform.repository;

import com.ira.quizplatform.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<Teacher, Long> {

    Teacher findByName(String name);
}
