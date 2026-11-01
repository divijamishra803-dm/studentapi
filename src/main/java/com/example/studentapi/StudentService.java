package com.example.studentapi;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class StudentService {

    private final Map<Integer, Student> students = new ConcurrentHashMap<>();

    public Student add(Student s) {
        if (s.name() == null || s.name().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (s.cgpa() < 0 || s.cgpa() > 10) {
            throw new IllegalArgumentException("CGPA must be between 0 and 10");
        }
        students.put(s.id(), s);
        return s;
    }

    public Optional<Student> find(int id) {
        return Optional.ofNullable(students.get(id));
    }
}