package ru.hogwarts.school.service;


import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;
import java.util.Optional;


@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }


    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).orElse(null);
    }
    public Faculty editFaculty(Faculty faculty) {
        Optional <Faculty> optional = facultyRepository.findById(faculty.getId());
        if (!optional.isPresent()) {
            return null;
        }
        Faculty fromDb = optional.get();
        fromDb.setName(faculty.getName());
        fromDb.setColor(faculty.getColor());
        return facultyRepository.save(fromDb);
    }
    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }
    public Collection <Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }
    public Collection<Faculty> findByName(String name, String color) {
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }
    public Collection<Student> getStudentFaculty (long id) {
        Optional<Faculty> optional = facultyRepository.findById(id);
        if (!optional.isPresent()) {
            return null;
        }
        Faculty fromDb = optional.get();
        return fromDb.getStudent();
    }
}
