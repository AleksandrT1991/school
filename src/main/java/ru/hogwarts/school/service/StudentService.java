package ru.hogwarts.school.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;


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

    public Collection<Student> getAllStudents() {
        logger.info("Metod \"StudentService.getAllStudents()\" was called");
        return studentRepository.findAll();
    }

    public Collection<Student> findByBetween(int min, int max) {
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

    public Collection<Student> getLastStudent() {
        logger.info("Metod \"StudentService.getLastStudent()\" was called");
        return studentRepository.getLastStudent();

    }

    public Collection<String> getFirstLetterName(char ch) {
        logger.info("Metod \"StudentService.getFirstLetterName()\" was called");
        Collection<Student> collection = studentRepository.findAll();
        String letter = (ch + "").toUpperCase();
        return collection.stream().map(Student::getName).map(String::toUpperCase).
                filter(s -> s.startsWith(letter)).sorted().collect(Collectors.toList());
    }

    public double getAverageAge() {
        Collection<Student> collection = studentRepository.findAll();
        return collection.stream().mapToDouble(Student::getAge).average().orElseThrow();
    }

    public String outPrintLn() {
        List<Student> list = studentRepository.getStudentById();
        printHello(list.get(0));
        printHello(list.get(1));

        Thread thread = new Thread(() -> {
            printHello(list.get(2));
            printHello(list.get(3));
        });
        thread.start();
        new Thread(() -> {
            printHello(list.get(4));
            printHello(list.get(5));
        }).start();

        return " ";
    }

    public void printHello(Student student) {
        try {
            System.out.println(student);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Прервано");
        }

    }


    public String outPrintLn1() {
        List<Student> list = studentRepository.getStudentById();
        printHello1(list.get(0));
        printHello1(list.get(1));
        new Thread(() -> {
            printHello1(list.get(2));
            printHello1(list.get(3));
        }).start();
        new Thread(() -> {
            printHello1(list.get(4));
            printHello1(list.get(5));
        }).start();



        return " ";

    }

    public void printHello1(Student student) {
        try {
                synchronized (StudentService.class) {
                    System.out.println(student);
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e){
                System.out.println("Прервано");
            }
    }
}