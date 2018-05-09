package com.ira.quizplatform.repository;

import com.ira.quizplatform.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<Quiz, Long> {
}
