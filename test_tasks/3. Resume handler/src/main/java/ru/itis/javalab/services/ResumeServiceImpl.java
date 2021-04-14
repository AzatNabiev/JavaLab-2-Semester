package ru.itis.javalab.services;

import com.fasterxml.jackson.datatype.jsr310.ser.DurationSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.ResumeDto;
import ru.itis.javalab.models.Resume;
import ru.itis.javalab.repositories.ResumeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Override
    public void save(ResumeDto resumeDto) {
        resumeRepository.save(Resume.builder().firstName(resumeDto.getFirstName())
                .lastName(resumeDto.getLastName()).email(resumeDto.getEmail())
                .telegram(resumeDto.getTelegram()).age(resumeDto.getAge()).
                        aboutYourself(resumeDto.getAboutYourself()).build());

    }

    @Override
    public List<ResumeDto> getAll() {
        return ResumeDto.from(resumeRepository.findAll());
    }

    @Override
    public Optional<Resume> getResumeDtoById(String resumeId) {
        Optional<Resume> resume = resumeRepository.findById(resumeId);
        return resume;
    }

    @Override
    public void deleteResumeById(String resumeId) {
        resumeRepository.deleteById(resumeId);
    }

    @Override
    public void changeResumeById(ResumeDto resumeDto) {
            resumeRepository.save(Resume.builder()._id(resumeDto.get_id())
            .firstName(resumeDto.getFirstName()).lastName(resumeDto.getLastName()).email(resumeDto.getEmail())
                    .age(resumeDto.getAge()).telegram(resumeDto.getTelegram())
                    .aboutYourself(resumeDto.getAboutYourself()).build());
    }
}

