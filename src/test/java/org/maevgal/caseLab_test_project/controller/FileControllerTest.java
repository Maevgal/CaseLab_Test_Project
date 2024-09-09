package org.maevgal.caseLab_test_project.controller;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.maevgal.caseLab_test_project.dto.FileDTO;
import org.maevgal.caseLab_test_project.dto.FileDTORequest;
import org.maevgal.caseLab_test_project.dto.FileDTOResponse;
import org.maevgal.caseLab_test_project.dto.GetFileDTOResponse;
import org.maevgal.caseLab_test_project.service.FileService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        FileDTORequest testFile = new FileDTORequest();
        UUID testUid = UUID.randomUUID();
        testFile.setFile("0J/RgNC40LLQtdGCINC80LjRgA==");
        LocalDateTime now = LocalDateTime
                .of(2019, Month.FEBRUARY, 22, 9, 49, 19, 275039200);
        testFile.setCreationDate(now);
        testFile.setTitle("Первый файл");
        testFile.setDescription("Привет мир");

        Mockito.when(fileService.createFile(testFile)).thenReturn(new FileDTOResponse().setUuid(testUid));

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
        FileDTORequest testFile = new FileDTORequest();
        UUID testUid = UUID.randomUUID();
        testFile.setFile("0J/RgNC40LLQtdGCINC80LjRgA==");
        LocalDateTime now = LocalDateTime
                .of(2019, Month.FEBRUARY, 22, 9, 49, 19, 275039200);
        testFile.setCreationDate(now);
        testFile.setTitle("Первый файл");
        testFile.setDescription("Привет мир");

        Mockito.when(fileService.createFile(testFile)).thenReturn(new FileDTOResponse().setUuid(testUid));
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
        FileDTORequest testFile = new FileDTORequest();
        UUID testUid = UUID.randomUUID();
        testFile.setFile("0J/RgNC40LLQtdGCINC80LjRgA==");
        LocalDateTime now = LocalDateTime
                .of(2019, Month.FEBRUARY, 22, 9, 49, 19, 275039200);
        testFile.setCreationDate(now);
        testFile.setTitle("Первый файл");
        testFile.setDescription("Привет мир");

        Mockito.when(fileService.createFile(testFile)).thenReturn(new FileDTOResponse().setUuid(testUid));

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
        FileDTORequest testFile = new FileDTORequest();
        UUID testUid = UUID.randomUUID();
        testFile.setFile("0J/RgNC40LLQtdGCINC80LjRgA==");
        LocalDateTime now = LocalDateTime
                .of(2019, Month.FEBRUARY, 22, 9, 49, 19, 275039200);
        testFile.setCreationDate(now);
        testFile.setTitle("Первый файл");
        testFile.setDescription("Привет мир");

        Mockito.when(fileService.createFile(testFile)).thenReturn(new FileDTOResponse().setUuid(testUid));

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
        FileDTORequest testFile = new FileDTORequest();
        UUID testUid = UUID.randomUUID();
        testFile.setFile("0J/RgNC40LLQtdGCINC80LjRgA==");
        LocalDateTime now = LocalDateTime
                .of(2019, Month.FEBRUARY, 22, 9, 49, 19, 275039200);
        testFile.setCreationDate(now);
        testFile.setTitle("Первый файл");
        testFile.setDescription("Привет мир");

        Mockito.when(fileService.createFile(testFile)).thenReturn(new FileDTOResponse().setUuid(testUid));

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

    @Test
    void show() throws Exception {
        UUID testUid = UUID.randomUUID();
        LocalDateTime now = LocalDateTime
                .of(2019, Month.FEBRUARY, 22, 9, 49, 19, 275039200);

        GetFileDTOResponse expected = new GetFileDTOResponse();
        expected.setFile("0J/RgNC40LLQtdGCINC80LjRgA==");
        expected.setCreationDate(now);
        expected.setTitle("Первый файл");
        expected.setDescription("Привет мир");

        Mockito.when(fileService.findFileByUuid(testUid)).thenReturn(expected);
        mockMvc.perform(get("/file/%s".formatted(testUid.toString())))
                .andExpect(status().isOk());
    }

    @Test
    void findAllFilesPageable() throws Exception {
        int page = 0;
        int size = 2;

        FileDTO fileDTO1 = generateFile();
        FileDTO fileDTO2 = generateFile();

        List<FileDTO> list = new ArrayList<>();
        list.add(fileDTO1);
        list.add(fileDTO2);

        Page<FileDTO> expected = new PageImpl<>(list, PageRequest.of(page, size), 3);

        Mockito.when(fileService.findAllFilesPageable(page, size)).thenReturn(expected);
        mockMvc.perform(get("/file?page=0&size=2")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                          "content": [
                            {
                              "uuid": "%s",
                              "file": "0J/RgNC40LLQtdGCINC80LjRgA==",
                              "title": "Первый файл",
                              "creationDate": "2019-02-22T09:49:19.2750392",
                              "description": "Привет мир"
                            },
                            {
                              "uuid": "%s",
                              "file": "0J/RgNC40LLQtdGCINC80LjRgA==",
                              "title": "Первый файл",
                              "creationDate": "2019-02-22T09:49:19.2750392",
                              "description": "Привет мир"
                            }
                          ],
                          "pageable": {
                            "pageNumber": 0,
                            "pageSize": 2,
                            "sort": {
                              "empty": true,
                              "sorted": false,
                              "unsorted": true
                            },
                            "offset": 0,
                            "paged": true,
                            "unpaged": false
                          },
                          "last": false,
                          "totalPages": 2,
                          "totalElements": 3,
                          "first": true,
                          "size": 2,
                          "number": 0,
                          "sort": {
                            "empty": true,
                            "sorted": false,
                            "unsorted": true
                          },
                          "numberOfElements": 2,
                          "empty": false
                        }
                        """.formatted(fileDTO1.getUuid(), fileDTO2.getUuid())));
    }

    private static @NotNull FileDTO generateFile() {
        UUID testUid1 = UUID.randomUUID();
        LocalDateTime now = LocalDateTime
                .of(2019, Month.FEBRUARY, 22, 9, 49, 19, 275039200);
        FileDTO fileDTO1 = new FileDTO();
        fileDTO1.setFile("0J/RgNC40LLQtdGCINC80LjRgA==");
        fileDTO1.setCreationDate(now);
        fileDTO1.setTitle("Первый файл");
        fileDTO1.setDescription("Привет мир");
        fileDTO1.setUuid(testUid1);
        return fileDTO1;
    }
}