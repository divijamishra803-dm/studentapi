package com.example.studentapi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    private final StudentService service = new StudentService();

    @Test
    void addStudent_returnsSavedStudent() {
        Student s = service.add(new Student(1, "Asha", 8.5));
        assertEquals("Asha", s.name());
    }

    @Test
    void find_unknownId_returnsEmpty() {
        assertTrue(service.find(99).isEmpty());
    }

    @Test
    void add_blankName_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> service.add(new Student(2, " ", 7.0)));
    }

    @Test
    void add_invalidCgpa_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> service.add(new Student(3, "Ravi", 11.0)));
    }
}