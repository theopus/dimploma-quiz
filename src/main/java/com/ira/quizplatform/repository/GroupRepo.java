package com.ira.quizplatform.repository;

import com.ira.quizplatform.entity.Group;
import com.ira.quizplatform.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepo extends JpaRepository<Group, Long> {
}
