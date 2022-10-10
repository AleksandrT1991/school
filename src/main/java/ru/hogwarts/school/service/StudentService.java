package ru.hogwarts.school.service;


import org.apache.el.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;


@Service
//@Profile("Production")
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);


    public Student addStudent(Student student) {
        logger.info("Metod \"StudentService.addStudent()\" was called");
       return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("Metod \"StudentService.findStudent()\" was called");
        return studentRepository.findById(id).orElse(null);
    }

    public Student editStudent(Student student) {
        logger.info("Metod \"StudentService.editStudent()\" was called");
        Optional<Student> optional = studentRepository.findById(student.getId());
        if (!optional.isPresent()) {
            return null;
        }
        Student fromDb = optional.get();
        fromDb.setName(student.getName());
        fromDb.setAge(student.getAge());
        return studentRepository.save(fromDb);
    }

    public void deleteStudent(long id) {
        logger.info("Metod \"StudentService.deleteStudent()\" was called");
        studentRepository.deleteById(id);
    }

    public Collection <Student> getAllStudents() {
        logger.info("Metod \"StudentService.getAllStudents()\" was called");
        return studentRepository.findAll();
    }

    public Collection <Student> findByBetween (int min, int max) {
        logger.info("Metod \"StudentService.findByBetween()\" was called");
        return studentRepository.findByAgeBetween(min, max);
    }

    //    public Faculty getFacultyStudent (long id) {
//        Optional<Student> optional = studentRepository.findById(id);
//        if (!optional.isPresent()) {
//            return null;
//        }
//        Student fromDb = optional.get();
//        return fromDb.getFaculty();
//    }
    public int getAmountStudent() {
        logger.info("Metod \"StudentService.getAmountStudent()\" was called");
        return studentRepository.getAmountStudent();
    }
    public double getAverageAgeStudent() {
        logger.info("Metod \"StudentService.getAverageAgeStudent()\" was called");
        return studentRepository.getAverageAgeStudent();
    }
    public Collection <Student> getLastStudent () {
        logger.info("Metod \"StudentService.getLastStudent()\" was called");
        return studentRepository.getLastStudent();

    }
    public Collection <Student> getFirstLetterName () {
        logger.info("Metod \"StudentService.getFirstLetterName()\" was called");
        Collection<Student> collection = studentRepository.findAll();
        Comparator<Student> comparatorName = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        return  collection.stream().map(Student::getName).map(String::toUpperCase).sorted(comparatorName);
    }

    public double getAverageAge() {
        Collection<Student> collection = studentRepository.findAll();
       double a = (double) collection.stream().mapToDouble(s -> s.getAge()).sum();
       return a / collection.size();




    }

}