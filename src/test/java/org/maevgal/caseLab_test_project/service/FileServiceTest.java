package org.maevgal.caseLab_test_project.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.maevgal.caseLab_test_project.dto.FileDTO;
import org.maevgal.caseLab_test_project.dto.FileDTORequest;
import org.maevgal.caseLab_test_project.dto.FileDTOResponse;
import org.maevgal.caseLab_test_project.dto.GetFileDTOResponse;
import org.maevgal.caseLab_test_project.mapper.FileMapper;
import org.maevgal.caseLab_test_project.model.File;
import org.maevgal.caseLab_test_project.repository.FileRepository;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        FileDTORequest testFile = new FileDTORequest();
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

        FileDTOResponse expectedDTO = new FileDTOResponse();
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

    @Test
    void findFileByUuid() {
        UUID testUid = UUID.randomUUID();
        LocalDateTime now = LocalDateTime
                .of(2019, Month.FEBRUARY, 22, 9, 49, 19, 275039200);

        File testFile = new File();
        testFile.setUuid(testUid);
        testFile.setFile("0J/RgNC40LLQtdGCINC80LjRgA==");
        testFile.setCreationDate(now);
        testFile.setTitle("Первый файл");
        testFile.setDescription("Привет мир");

        GetFileDTOResponse expected = new GetFileDTOResponse();
        expected.setFile("0J/RgNC40LLQtdGCINC80LjRgA==");
        expected.setCreationDate(now);
        expected.setTitle("Первый файл");
        expected.setDescription("Привет мир");

        Mockito.when(fileRepository.findByUuid(testUid)).thenReturn(Optional.of(testFile));
        Mockito.when(fileMapper.map(testFile)).thenReturn(expected);
        Assertions.assertThat(fileService.findFileByUuid(testUid)).isEqualTo(expected);
    }

    @Test
    void findAllFilesPageable() {
        UUID testUid = UUID.randomUUID();
        LocalDateTime now = LocalDateTime
                .of(2019, Month.FEBRUARY, 22, 9, 49, 19, 275039200);

        File testFile = new File();
        testFile.setFile("0J/RgNC40LLQtdGCINC80LjRgA==");
        testFile.setCreationDate(now);
        testFile.setTitle("Первый файл");
        testFile.setDescription("Привет мир");
        testFile.setUuid(testUid);

        List<File> files = new ArrayList<>();
        files.add(testFile);

        int page = 0;
        int size = 2;

        FileDTO fileDTO = new FileDTO();
        fileDTO.setFile("0J/RgNC40LLQtdGCINC80LjRgA==");
        fileDTO.setCreationDate(now);
        fileDTO.setTitle("Первый файл");
        fileDTO.setDescription("Привет мир");
        fileDTO.setUuid(testUid);

        List<FileDTO> filesDTO = new ArrayList<>();
        filesDTO.add(fileDTO);

        Page<FileDTO> expected = new PageImpl<>(filesDTO, PageRequest.of(page, size), 2);
        Page<File> testfiles = new PageImpl<>(files, PageRequest.of(page, size), 2);
        Mockito.when(fileRepository.findAll(PageRequest.of(page, size, Sort.by("creationDate")))).thenReturn(testfiles);
        Mockito.when(fileMapper.mapToDTO(testFile)).thenReturn(fileDTO);
        Assertions.assertThat(fileService.findAllFilesPageable(page, size)).isEqualTo(expected);
    }
}