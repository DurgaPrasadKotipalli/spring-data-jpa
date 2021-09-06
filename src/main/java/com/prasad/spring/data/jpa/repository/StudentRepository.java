package com.prasad.spring.data.jpa.repository;

import com.prasad.spring.data.jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Long> {

    public List<Student> findByFirstName(String firstName);

    public List<Student> findByFirstNameContaining(String firstName);

    public List<Student> findByLastNameNotNull();

    public List<Student> findByGuardianName(String guardianName);

    public Student findByFirstNameAndLastName(String firstName, String lastName);

    //JPQL
    @Query("select s from Student s where s.emailId = ?1")
    public Student getStudentByEmailAddress(String emailId);


    //Native
    @Query(
            value = "SELECT * FROM tbl_student s where s.email_address = ?1",
            nativeQuery = true
    )
    public Student getStudentByEmailAddressNative(String emailId);

    //Native query by Named params
    @Query(
            value = "SELECT * FROM tbl_student s where s.email_address = :email",
            nativeQuery = true
    )
    public Student getStudentByEmailAddressNativeNamedParam(@Param("email") String emailId);

    @Modifying
    @Transactional
    @Query(
            value = "update tbl_student set first_name=?1 where email_address = ?2",
            nativeQuery = true
    )
    public int updateStudentFirstNameByEmailAddress(String firstName, String emailId);

}
