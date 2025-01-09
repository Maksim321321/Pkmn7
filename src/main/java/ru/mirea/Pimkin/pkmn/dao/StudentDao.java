package ru.mirea.Pimkin.pkmn.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.mirea.Pimkin.pkmn.entity.StudentEntity;
import ru.mirea.Pimkin.pkmn.repository.StudentEntityRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentDao {

    private final StudentEntityRepository studentRepository;

    public List<StudentEntity> getStudentsByGroup(String group) {
        return studentRepository.getStudentByGroup(group);
    }

    public boolean studentExists(StudentEntity student) {
        return studentRepository.existsByFirstNameAndSurNameAndFamilyName(student.getFirstName(),
                student.getSurName(),
                student.getFamilyName());
    }

    public List<StudentEntity> getStudentsByFIO(StudentEntity student) {
        return studentRepository.findByFirstNameAndSurNameAndFamilyName(student.getFirstName(), student.getSurName(), student.getFamilyName());
    }

    // Сохранить студента
    public StudentEntity saveStudent(StudentEntity student) {
        return studentRepository.saveAndFlush(student);
    }

    public List<StudentEntity> findAllStudent(){
        return studentRepository.findAll();
    }


}
