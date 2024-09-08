package org.maevgal.caseLab_test_project.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table(name = "storage")
public class File {
    @Id
    private Long id;
    private String file;
    private String title;
    private LocalDateTime creationDate;
    private String description;
    private UUID uuid;
}
