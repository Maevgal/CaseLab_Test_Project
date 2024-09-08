package org.maevgal.caseLab_test_project.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.maevgal.caseLab_test_project.dto.FileCreateDTO;
import org.maevgal.caseLab_test_project.dto.FileDTO;
import org.maevgal.caseLab_test_project.mapper.FileMapper;
import org.maevgal.caseLab_test_project.model.File;
import org.maevgal.caseLab_test_project.repository.FileRepository;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class FileServiceTest {
    @Mock
    private FileRepository fileRepository;
    @Mock
    private FileMapper fileMapper;
    @InjectMocks
    private FileService fileService;

    @Test
    void createFile() {
        FileCreateDTO testFile = new FileCreateDTO();
        UUID testUid = UUID.randomUUID();
        testFile.setFile("0J/RgNC40LLQtdGCINC80LjRgA==");
        LocalDateTime now = LocalDateTime
                .of(2019, Month.FEBRUARY, 22, 9, 49, 19, 275039200);
        testFile.setCreationDate(now);
        testFile.setTitle("Первый файл");
        testFile.setDescription("Привет мир");

        File expectedFile = new File();
        expectedFile.setFile("0J/RgNC40LLQtdGCINC80LjRgA==");
        expectedFile.setCreationDate(now);
        expectedFile.setTitle("Первый файл");
        expectedFile.setDescription("Привет мир");
        expectedFile.setUuid(testUid);

        FileDTO expectedDTO = new FileDTO();
        expectedDTO.setUuid(testUid);

        Mockito.when(fileMapper.map(testFile)).thenReturn(expectedFile);

        Assertions.assertThat(fileService.createFile(testFile))
                .matches(dto -> dto.getUuid() != null);

        ArgumentCaptor<File> fileArgumentCaptor = ArgumentCaptor.forClass(File.class);

        Mockito.verify(fileRepository).save(fileArgumentCaptor.capture());

        Assertions.assertThat(fileArgumentCaptor.getValue())
                .matches(file -> file.getUuid() != null)
                .usingRecursiveComparison()
                .ignoringFields("uuid")
                .isEqualTo(expectedFile);


    }
}