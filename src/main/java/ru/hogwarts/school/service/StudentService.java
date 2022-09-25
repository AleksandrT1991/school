package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.Optional;


@Service
public class StudentService {

    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;

    }


    public Student addStudent(Student student) {
       return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student editStudent(Student student) {
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
        studentRepository.deleteById(id);
    }

    public Collection <Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Collection <Student> findByBetween (int min, int max) {
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

}
