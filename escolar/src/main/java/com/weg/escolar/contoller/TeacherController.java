package com.weg.escolar.contoller;

import com.weg.escolar.dto.TeacherRequestDto;
import com.weg.escolar.dto.TeacherResponseDto;
import com.weg.escolar.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/professores")
public class TeacherController {

    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @PostMapping()
    public TeacherResponseDto createTeacher(@RequestBody TeacherRequestDto teacherRequestDto) {
        try {
            return service.createTeacher(teacherRequestDto);
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public TeacherResponseDto getTeacher(@PathVariable Long id) {
        try {
            return service.getTeacher(id);
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping()
    public List<TeacherResponseDto> getAllTeachers() {
        try {
            return service.getAllTeachers();
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public TeacherResponseDto updateTeacher(@RequestBody TeacherRequestDto teacherRequestDto, @PathVariable Long id) {
        try {
            return service.updateTeacher(teacherRequestDto, id);
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        try {
            service.deleteTeacher(id);
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
