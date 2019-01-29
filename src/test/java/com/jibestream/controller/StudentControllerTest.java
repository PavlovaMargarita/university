package com.jibestream.controller;

import com.jibestream.dto.StudentDto;
import com.jibestream.service.StudentService;
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

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { StudentController.class })
@EnableSpringDataWebSupport
public class StudentControllerTest {
    private String PATH_CREATE_STUDENT_JSON = "payload/student/student.json";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    public void shouldStoreStudent() throws Exception {
        mockMvc.perform(post("/student")
                .contentType(APPLICATION_JSON)
                .content(readFromFile(PATH_CREATE_STUDENT_JSON)))
                .andExpect(status().isOk());

        verify(studentService).createStudent(StudentDto.newBuilder().withFirstName("John").withLastName("Smith").build());
    }

    @Test
    public void shouldUpdateStudent() throws Exception {
        mockMvc.perform(put("/student/{id}", 3)
                .contentType(APPLICATION_JSON)
                .content(readFromFile(PATH_CREATE_STUDENT_JSON)))
                .andExpect(status().isOk());

        verify(studentService).updateStudent(3L, StudentDto.newBuilder().withFirstName("John").withLastName("Smith")
                .build());
    }

    @Test
    public void shouldDeleteStudent() throws Exception {
        mockMvc.perform(delete("/student/{id}", 3)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(studentService).deleteStudent(3L);
    }

    @Test
    public void shouldAssignClassToStudent() throws Exception {
        mockMvc.perform(delete("/student/assignclass", 3)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(studentService).deleteStudent(3L);
    }

    private String readFromFile(final String filename) {
        try {
            return IOUtils.toString(new ClassPathResource(filename).getInputStream(), UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
