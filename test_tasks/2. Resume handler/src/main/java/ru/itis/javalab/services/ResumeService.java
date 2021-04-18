package ru.itis.javalab.services;

import ru.itis.javalab.dto.ResumeDto;
import ru.itis.javalab.models.Resume;

import java.util.List;
import java.util.Optional;

public interface ResumeService {
    void save(ResumeDto resumeDto);
    List<ResumeDto> getAll();
    Optional<Resume> getResumeDtoById(String resumeId);
    void deleteResumeById(String resumeId);
    void changeResumeById(ResumeDto resumeDto);
}
