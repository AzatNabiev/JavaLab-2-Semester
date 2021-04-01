package ru.itis.javalab.services;

import ru.itis.javalab.dto.CarNumbDto;

public interface CarNumbService {
    CarNumbDto getNextNumb();
    CarNumbDto getRandomNumb();
}
