package org.maevgal.caseLab_test_project.mapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.maevgal.caseLab_test_project.dto.FileDTO;
import org.maevgal.caseLab_test_project.dto.FileDTORequest;
import org.maevgal.caseLab_test_project.dto.GetFileDTOResponse;
import org.maevgal.caseLab_test_project.model.File;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

class FileMapperTest {
    FileMapper mapper = Mappers.getMapper(FileMapper.class);

    @Test
    void mapFileDTORequestToFile() {
        LocalDateTime now = LocalDateTime
                .of(2019, Month.FEBRUARY, 22, 9, 49, 19, 275039200);

        File testFile = new File();
        testFile.setFile("0J/RgNC40LLQtdGCINC80LjRgA==");
        testFile.setCreationDate(now);
        testFile.setTitle("Первый файл");
        testFile.setDescription("Привет мир");

        FileDTORequest testFileCreateDTO = new FileDTORequest();
        testFileCreateDTO.setFile("0J/RgNC40LLQtdGCINC80LjRgA==");
        testFileCreateDTO.setCreationDate(now);
        testFileCreateDTO.setTitle("Первый файл");
        testFileCreateDTO.setDescription("Привет мир");

        Assertions.assertThat(mapper.map(testFileCreateDTO)).isEqualTo(testFile);
    }

    @Test
    void mapFileToGetFileDTOResponse() {
        LocalDateTime now = LocalDateTime
                .of(2019, Month.FEBRUARY, 22, 9, 49, 19, 275039200);
        UUID testUid = UUID.randomUUID();

        File testFile = new File();
        testFile.setFile("0J/RgNC40LLQtdGCINC80LjRgA==");
        testFile.setCreationDate(now);
        testFile.setTitle("Первый файл");
        testFile.setDescription("Привет мир");
        testFile.setUuid(testUid);


        GetFileDTOResponse testGetFileDTOResponse = new GetFileDTOResponse();
        testGetFileDTOResponse.setFile("0J/RgNC40LLQtdGCINC80LjRgA==");
        testGetFileDTOResponse.setCreationDate(now);
        testGetFileDTOResponse.setTitle("Первый файл");
        testGetFileDTOResponse.setDescription("Привет мир");

        Assertions.assertThat(mapper.map(testFile)).isEqualTo(testGetFileDTOResponse);
    }

    @Test
    void mapFileDTOToFile() {
        LocalDateTime now = LocalDateTime
                .of(2019, Month.FEBRUARY, 22, 9, 49, 19, 275039200);
        UUID testUid = UUID.randomUUID();

        File testFile = new File();
        testFile.setFile("0J/RgNC40LLQtdGCINC80LjRgA==");
        testFile.setCreationDate(now);
        testFile.setTitle("Первый файл");
        testFile.setDescription("Привет мир");
        testFile.setUuid(testUid);


        FileDTO testFileDTO = new FileDTO();
        testFileDTO.setFile("0J/RgNC40LLQtdGCINC80LjRgA==");
        testFileDTO.setCreationDate(now);
        testFileDTO.setTitle("Первый файл");
        testFileDTO.setDescription("Привет мир");
        testFileDTO.setUuid(testUid);

        Assertions.assertThat(mapper.mapToDTO(testFile)).isEqualTo(testFileDTO);
    }
}