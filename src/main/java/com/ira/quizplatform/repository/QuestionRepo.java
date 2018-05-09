package com.ira.quizplatform.repository;

import com.ira.quizplatform.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepo extends JpaRepository<Question, Long> {
}
