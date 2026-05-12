package com.weg.escolar.contoller;

import com.weg.escolar.dto.GradeResponseDto;
import com.weg.escolar.dto.StudentRequestDto;
import com.weg.escolar.dto.StudentResponseDto;
import com.weg.escolar.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping()
    public StudentResponseDto createStudent(@RequestBody StudentRequestDto studentRequestDto) {
        try {
            return service.createStudent(studentRequestDto);
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public StudentResponseDto getStudent(@PathVariable Long id) {
        try {
            return service.getStudent(id);
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping()
    public List<StudentResponseDto> getAllStudents() {
        try {
            return service.getAllStudents();
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public StudentResponseDto updateStudent(@RequestBody StudentRequestDto studentRequestDto, @PathVariable Long id) {
        try {
            return service.updateStudent(studentRequestDto, id);
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        try {
            service.deleteStudent(id);
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}/notas")
    public List<GradeResponseDto> getStudentGrades(@PathVariable Long id) {
        try {
            return service.getStudentGrades(id);
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}