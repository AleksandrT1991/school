package ru.hogwarts.school.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public Faculty addFaculty(Faculty faculty) {
        logger.info("Metod \"FacultyService.addFaculty()\" was called");
        return facultyRepository.save(faculty);
    }


    public Faculty findFaculty(long id) {
        logger.info("Metod \"FacultyService.findFaculty()\" was called");
        return facultyRepository.findById(id).orElse(null);
    }
    public Faculty editFaculty(Faculty faculty) {
        logger.info("Metod \"FacultyService.editFaculty()\" was called");
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
        logger.info("Metod \"FacultyService.deleteFaculty()\" was called");
        facultyRepository.deleteById(id);
    }
    public Collection <Faculty> getAllFaculties() {
        logger.info("Metod \"FacultyService.getAllFaculties()\" was called");
        return facultyRepository.findAll();
    }
    public Collection<Faculty> findByName(String name, String color) {
        logger.info("Metod \"FacultyService.findByName()\" was called");
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }
//    public Collection<Student> getStudentFaculty (long id) {
//        Optional<Faculty> optional = facultyRepository.findById(id);
//        if (!optional.isPresent()) {
//            return null;
//        }
//        Faculty fromDb = optional.get();
//        return fromDb.getStudent();
//    }
    public String longNameFaculty () {
        logger.info("Metod \"FacultyService.longNameFaculty()\" was called");
        Comparator<Faculty> compareName = new Comparator<Faculty>() {
            @Override
            public int compare(Faculty o1, Faculty o2) {
                return o1.getName().length() - o2.getName().length();
            }
        };
        List<Faculty> list = facultyRepository.findAll();
        return list.stream().max(compareName).orElse(null).getName();

    }

}
