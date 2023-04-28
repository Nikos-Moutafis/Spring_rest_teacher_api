package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.repository.TeacherRepository;
import gr.aueb.cf.schoolapp.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements ITeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Transactional
    @Override
    public Teacher insertTeacher(TeacherDTO teacherDTO)  {
        return teacherRepository.save(convertToTeacher(teacherDTO));
    }

    @Transactional
    @Override
    public Teacher updateTeacher(TeacherDTO teacherDTO) throws EntityNotFoundException {
        //Optional<Teacher> teacher = teacherRepository.findById(teacherDTO.getId());
        //if (teacher.isEmpty()) throw new EntityNotFoundException(Teacher.class, teacherDTO.getId());
        //return teacherRepository.save(convertToTeacher(teacher, teacherDTO));
        Teacher teacher = teacherRepository.findTeacherById(teacherDTO.getId());
        if (teacher == null) throw new EntityNotFoundException(Teacher.class, teacherDTO.getId());
        return teacherRepository.save(convertToTeacher(teacherDTO));
    }

    @Transactional
    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public List<Teacher> getTeachersByLastname(String lastname) throws EntityNotFoundException {
        List<Teacher> teachers;
        teachers = teacherRepository.findByLastnameStartingWith(lastname);
        if (teachers.size() == 0) throw new EntityNotFoundException(Teacher.class, 0L);
        return teachers;
    }

    @Override
    public Teacher getTeacherById(Long id) throws EntityNotFoundException {
        Optional<Teacher> teacher;
        teacher = teacherRepository.findById(id);
        if (teacher.isEmpty()) throw new EntityNotFoundException(Teacher.class, 0L);
        return teacher.get();
    }

    private static Teacher convertToTeacher(TeacherDTO dto) {
        return new Teacher(dto.getId(), dto.getFirstname(), dto.getLastname());
    }
}
