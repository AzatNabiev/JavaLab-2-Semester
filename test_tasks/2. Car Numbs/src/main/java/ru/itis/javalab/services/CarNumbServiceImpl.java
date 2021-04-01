package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.CarNumbDto;
import ru.itis.javalab.models.CarNumb;
import ru.itis.javalab.repositories.CarNumbsRepository;

import java.util.*;

@Service
public class CarNumbServiceImpl implements CarNumbService {

    @Autowired
    private CarNumbsRepository carNumbsRepository;

    @Override
    public CarNumbDto getNextNumb() {
        List<Integer> list = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0));
        Optional<CarNumb> carNumb;
        boolean isFound = false;
        while (!isFound){
            if (list.stream().mapToInt(a -> a).sum()!=60) {
                carNumb = carNumbsRepository.getCarNumbByNumbValue(getNumb(list));
                if (carNumb.isPresent()) {
                    next(list);
                } else {
                    CarNumb carNumbForSaving = CarNumb.builder().numbValue(getNumb(list)).build();
                    carNumbsRepository.save(carNumbForSaving);
                    isFound = true;
                    next(list);
                    return CarNumbDto.from(carNumbForSaving);
                }
            } else {
                isFound = true;
                return CarNumbDto.builder().value("You reach the limit").build();
            }
        }
        return null;
    }

    @Override
    public CarNumbDto getRandomNumb() {
        boolean isUnique = true;
        while (isUnique){
            List<Integer> list = getRandomArray();
            String value = getNumb(list);
            Optional<CarNumb> carNumbOpt = carNumbsRepository.getCarNumbByNumbValue(value);
            if (!carNumbOpt.isPresent()){
                CarNumb carNumb = CarNumb.builder().numbValue(value).build();
                carNumbsRepository.save(carNumb);
                isUnique = false;
                return CarNumbDto.from(carNumb);
            }
        }
        return null;
    }

    private List<Integer> getRandomArray(){
        int val0 = (int)(Math.random()*12);
        int val1 = (int)(Math.random()*10);
        int val2 = (int)(Math.random()*10);
        int val3 = (int)(Math.random()*10);
        int val4 = (int)(Math.random()*12);
        int val5 = (int)(Math.random()*12);
        return new ArrayList<>(Arrays.asList(val0,val1,val2,val3,val4,val5));
    }


    private void next(List<Integer> list) {
        int maxSum = 60;
        int arraySum = list.stream().mapToInt(a -> a).sum();
        if (maxSum != arraySum) {
            if (list.get(3) != 9) {
                list.set(3, list.get(3) + 1);
            } else if (list.get(2) != 9) {
                list.set(3, 0);
                list.set(2, list.get(2) + 1);
            } else if (list.get(1) != 9) {
                list.set(3,0);
                list.set(2,0);
                list.set(1, list.get(1) + 1);
            } else {
                list.set(1, 0);
                list.set(2, 0);
                list.set(3, 0);
                if (list.get(5) != 11){
                    list.set(5,list.get(5)+1);
                } else if (list.get(4)!=11){
                    list.set(5,0);
                    list.set(4,list.get(4)+1);
                } else if (list.get(0)!=11){
                    list.set(5,0);
                    list.set(4,0);
                    list.set(0,list.get(0)+1);
                }
            }
        }
    }
    private String getNumb(List<Integer> list){
        Map<Integer, Character> map = new HashMap<>();
        StringBuilder string = new StringBuilder();
        map.put(0,'A');map.put(1,'E');map.put(2,'T');map.put(3,'O');map.put(4,'P');map.put(5,'H');
        map.put(6,'У');map.put(7,'K');map.put(8,'X');map.put(9,'C');map.put(10,'B');map.put(11,'M');
        string.append(map.get(list.get(0))).append(list.get(1)).append(list.get(2))
                .append(list.get(3)).append(map.get(list.get(4))).append(map.get(list.get(5)));
        return string.toString();
    }
}
