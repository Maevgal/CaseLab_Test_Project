package org.maevgal.caseLab_test_project.repository;

import org.maevgal.caseLab_test_project.model.File;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FileRepository extends CrudRepository<File, Long> {
    Optional<File> findByUuid(UUID uuid);
}
