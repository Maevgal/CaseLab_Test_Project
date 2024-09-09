package org.maevgal.caseLab_test_project.service;

import lombok.RequiredArgsConstructor;
import org.maevgal.caseLab_test_project.dto.FileDTO;
import org.maevgal.caseLab_test_project.dto.FileDTORequest;
import org.maevgal.caseLab_test_project.dto.FileDTOResponse;
import org.maevgal.caseLab_test_project.dto.GetFileDTOResponse;
import org.maevgal.caseLab_test_project.exception.ResourceNotFoundException;
import org.maevgal.caseLab_test_project.mapper.FileMapper;
import org.maevgal.caseLab_test_project.model.File;
import org.maevgal.caseLab_test_project.repository.FileRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileService {
    private final FileMapper fileMapper;
    private final FileRepository fileRepository;

    public FileDTOResponse createFile(FileDTORequest fileCreateDTO) {
        File file = fileMapper.map(fileCreateDTO);
        UUID fileUuid = UUID.randomUUID();
        file.setUuid(fileUuid);
        fileRepository.save(file);
        return new FileDTOResponse().setUuid(fileUuid);
    }

    public GetFileDTOResponse findFileByUuid(UUID uuid) {
        File file = fileRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Not found file with %uuid.".formatted(uuid)));

        return fileMapper.map(file);
    }

    public Page<FileDTO> findAllFilesPageable(int page, int size) {
        Page<File> files = fileRepository.findAll(
                PageRequest.of(page, size, Sort.by("creationDate"))
        );
        return files.map(fileMapper::mapToDTO);
    }
}
