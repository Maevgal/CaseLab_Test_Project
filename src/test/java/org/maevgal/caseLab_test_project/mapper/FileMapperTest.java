package org.maevgal.caseLab_test_project.mapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.maevgal.caseLab_test_project.dto.FileCreateDTO;
import org.maevgal.caseLab_test_project.model.File;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.Month;

class FileMapperTest {
    FileMapper mapper = Mappers.getMapper(FileMapper.class);

    @Test
    void map() {
        LocalDateTime now = LocalDateTime
                .of(2019, Month.FEBRUARY, 22, 9, 49, 19, 275039200);

        File testFile = new File();
        testFile.setFile("0J/RgNC40LLQtdGCINC80LjRgA==");
        testFile.setCreationDate(now);
        testFile.setTitle("Первый файл");
        testFile.setDescription("Привет мир");

        FileCreateDTO testFileCreateDTO = new FileCreateDTO();
        testFileCreateDTO.setFile("0J/RgNC40LLQtdGCINC80LjRgA==");
        testFileCreateDTO.setCreationDate(now);
        testFileCreateDTO.setTitle("Первый файл");
        testFileCreateDTO.setDescription("Привет мир");

        Assertions.assertThat(mapper.map(testFileCreateDTO)).isEqualTo(testFile);
    }
}