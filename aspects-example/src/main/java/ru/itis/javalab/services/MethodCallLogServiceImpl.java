package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.MethodLogDto;
import ru.itis.javalab.models.MethodLog;
import ru.itis.javalab.repositories.MethodCallLogRepository;

@Service
public class MethodCallLogServiceImpl implements MethodCallLogService {

    @Autowired
    private MethodCallLogRepository methodCallLogRepository;

    @Override
    public void logMethodCall(MethodLogDto methodLogDto) {
        methodCallLogRepository.save(MethodLog
                .builder()
                .methodName(methodLogDto.getMethodName())
                .callTime(methodLogDto.getCallTime())
                .build());
    }
}
