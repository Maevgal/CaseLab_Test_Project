package org.maevgal.caseLab_test_project.controller;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.maevgal.caseLab_test_project.dto.FileDTO;
import org.maevgal.caseLab_test_project.dto.FileDTORequest;
import org.maevgal.caseLab_test_project.dto.FileDTOResponse;
import org.maevgal.caseLab_test_project.dto.GetFileDTOResponse;
import org.maevgal.caseLab_test_project.service.FileService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@Data
@RestController
@RequestMapping
public class FileController {

    private final FileService fileService;

    @PostMapping("/file")
    @ResponseStatus(HttpStatus.CREATED)
    public FileDTOResponse create(@Valid @RequestBody FileDTORequest fileCreateDTO) {
        return fileService.createFile(fileCreateDTO);
    }

    @GetMapping("/file/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public GetFileDTOResponse show(@PathVariable UUID uuid) {
        return fileService.findFileByUuid(uuid);
    }

    @GetMapping("/file")
    @ResponseStatus(HttpStatus.OK)
    public Page<FileDTO> showAllFiles(@RequestParam int page, @RequestParam int size) {
        return fileService.findAllFilesPageable(page, size);
    }
}
