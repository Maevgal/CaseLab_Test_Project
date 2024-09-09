package org.maevgal.caseLab_test_project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "storage")
public class File {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String file;
    private String title;
    private LocalDateTime creationDate;
    private String description;
    private UUID uuid;
}
