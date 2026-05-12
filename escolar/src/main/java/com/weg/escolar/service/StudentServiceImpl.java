package com.weg.escolar.service;

import com.weg.escolar.dto.GradeResponseDto;
import com.weg.escolar.dto.StudentRequestDto;
import com.weg.escolar.dto.StudentResponseDto;
import com.weg.escolar.mapper.StudentMapper;
import com.weg.escolar.model.Student;
import com.weg.escolar.repo.StudentRepo;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentMapper mapper;
    private final StudentRepo repo;

    public StudentServiceImpl(StudentMapper mapper, StudentRepo repo) {
        this.mapper = mapper;
        this.repo = repo;
    }

    @Override
    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto) throws SQLException {
        Student student = mapper.toEntity(studentRequestDto);

        return mapper.toResponse(repo.createStudent(student));
    }

    @Override
    public StudentResponseDto getStudent(Long id) throws SQLException {
        return mapper.toResponse(repo.getStudent(id)
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado")));
    }

    @Override
    public List<StudentResponseDto> getAllStudents() throws SQLException {
        return repo.getAllStudents().stream()
                .map(student -> new StudentResponseDto(student.getNome(),
                                                            student.getEmail(),
                                                            student.getMatricula(),
                                                            student.getDataNascimento()))
                .toList();
    }

    @Override
    public StudentResponseDto updateStudent(StudentRequestDto studentRequestDto, Long id) throws SQLException {
        Student student = mapper.toEntity(studentRequestDto);
        student.setId(id);

        repo.updateStudent(student);

        return mapper.toResponse(repo.getStudent(student.getId())
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado")));
    }

    @Override
    public void deleteStudent(Long id) throws SQLException {
        repo.deleteStudent(id);
    }

    @Override
    public List<GradeResponseDto> getStudentGrades(Long id) throws SQLException {
        return repo.getStudentGrades(id).stream()
                .map(grade -> new GradeResponseDto(grade.getAlunoNome(),
                                                        grade.getAulaAssunto(),
                                                        grade.getValor()))
                .toList();
    }
}
