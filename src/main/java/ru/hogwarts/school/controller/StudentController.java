package ru.hogwarts.school.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity <Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping

    public ResponseEntity<Collection<Student>> findStudents (@RequestParam  (required = false) int min,
                                                             @RequestParam (required = false) int max) {
        if (min != 0 && max!= 0 ) {
        return ResponseEntity.ok((studentService.findByBetween(min, max)));
        }
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping

    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping
    public ResponseEntity <Student> editStudent (@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/amountStudent")
    public int getAmountStudent() {
        return studentService.getAmountStudent();
    }
    @GetMapping("/averageAge")
    public double getAverageAgeStudent() {
        return studentService.getAverageAgeStudent();
    }
    @GetMapping("/lastStudent")
    public Collection <Student> getLastStudent() {
        return studentService.getLastStudent();
    }
    @GetMapping("getFirstLetterName")
    public Collection <String> getFirstLetterName(@RequestParam(name = "firstletter") char ch) {
        return studentService.getFirstLetterName(ch);
    }
    @GetMapping("/getAverageAge() ")
    public double getAverageAge() {
        return studentService.getAverageAge();
    }

}