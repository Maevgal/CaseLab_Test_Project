package org.maevgal.caseLab_test_project.service;

import lombok.RequiredArgsConstructor;
import org.maevgal.caseLab_test_project.dto.FileCreateDTO;
import org.maevgal.caseLab_test_project.dto.FileDTO;
import org.maevgal.caseLab_test_project.mapper.FileMapper;
import org.maevgal.caseLab_test_project.model.File;
import org.maevgal.caseLab_test_project.repository.FileRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileService {
    private FileMapper fileMapper;
    private FileRepository fileRepository;

    public FileDTO createFile(FileCreateDTO fileCreateDTO) {
        File file = fileMapper.map(fileCreateDTO);
        UUID fileUuid = UUID.randomUUID();
        file.setUuid(fileUuid);
        fileRepository.save(file);
        return new FileDTO().setUuid(fileUuid);
    }
}
