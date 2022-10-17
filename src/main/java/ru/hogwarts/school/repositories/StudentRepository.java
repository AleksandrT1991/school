package ru.hogwarts.school.repositories;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection <Student> findByAgeBetween(int min, int max);

    @Query(value= "SELECT count (id) FROM student", nativeQuery = true )
     int getAmountStudent();

    @Query(value= "SELECT avg (age) FROM student", nativeQuery = true )
    int getAverageAgeStudent();

    @Query(value= "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true )
    Collection <Student> getLastStudent();

    @Query(value= "SELECT * FROM student ORDER BY id" , nativeQuery = true )
    List <Student> getStudentById();


}
