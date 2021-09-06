package com.prasad.spring.data.jpa.repository;

import com.prasad.spring.data.jpa.entity.Course;
import com.prasad.spring.data.jpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;

import java.util.List;

@SpringBootTest
public class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher(){
        Course coreJava = Course.builder()
                .title("coreJava")
                .credit(8)
                .build();

        Course awsCloud = Course.builder()
                .title("AWS")
                .credit(7)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("java")
                .lastName("techie")
                //.courses(List.of(coreJava, awsCloud))
                .build();

        teacherRepository.save(teacher);

    }
}
