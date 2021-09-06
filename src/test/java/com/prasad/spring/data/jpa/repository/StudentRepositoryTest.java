package com.prasad.spring.data.jpa.repository;

import com.prasad.spring.data.jpa.entity.Guardian;
import com.prasad.spring.data.jpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){

        Student student = Student.builder().firstName("durga prasad")
                .lastName("Kotipalli")
                .emailId("prasadkse@gmail.com")
                //.guardianEmail("satishkotipalli@gmail.com")
                //.guardianMobile("9398671944")
                //.guardianName("satyanarayana")
                .build();
        studentRepository.save(student);

    }

    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder()
                .email("prasadkse@gmail.com")
                .mobile("9652126899")
                .name("prasad")
                .build();
        Student student = Student.builder()
                .emailId("aakshith@gmail.com")
                .firstName("Aakshith")
                .lastName("Kotipalli")
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }

    @Test
    public void printAllStudents(){
        List<Student> list = studentRepository.findAll();
        System.out.println("Student List ::"+list);
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> list = studentRepository.findByFirstName("Aakshith");
        System.out.println("Student List ::"+list);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> list = studentRepository.findByFirstNameContaining("prasad");
        System.out.println("Student List ::"+list);
    }

    @Test
    public void printStudentByLastNameNotNull(){
        List<Student> list = studentRepository.findByLastNameNotNull();
        System.out.println("student List :"+list);
    }

    @Test
    public void printStudentByGuardianName(){
        List<Student> list = studentRepository.findByGuardianName("prasad");
        System.out.println("student list : "+list);
    }

    @Test
    public void printStudentByFistNameAndLastName(){
        Student student = studentRepository.findByFirstNameAndLastName("Aakshith", "Kotipalli");
        System.out.println("student = " + student);
    }

    @Test
    public void printStudentByEmailId(){
        Student student = studentRepository.getStudentByEmailAddress("prasadkse@gmail.com");
        System.out.println("student = " + student);
    }

    @Test
    public void printStudentByEmailIdNative(){
        Student student = studentRepository.getStudentByEmailAddressNative("aakshith@gmail.com");
        System.out.println("student = " + student);
    }
    
    @Test
    public void printStudentByEmailIdNativeNamedParams(){
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("aakshith@gmail.com");
        System.out.println("student = " + student);
    }

    @Test
    public void updateStudentFirstNameByEmailAddressTest(){
        studentRepository.updateStudentFirstNameByEmailAddress("Durga Prasad", "prasadkse@gmail.com");
    }
}
