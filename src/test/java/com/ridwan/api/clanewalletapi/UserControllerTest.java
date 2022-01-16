//package com.ridwan.api.clanewalletapi;
//
//import com.mcrsvc.studentapp.controller.StudentController;
//import com.mcrsvc.studentapp.model.Course;
//import com.mcrsvc.studentapp.model.Student;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private StudentController studentController;
//
//    @Test
//    void getStudents() throws Exception {
//        List<Course> courses = new ArrayList<>();
//        List<Student> students = new ArrayList<>();
//        courses.add(new Course("101", "Mathematics"));
//        courses.add(new Course("102", "English"));
//        courses.add(new Course("103", "Physics"));
//
//        students.add(new Student("1", "Sam Wise", courses));
//        students.add(new Student("2", "Ben Jerry", courses));
//        students.add(new Student("3", "Katie Price", courses));
//
//        when(studentController.getStudents(true)).thenReturn(ResponseEntity.ok().body(students));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/get/false")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(3))).andDo(print());
//    }
//}
