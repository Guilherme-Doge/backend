package com.weg.escolar.repo;

import com.weg.escolar.model.Class;
import com.weg.escolar.model.Course;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CourseRepo {
    Course createCourse(Course course) throws SQLException;

    Optional<Course> getCourse(Long id) throws SQLException;

    List<Course> getAllCourses() throws SQLException;

    Course updateCourse(Course course) throws SQLException;

    void deleteCourse(Long id) throws SQLException;

    List<Class> getCourseClasses(Long id) throws SQLException;
}
