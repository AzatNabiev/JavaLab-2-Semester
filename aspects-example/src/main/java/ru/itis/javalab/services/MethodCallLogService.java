package ru.itis.javalab.services;

import ru.itis.javalab.dto.MethodLogDto;

public interface MethodCallLogService {
    void logMethodCall(MethodLogDto methodLogDto);
}
