package com.ira.quizplatform.repository;

import com.ira.quizplatform.entity.Group;
import com.ira.quizplatform.entity.Quiz;
import com.ira.quizplatform.entity.Result;
import com.ira.quizplatform.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Oleksandr_Tkachov on 20.05.2018.
 */
public interface ResultRepo extends JpaRepository<Result, Long> {

    List<Result> findByStudent(Student student);

    List<Result> findByStudent_Group(Group group);

    List<Result> findByStudentAndQuiz(Student student, Quiz quiz);

    @Query("select distinct r.quiz from result r where r.student.group.id = :groupId")
    List<Quiz> uniqueQuizzes(@Param("groupId")Long groupId);
}
