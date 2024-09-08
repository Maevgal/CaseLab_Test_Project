package org.maevgal.caseLab_test_project.mapper;

import org.maevgal.caseLab_test_project.dto.FileCreateDTO;
import org.maevgal.caseLab_test_project.model.File;
import org.maevgal.caseLab_test_project.repository.FileRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class FileMapper {
    @Autowired
    private FileRepository fileRepository;

    public abstract File map(FileCreateDTO dto);
}
