package ru.itis.javalab.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itis.javalab.models.Resume;

public interface ResumeRepository extends MongoRepository<Resume,String> {
}
