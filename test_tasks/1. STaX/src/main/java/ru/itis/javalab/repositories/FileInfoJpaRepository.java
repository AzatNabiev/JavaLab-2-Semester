package ru.itis.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.models.FileInfo;

@Repository
public interface FileInfoJpaRepository extends JpaRepository<FileInfo,Long> {
}
