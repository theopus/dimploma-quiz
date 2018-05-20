package com.ira.quizplatform.repository;

import com.ira.quizplatform.entity.Group;
import com.ira.quizplatform.entity.Quiz;
import com.ira.quizplatform.entity.Result;
import com.ira.quizplatform.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Oleksandr_Tkachov on 20.05.2018.
 */
public interface ResultRepo extends JpaRepository<Result, Long> {

    List<Result> findByStudent(Student student);

    List<Result> findByStudent_Group(Group group);

    List<Result> findByStudentAndQuiz(Student student, Quiz quiz);
}
