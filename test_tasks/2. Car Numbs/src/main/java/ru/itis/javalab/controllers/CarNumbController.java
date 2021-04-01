package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.dto.CarNumbDto;
import ru.itis.javalab.services.CarNumbService;

@RestController
public class CarNumbController {

    @Autowired
    CarNumbService carNumbService;

    @GetMapping("number/next")
    public String getNextNumb(){
        StringBuilder string = new StringBuilder();
        CarNumbDto carNumbDto = carNumbService.getNextNumb();
        string.append(carNumbDto.getValue()).append(" ").append(carNumbDto.getRegion());
        return string.toString();
    }
    @GetMapping("number/random")
    public String getRandomNumb() {
        StringBuilder string = new StringBuilder();
        CarNumbDto carNumbDto = carNumbService.getRandomNumb();
        string.append(carNumbDto.getValue()).append(" ").append(carNumbDto.getRegion());
        return string.toString();
    }


}
