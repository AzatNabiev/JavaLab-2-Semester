package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.CarNumbDto;
import ru.itis.javalab.models.CarNumb;
import ru.itis.javalab.repositories.CarNumbsRepository;
import java.util.*;

@Service
public class CarNumbServiceImpl implements CarNumbService {

    @Autowired
    private CarNumbsRepository carNumbsRepository;

    private Random random;
    private Integer currentVal;
    private Integer[] mas;
    private List<Character> list;
    private Integer maxSize;

    public CarNumbServiceImpl(){
        currentVal=0;
        mas = new Integer[]{0,0,0};
        random = new Random();
        list = new ArrayList<>(Arrays.asList('A','E','T','O','P','H','У','K','X','C','B','M'));
        maxSize=1033;
    }

    @Override
    public CarNumbDto getNextNumb() {
        while (true) {
            if (currentVal + mas[0] + mas[1] + mas[2] != maxSize) {
                String val = getNumb(currentVal, mas);
                CarNumb carNumb = CarNumb.builder().numbValue(val).build();
                try {
                    carNumbsRepository.save(carNumb);
                    return CarNumbDto.from(carNumb);
                } catch (DataAccessException e) {
                    next();
                }
            } else {
                throw new IllegalArgumentException("Limit has been reached.");
            }
        }
    }

    @Override
    public CarNumbDto getRandomNumb() throws DataAccessException {
        while (true){
            if (currentVal+mas[0]+mas[1]+mas[2] != maxSize){
                String randomVal = getRandomArray();
                CarNumb carNumb = CarNumb.builder().numbValue(randomVal).build();
                    carNumbsRepository.save(carNumb);
                    return CarNumbDto.from(carNumb);
            } else {
                throw new IllegalArgumentException("Limit has been reached.");
            }
        }
    }

    private String getRandomArray(){
        int val0 = random.nextInt(12);
        int val1 = random.nextInt(12);
        int val2 = random.nextInt(12);
        int val3 = random.nextInt(1000);
        return getNumb(val3, new Integer[]{val0,val1,val2});
    }


    private void next() {
        if (currentVal != 1000) {
            currentVal += 1;
        } else {
            currentVal = 0;
            if (mas[2] != 12) {
                mas[2] += 1;
            } else if (mas[1] != 12) {
                mas[2] = 0;
                mas[1] += 1;
            } else if (mas[0] != 12) {
                mas[2] = 0;
                mas[1] = 0;
                mas[0] += 1;
            }
        }
    }
    private String getNumb(Integer currentVal, Integer[] mas) {
        StringBuilder string = new StringBuilder();
        string.append(list.get(mas[0])).append(currentVal / 100 % 10).append(currentVal / 10 % 10).append(currentVal % 10).append(list.get(mas[1]))
                .append(list.get(mas[2]));
        return string.toString();
    }
}
