package org.maevgal.caseLab_test_project.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.maevgal.caseLab_test_project.dto.FileCreateDTO;
import org.maevgal.caseLab_test_project.dto.FileDTO;
import org.maevgal.caseLab_test_project.exception.ResourceNotFoundException;
import org.maevgal.caseLab_test_project.mapper.FileMapper;
import org.maevgal.caseLab_test_project.model.File;
import org.maevgal.caseLab_test_project.repository.FileRepository;
import org.maevgal.caseLab_test_project.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@AllArgsConstructor
@Data
@RestController
@RequestMapping
public class FileController {
/*    @Autowired
    private FileRepository fileRepository;*/
/*    private FileMapper fileMapper;*/
    private FileService fileService;

    @PostMapping("/file")
    @ResponseStatus(HttpStatus.CREATED)
    public FileDTO create(@Valid @RequestBody FileCreateDTO fileCreateDTO) {
        return fileService.createFile(fileCreateDTO);
    }

/*    @GetMapping("/file/uuid")
    @ResponseStatus(HttpStatus.OK)
    public FileDTO show(@PathVariable UUID uuid) {
        File file = fileRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Not found file with %uuid.", uuid)));
        return new FileDTO().setUuid(uuid);
    }*/

}
