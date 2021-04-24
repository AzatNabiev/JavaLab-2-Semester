package ru.itis.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.models.Doctor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorDto {
    private String firstName;
    private String lastName;
    private Long age;
    private Long experience;
    private List<UserDto> clients;

    public static DoctorDto from(Doctor doctor){
        DoctorDto doctorDto = DoctorDto.builder()
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .age(doctor.getAge())
                .experience(doctor.getExperience()).build();

        if (doctor.getUsers() != null){
            doctorDto.setClients((doctor.getUsers().stream().map(c ->
                    UserDto.builder().firstName(c.getFirstName()).lastName(c.getLastName()).diseases(c.getDiseases()).build())
                    .collect(Collectors.toList())));
        }
        return doctorDto;
    }

    public static List<DoctorDto> from(List<Doctor> teachers) {
        return teachers.stream().map(DoctorDto::from).collect(Collectors.toList());
    }


}
