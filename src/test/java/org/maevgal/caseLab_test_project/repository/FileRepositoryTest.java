package org.maevgal.caseLab_test_project.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.maevgal.caseLab_test_project.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

@DataJpaTest
@ActiveProfiles("test")
class FileRepositoryTest {
    @Autowired
    FileRepository fileRepository;

    @Test
    void findByUuid() {
        UUID testUid = UUID.randomUUID();
        LocalDateTime now = LocalDateTime
                .of(2019, Month.FEBRUARY, 22, 9, 49, 19, 275039200);

        File testFile = new File();
        testFile.setFile("0J/RgNC40LLQtdGCINC80LjRgA==");
        testFile.setCreationDate(now);
        testFile.setTitle("Первый файл");
        testFile.setDescription("Привет мир");
        testFile.setUuid(testUid);

        fileRepository.save(testFile);

        Assertions.assertThat(fileRepository.findByUuid(testUid).get()).isEqualTo(testFile);
    }
}