package ru.itis.javalab.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.models.CarNumb;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarNumbDto {
    private String value;
    private String region;

    public static CarNumbDto from(CarNumb carNumb){
        return CarNumbDto.builder()
                .value(carNumb.getNumbValue())
                .region("116 RUS")
                .build();
    }
}
