package org.maevgal.caseLab_test_project.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
public class FileDTOResponse {
    private UUID uuid;
}
