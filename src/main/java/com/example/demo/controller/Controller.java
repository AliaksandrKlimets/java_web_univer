package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.model.Mentor;
import com.example.demo.model.Student;
import com.example.demo.model.Univer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class Controller {

    private Univer univer;
    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private List<Mentor> mentors = new ArrayList<>();

    @Autowired
    Controller() {
        this.univer = new Univer();

        for (int i = 1; i < 3; i++) {
            Mentor mentor = new Mentor();
            mentor.setId((long) i);
            mentor.setName("Pyotr " + i);
            mentor.setNickname("Volan de mrot " + 1);
            mentor.setAge((long) (Math.random() * 20 + 50));
            this.mentors.add(mentor);
        }


        for (int i = 1; i < 4; i++) {
            Student student = new Student();
            student.setId((long) i);
            student.setName("Lyoha " + i);
            student.setStipuha((long) (Math.random() * 10000));
            student.setNickname("Ubivator" + (i + 1 * 1000));
            this.students.add(student);
        }


        for (int i = 1; i < 3; i++) {
            Course course = new Course();
            course.setId((long) i);
            course.setCourseName("Electronica " + i);
            course.setMentor(mentors.get(i - 1));
            this.courses.add(course);
        }

        this.univer.setCourses(courses);
        this.univer.setMentors(mentors);
        this.univer.setStudents(students);
    }


    @GetMapping("/univer")
    public Univer test() {
        return univer;
    }

    @PostMapping("/course/{courseId}/add/student/{studentId}")
    public Course addStudentToCourse(@PathVariable("courseId") Long courseId,
                                     @PathVariable("studentId") Long studentId) {
        Course course = null;
        for (Course course1 : courses) {
            if (course1.getId() == courseId) {
                course = course1;
                break;
            }
        }
        Student student = null;
        for (Student student1 : students) {
            if (student1.getId() == studentId) {
                student = student1;
                break;
            }
        }
        course.getStudents().add(student);
        return course;
    }

    @PostMapping("/univer/add/mentor")
    public Univer addMentor(@RequestBody Mentor mentor) {
        univer.getMentors().add(mentor);
        return univer;
    }

    @DeleteMapping("/univer/remove/mentor/{mentorId}")
    public Univer removeMentor(@PathVariable Long mentorId) {
        Iterator<Mentor> iterator = univer.getMentors().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == mentorId) {
                iterator.remove();
            }
        }
        return univer;
    }

    @PutMapping("/mentor/elder/{mentorId}")
    public Mentor makeMentorElder(@PathVariable("mentorId") long id) {
        Mentor mentor = null;
        for (Mentor mentor1 : mentors) {
            if (mentor1.getId() == id) {
                mentor = mentor1;
            }
        }
        mentor.setAge(mentor.getAge() + 1);
        return mentor;
    }

    @PutMapping("/stip/update/{studentId}")
    public Student updateStip(@PathVariable("studentId") long id,
                              @RequestParam Double avgMark) {
        Student student = null;
        for (Student student1 : students) {
            if (student1.getId() == id) {
                student = student1;
                break;
            }
        }
        student.setAvgMark(avgMark);
        return student;
    }

    @GetMapping("/student/search")
    public List<Student> getStudents(@RequestParam(required = false) String searchValue) {
        if(searchValue == null) return students;
        return students.stream()
                .filter(a -> a.getName().toLowerCase().contains(searchValue.toLowerCase()))
                .collect(Collectors.toList());
    }
}
