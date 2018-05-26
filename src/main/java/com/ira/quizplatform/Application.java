package com.ira.quizplatform;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ira.quizplatform.entity.*;
import com.ira.quizplatform.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.io.IOException;

@SpringBootApplication
public class Application {

//    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(TeacherRepo teacherRepo, StudentRepo repo, GroupRepo groupRepo, ObjectMapper objectMapper, QuizRepo quizRepo, ResultRepo resultRepo, EntityManagerFactory entityManager) {
        return (String... args) -> {
            saveInitData(teacherRepo, repo, groupRepo, objectMapper, quizRepo, resultRepo, entityManager);
        };
    }

    @Transactional
    @javax.transaction.Transactional
    public void saveInitData(TeacherRepo teacherRepo, StudentRepo repo, GroupRepo groupRepo, ObjectMapper objectMapper, QuizRepo quizRepo, ResultRepo resultRepo, EntityManagerFactory entityManager) throws IOException {

        Quiz quiz = objectMapper.readValue("{\n" +
                "    \"id\": null,\n" +
                "    \"title\":\"213\",\n" +
                "    \"completionTime\": 3,\n" +
                "    \"list\": [\n" +
                "        {\n" +
                "            \"id\": null,\n" +
                "            \"title\":\"First\",\n" +
                "            \"description\":\"3123\",\n" +
                "            \"weitgh\": 1.0,\n" +
                "            \"all\": [\n" +
                "                {\n" +
                "                    \"value\":\"1\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\":\"2\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"correct\": [\n" +
                "                {\n" +
                "                    \"value\":\"1\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\":\"2\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": null,\n" +
                "            \"title\":\"Second\",\n" +
                "            \"description\":\"3123\",\n" +
                "            \"weitgh\": 1.0,\n" +
                "            \"all\": [\n" +
                "                {\n" +
                "                    \"value\":\"1\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\":\"2\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"correct\": [\n" +
                "                {\n" +
                "                    \"value\":\"1\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": null,\n" +
                "            \"title\":\"Third\",\n" +
                "            \"description\":\"3123\",\n" +
                "            \"weitgh\": 1.0,\n" +
                "            \"all\": [\n" +
                "                {\n" +
                "                    \"value\":\"1\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\":\"2\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"correct\": [\n" +
                "                {\n" +
                "                    \"value\":\"1\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\":\"2\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}", Quiz.class);


        Quiz quiz2 = objectMapper.readValue("{\n" +
                "    \"id\": null,\n" +
                "    \"title\":\"Maths\",\n" +
                "    \"completionTime\": 3,\n" +
                "    \"list\": [\n" +
                "        {\n" +
                "            \"id\": null,\n" +
                "            \"title\":\"213\",\n" +
                "            \"description\":\"3123\",\n" +
                "            \"weitgh\": 1.0,\n" +
                "            \"all\": [\n" +
                "                {\n" +
                "                    \"value\":\"213\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\":\"213\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"correct\": [\n" +
                "                {\n" +
                "                    \"value\":\"213\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\":\"213\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}", Quiz.class);

        Group group = groupRepo.save(new Group("Иуст-41"));
        Quiz quizSaved = quizRepo.save(quiz);
        Quiz quizSaved2 = quizRepo.save(quiz2);
        Student student = repo.save(new Student("ira", "ira", group));
        teacherRepo.save(new Teacher("gorda", "gorda"));

        Result result = new Result();
        result.setBalance(0);
        result.setPassed(false);
        result.setStudent(student);
        result.setQuiz(quizSaved);
        resultRepo.save(result);

        Result result1 = new Result();
        result1.setBalance(0);
        result1.setPassed(false);
        result1.setStudent(student);
        result1.setQuiz(quizSaved2);
        resultRepo.save(result1);


    }

}
