package com.weg.escolar.contoller;

import com.weg.escolar.dto.*;
import com.weg.escolar.service.ClassService;
import com.weg.escolar.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/turmas")
public class ClassController {

    private final ClassService service;

    public ClassController(ClassService service) {
        this.service = service;
    }

    @PostMapping()
    public ClassResponseDto createClass(@RequestBody ClassRequestDto classRequestDto) {
        try {
            return service.createClass(classRequestDto);
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ClassResponseDto getClass(@PathVariable Long id) {
        try {
            return service.getClass(id);
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping()
    public List<ClassResponseDto> getAllClasses() {
        try {
            return service.getAllClasses();
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public ClassResponseDto updateClass(@RequestBody ClassRequestDto classRequestDto, @PathVariable Long id) {
        try {
            return service.updateClass(classRequestDto, id);
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteClass(@PathVariable Long id) {
        try {
            service.deleteClass(id);
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}/alunos")
    public List<StudentResponseDto> getClassStudents(@PathVariable Long id) {
        try {
            return service.getClassStudents(id);
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}