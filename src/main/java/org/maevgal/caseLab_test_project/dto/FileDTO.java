package org.maevgal.caseLab_test_project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class FileDTO {
    private UUID uuid;
    @NotBlank
    private String file;
    @NotBlank
    private String title;
    @NotNull
    private LocalDateTime creationDate;
    @NotBlank
    private String description;
}
