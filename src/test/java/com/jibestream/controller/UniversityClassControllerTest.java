package com.jibestream.controller;

import com.jibestream.controller.UniversityClassController;
import com.jibestream.dto.UniversityClassDto;
import com.jibestream.service.UniversityClassService;
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
@WebMvcTest(controllers = { UniversityClassController.class })
@EnableSpringDataWebSupport
public class UniversityClassControllerTest {
    private String PATH_CLASS_JSON = "payload/class/class.json";
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

        verify(universityClassService).createClass(
                UniversityClassDto.newBuilder().withName("Computer Science").build());
    }

    @Test
    public void shouldUpdateClass() throws Exception {
        mockMvc.perform(put("/class/{id}", 3)
                .contentType(APPLICATION_JSON)
                .content(readFromFile(PATH_CLASS_JSON)))
                .andExpect(status().isOk());

        verify(universityClassService).updateClass(3L,
                UniversityClassDto.newBuilder().withName("Computer Science").build());
    }

    @Test
    public void shouldDeleteClass() throws Exception {
        mockMvc.perform(delete("/class/{id}", 3)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(universityClassService).deleteClass(3L);
    }

    private String readFromFile(final String filename) {
        try {
            return IOUtils.toString(new ClassPathResource(filename).getInputStream(), UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
