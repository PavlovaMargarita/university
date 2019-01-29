package com.pavlova.controller;

import com.pavlova.dto.ClassStudentGradeDto;
import com.pavlova.dto.UniversityClassDto;
import com.pavlova.service.UniversityClassService;
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

import static com.pavlova.dto.ClassStudentGradeDto.aClassStudentGradeDto;
import static com.pavlova.dto.UniversityClassDto.aUniversityClassDto;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { UniversityClassController.class })
@EnableSpringDataWebSupport
public class UniversityClassControllerTest {
    private String PATH_CLASS_JSON = "payload/class/class.json";
    private String PATH_ASSIGN_STUDENT_TO_CLASS_JSON = "payload/class/assign_student_to_class.json";

    private Long TEST_UNIVERSITY_CLASS_ID = 3L;
    private Long TEST_STUDENT_ID = 5L;
    private UniversityClassDto TEST_EXPECTED_SAVED_UNIVERSITY_CLASS
            = aUniversityClassDto().withName("Computer Science").build();
    private ClassStudentGradeDto TEST_EXPECTED_SAVED_STUDENT_GRADE
            = aClassStudentGradeDto().withStudentId(TEST_STUDENT_ID).withGrade(9).build();


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UniversityClassService universityClassService;

    @Test
    public void shouldStoreUniversityClass() throws Exception {

        mockMvc.perform(post("/class")
                .contentType(APPLICATION_JSON)
                .content(readFromFile(PATH_CLASS_JSON)))
                .andExpect(status().isOk());

        verify(universityClassService).createClass(TEST_EXPECTED_SAVED_UNIVERSITY_CLASS);
    }

    @Test
    public void shouldUpdateClass() throws Exception {
        mockMvc.perform(put("/class/{id}", TEST_UNIVERSITY_CLASS_ID)
                .contentType(APPLICATION_JSON)
                .content(readFromFile(PATH_CLASS_JSON)))
                .andExpect(status().isOk());

        verify(universityClassService).updateClass(TEST_UNIVERSITY_CLASS_ID, TEST_EXPECTED_SAVED_UNIVERSITY_CLASS);
    }

    @Test
    public void shouldDeleteClass() throws Exception {
        mockMvc.perform(delete("/class/{id}", TEST_UNIVERSITY_CLASS_ID))
                .andExpect(status().isOk());

        verify(universityClassService).deleteClass(TEST_UNIVERSITY_CLASS_ID);
    }

    @Test
    public void shouldAssignStudentToClass() throws Exception {
        mockMvc.perform(post("/class/{id}/assignstudent", TEST_UNIVERSITY_CLASS_ID)
                .contentType(APPLICATION_JSON)
                .content(readFromFile(PATH_ASSIGN_STUDENT_TO_CLASS_JSON)))
                .andExpect(status().isOk());

        verify(universityClassService).assignStudentToClass(TEST_UNIVERSITY_CLASS_ID, TEST_EXPECTED_SAVED_STUDENT_GRADE);
    }

    @Test
    public void shouldUnassignStudentToClass() throws Exception {
        mockMvc.perform(delete("/class/{id}/unassignstudent/{studentId}", TEST_UNIVERSITY_CLASS_ID, TEST_STUDENT_ID))
                .andExpect(status().isOk());

        verify(universityClassService).unassignStudentFromClass(TEST_UNIVERSITY_CLASS_ID, TEST_STUDENT_ID);
    }

    private String readFromFile(final String filename) {
        try {
            return IOUtils.toString(new ClassPathResource(filename).getInputStream(), UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
