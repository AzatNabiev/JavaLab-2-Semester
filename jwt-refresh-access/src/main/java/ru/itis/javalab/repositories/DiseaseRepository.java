package ru.itis.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.models.Disease;

public interface DiseaseRepository extends JpaRepository<Disease, Long> {
}
