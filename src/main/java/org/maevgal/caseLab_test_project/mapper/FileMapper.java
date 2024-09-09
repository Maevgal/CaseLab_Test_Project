package org.maevgal.caseLab_test_project.mapper;

import org.maevgal.caseLab_test_project.dto.FileDTO;
import org.maevgal.caseLab_test_project.dto.FileDTORequest;
import org.maevgal.caseLab_test_project.dto.GetFileDTOResponse;
import org.maevgal.caseLab_test_project.model.File;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class FileMapper {
    public abstract File map(FileDTORequest dto);

    public abstract GetFileDTOResponse map(File model);

    public abstract FileDTO mapToDTO(File model);
}
