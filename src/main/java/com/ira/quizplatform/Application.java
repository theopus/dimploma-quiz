package com.ira.quizplatform;

import com.ira.quizplatform.entity.Group;
import com.ira.quizplatform.entity.Student;
import com.ira.quizplatform.entity.Teacher;
import com.ira.quizplatform.repository.GroupRepo;
import com.ira.quizplatform.repository.StudentRepo;
import com.ira.quizplatform.repository.TeacherRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(TeacherRepo teacherRepo, StudentRepo repo, GroupRepo groupRepo) {
        return (args) -> {

            Group group = groupRepo.save(new Group("Иуст-41"));
            repo.save(new Student("ira", "ira", group));
            teacherRepo.save(new Teacher("gorda", "gorda"));
        };
    }

}
