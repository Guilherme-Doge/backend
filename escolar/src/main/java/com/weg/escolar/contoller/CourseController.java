package com.weg.escolar.contoller;

import com.weg.escolar.dto.ClassResponseDto;
import com.weg.escolar.dto.CourseRequestDto;
import com.weg.escolar.dto.CourseResponseDto;
import com.weg.escolar.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @PostMapping()
    public CourseResponseDto createCourse(@RequestBody CourseRequestDto teacherRequestDto) {
        try {
            return service.createCourse(teacherRequestDto);
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public CourseResponseDto getCourse(@PathVariable Long id) {
        try {
            return service.getCourse(id);
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping()
    public List<CourseResponseDto> getAllCourse() {
        try {
            return service.getAllCourses();
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public CourseResponseDto updateCourse(@RequestBody CourseRequestDto teacherRequestDto, @PathVariable Long id) {
        try {
            return service.updateCourse(teacherRequestDto, id);
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        try {
            service.deleteCourse(id);
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}/turmas")
    public List<ClassResponseDto> getCourseClasses(@PathVariable Long id) {
        try {
            return service.getCourseClasses(id);
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
