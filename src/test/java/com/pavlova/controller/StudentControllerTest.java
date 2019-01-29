package com.pavlova.controller;

import com.pavlova.dto.StudentClassGradeDto;
import com.pavlova.dto.StudentDto;
import com.pavlova.service.StudentService;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.io.UncheckedIOException;

import static com.pavlova.dto.StudentDto.aStudentDto;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Arrays.asList;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { StudentController.class })
@EnableSpringDataWebSupport
public class StudentControllerTest {
    private final String PATH_STUDENT_JSON = "payload/student/student.json";
    private final String PATH_STUDENT_CLASSES_JSON = "payload/student/student_classes.json";
    private final StudentDto TEST_EXPECTED_STUDENT = aStudentDto().withFirstName("John").withLastName("Smith").build();
    private final Long TEST_STUDENT_ID = 3L;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    public void shouldStoreStudent() throws Exception {
        mockMvc.perform(post("/student")
                .contentType(APPLICATION_JSON)
                .content(readFromFile(PATH_STUDENT_JSON)))
                .andExpect(status().isOk());

        verify(studentService).createStudent(TEST_EXPECTED_STUDENT);
    }

    @Test
    public void shouldUpdateStudent() throws Exception {
        mockMvc.perform(put("/student/{id}", TEST_STUDENT_ID)
                .contentType(APPLICATION_JSON)
                .content(readFromFile(PATH_STUDENT_JSON)))
                .andExpect(status().isOk());

        verify(studentService).updateStudent(TEST_STUDENT_ID, TEST_EXPECTED_STUDENT);
    }

    @Test
    public void shouldDeleteStudent() throws Exception {
        mockMvc.perform(delete("/student/{id}", TEST_STUDENT_ID)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(studentService).deleteStudent(TEST_STUDENT_ID);
    }

    @Test
    public void shouldGetStudentClasses() throws Exception {
        StudentClassGradeDto computerScienceClass = StudentClassGradeDto.aStudentClassGradeDto()
                .withClassName("Computer Science")
                .withGrade(8)
                .build();

        StudentClassGradeDto philosophyClass = StudentClassGradeDto.aStudentClassGradeDto()
                .withClassName("Philosophy")
                .withGrade(9)
                .build();

        when(studentService.getStudentClasses(eq(TEST_STUDENT_ID))).thenReturn(
                asList(computerScienceClass, philosophyClass));

        mockMvc.perform(get("/student/{id}/classes", TEST_STUDENT_ID)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(readFromFile(PATH_STUDENT_CLASSES_JSON)));

        verify(studentService).getStudentClasses(TEST_STUDENT_ID);
    }

    private String readFromFile(final String filename) {
        try {
            return IOUtils.toString(new ClassPathResource(filename).getInputStream(), UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
