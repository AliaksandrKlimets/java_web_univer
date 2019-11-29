package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.model.Univer;
import com.example.demo.repo.CourseRepo;
import com.example.demo.repo.StudentRepo;
import com.example.demo.repo.UniverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class Controller {

    private final UniverRepo univerRepo;
    private final CourseRepo courseRepo;
    private final StudentRepo studentRepo;

    @Autowired
    Controller(UniverRepo univerRepo, CourseRepo courseRepo, StudentRepo studentRepo) {
        this.univerRepo = univerRepo;
        this.courseRepo = courseRepo;


        this.studentRepo = studentRepo;
    }


    @GetMapping("/univer")
    public List<Univer> test() {
        return univerRepo.findAll();
    }

    @PostMapping("/univer/create")
    public Univer createUniver(@RequestParam String name) {
        Univer univer = new Univer();
        univer.setName(name);
        univerRepo.save(univer);
        return univer;
    }

    @PostMapping("/student/create")
    public Student createStudent(@RequestBody Student student){
        studentRepo.save(student);
        return student;
    }

    @PutMapping("/student/{id}/update")
    public Student updateStudent(@PathVariable("id") Student st,
                                 @RequestBody Student student){
        st.setStipuha(student.getStipuha());
        st.setName(student.getName());
        studentRepo.save(st);
        return st;
    }

    @GetMapping("/course")
    public List<Course> courses() {
        return courseRepo.findAll();
    }

    @PostMapping("/course/{courseId}/student/{studentId}")
    public void addStudentToCourse(@PathVariable("courseId") Course course,
                                   @PathVariable("studentId") Student student) {
        course.getStudents().add(student);
        courseRepo.save(course);
    }

    @GetMapping("/univer/byname")
    public Univer byName(@RequestParam String name) {
        return univerRepo.findByName(name);
    }

    @GetMapping("/univer/{id}")
    public Univer byId(@PathVariable("id") Univer univer) {
        return univer;
    }
//
//    @PostMapping("/course/{courseId}/add/student/{studentId}")
//    public Course addStudentToCourse(@PathVariable("courseId") Long courseId,
//                                     @PathVariable("studentId") Long studentId) {
//        Course course = null;
//        for (Course course1 : courses) {
//            if (course1.getId() == courseId) {
//                course = course1;
//                break;
//            }
//        }
//        Student student = null;
//        for (Student student1 : students) {
//            if (student1.getId() == studentId) {
//                student = student1;
//                break;
//            }
//        }
//        course.getStudents().add(student);
//        return course;
//    }

//    @PostMapping("/univer/add/mentor")
//    public Univer addMentor(@RequestBody Mentor mentor) {
//        univer.getMentors().add(mentor);
//        return univer;
//    }

//    @DeleteMapping("/univer/remove/mentor/{mentorId}")
//    public Univer removeMentor(@PathVariable Long mentorId) {
//        Iterator<Mentor> iterator = univer.getMentors().iterator();
//        while (iterator.hasNext()) {
//            if (iterator.next().getId() == mentorId) {
//                iterator.remove();
//            }
//        }
//        return univer;
//    }

//    @PutMapping("/mentor/elder/{mentorId}")
//    public Mentor makeMentorElder(@PathVariable("mentorId") long id) {
//        Mentor mentor = null;
//        for (Mentor mentor1 : mentors) {
//            if (mentor1.getId() == id) {
//                mentor = mentor1;
//            }
//        }
//        mentor.setAge(mentor.getAge() + 1);
//        return mentor;
//    }
//
//    @PutMapping("/stip/update/{studentId}")
//    public Student updateStip(@PathVariable("studentId") long id,
//                              @RequestParam Double avgMark) {
//        Student student = null;
//        for (Student student1 : students) {
//            if (student1.getId() == id) {
//                student = student1;
//                break;
//            }
//        }
//        student.setAvgMark(avgMark);
//        return student;
//    }
//
//    @GetMapping("/student/search")
//    public List<Student> getStudents(@RequestParam(required = false) String searchValue) {
//        if(searchValue == null) return students;
//        return students.stream()
//                .filter(a -> a.getName().toLowerCase().contains(searchValue.toLowerCase()))
//                .collect(Collectors.toList());
//    }
}
