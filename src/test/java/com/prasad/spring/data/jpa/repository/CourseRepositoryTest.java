package com.prasad.spring.data.jpa.repository;

import com.prasad.spring.data.jpa.entity.Course;
import com.prasad.spring.data.jpa.entity.Student;
import com.prasad.spring.data.jpa.entity.Teacher;
import org.hibernate.usertype.CompositeUserType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;

@SpringBootTest
public class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses(){
        List<Course> courses = courseRepository.findAll();
        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithTeacher(){

        Teacher teacher = Teacher.builder()
                .firstName("Vijender")
                .lastName("devakari")
                .build();

        Course course = Course.builder()
                .title("Microservices")
                .teacher(teacher)
                .credit(5)
                .build();

        courseRepository.save(course);

    }

    @Test
    public void findAllPagination(){
        Pageable fistPageWithThreeRecords = PageRequest.of(0,3);
        Pageable secondPageWithTwoRecords = PageRequest.of(1, 2);
        List<Course> courses = courseRepository.findAll(fistPageWithThreeRecords).getContent();
        long totalElements = courseRepository.findAll(fistPageWithThreeRecords)
                        .getTotalElements();
        long totalPages = courseRepository.findAll(fistPageWithThreeRecords)
                        .getTotalPages();
        System.out.println("totalPages = " + totalPages);
        System.out.println("totalElements = " + totalElements);
        System.out.println("courses = " + courses);
    }

    @Test
    public void findAllWithSorting(){
        Pageable sortByTitle =
                PageRequest.of(0,7, Sort.by("title"));

        Pageable sortByCreditDesc =
                PageRequest.of(0,2, Sort.by("credit").descending() );

        Pageable sortByTitleandCredit =
                PageRequest.of(0,2, Sort.by("title").descending().and(Sort.by("credit")));

        List<Course> courses =
                courseRepository.findAll(sortByTitle).getContent();
        System.out.println("courses = " + courses);

    }

    @Test
    public void printFindByTitleContaining(){
        Pageable firstPageTenRecords = PageRequest.of(0, 10);
        List<Course> courses =
                courseRepository.findByTitleContaining("D", firstPageTenRecords).getContent();
        System.out.println("courses = " + courses);;
    }

    @Test
    public void saveCourseWithStudentAndTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Bheemla")
                .lastName("Nayak")
                .build();
        Student student = Student.builder()
                .firstName("Shilpa")
                .lastName("Shetty")
                .emailId("shilpa@gmail.com")
                .build();
        Course course = Course.builder()
                .title("CDN")
                .credit(12)
                .teacher(teacher)
                .build();

        course.addStudent(student);

        courseRepository.save(course);
    }
}
