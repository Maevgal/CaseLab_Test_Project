package org.maevgal.caseLab_test_project.controller;

import org.junit.jupiter.api.Test;
import org.maevgal.caseLab_test_project.dto.FileCreateDTO;
import org.maevgal.caseLab_test_project.dto.FileDTO;
import org.maevgal.caseLab_test_project.service.FileService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FileController.class)
class FileControllerTest {
    @MockBean
    private FileService fileService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void create() throws Exception {
        FileCreateDTO testFile = new FileCreateDTO();
        UUID testUid = UUID.randomUUID();
        testFile.setFile("0J/RgNC40LLQtdGCINC80LjRgA==");
        LocalDateTime now = LocalDateTime
                .of(2019, Month.FEBRUARY, 22, 9, 49, 19, 275039200);
        testFile.setCreationDate(now);
        testFile.setTitle("Первый файл");
        testFile.setDescription("Привет мир");

        Mockito.when(fileService.createFile(testFile)).thenReturn(new FileDTO().setUuid(testUid));

        mockMvc.perform(post("/file")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "file": "0J/RgNC40LLQtdGCINC80LjRgA==",
                                    "title": "Первый файл",
                                    "creationDate": "2019-02-22T09:49:19.275039200",
                                    "description": "Привет мир"
                                }
                                """))
                .andExpect(content().json("""
                        {
                        "uuid": "%s"
                        }
                        """.formatted(testUid.toString())))
                .andExpect(status().isCreated());
    }

    @Test
    void createWithDataFileIsNull() throws Exception {
        mockMvc.perform(post("/file")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "file": "",
                                    "title": "Первый файл",
                                    "creationDate": "2019-02-22T09:49:19.275039200",
                                    "description": "Привет мир"
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createWithDataTitleIsNull() throws Exception {
        mockMvc.perform(post("/file")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "file": "0J/RgNC40LLQtdGCINC80LjRgA==",
                                    "title": "",
                                    "creationDate": "2019-02-22T09:49:19.275039200",
                                    "description": "Привет мир"
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createWithDataCreationDateIsNull() throws Exception {
        mockMvc.perform(post("/file")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "file": "0J/RgNC40LLQtdGCINC80LjRgA==",
                                    "title": "Первый файл",
                                    "creationDate": "",
                                    "description": "Привет мир"
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createWithDataDescriptionIsNull() throws Exception {
        mockMvc.perform(post("/file")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "file": "0J/RgNC40LLQtdGCINC80LjRgA==",
                                    "title": "Первый файл",
                                    "creationDate": "2019-02-22T09:49:19.275039200",
                                    "description": ""
                                }
                                """))
                .andExpect(status().isBadRequest());
    }
}