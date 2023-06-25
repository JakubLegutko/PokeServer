package com.example.pokeserver.controllers;

import com.example.pokeserver.data.Course;
import com.example.pokeserver.data.CoursesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

import java.util.List;

@RestController
@Component
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CoursesController {

    private final CoursesRepository coursesRepository;

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return coursesRepository.findById(id).orElseThrow(() -> new NotFoundException("lol"));
    }

    @GetMapping()
    public List<Course> getAllCourses() {
        return coursesRepository.findAll();
    }
}
