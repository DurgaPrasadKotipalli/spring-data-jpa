package com.prasad.spring.data.jpa.repository;

import com.prasad.spring.data.jpa.entity.Course;
import com.prasad.spring.data.jpa.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository repository;

    @Test
    public void saveCourseMaterial(){
        Course course = Course.builder()
                .title("DSA")
                .credit(6)
                .build();
        CourseMaterial courseMaterial = CourseMaterial.builder()
                .course(course)
                .url("www.google.com")
                .build();
        repository.save(courseMaterial);

    }

    @Test
    public void printCourseMaterials(){
        List<CourseMaterial> list = repository.findAll();
        System.out.println("list = " + list);
    }
}
