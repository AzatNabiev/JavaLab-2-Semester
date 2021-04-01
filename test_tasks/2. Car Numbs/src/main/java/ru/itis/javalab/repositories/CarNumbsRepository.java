package ru.itis.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.models.CarNumb;
import java.util.Optional;

@Repository
public interface CarNumbsRepository extends JpaRepository<CarNumb,Long> {
    @Query(value = "SELECT new ru.itis.javalab.models.CarNumb(cn.id,cn.numbValue) from CarNumb as cn where cn.id=(select max(cn_2.id) from CarNumb as cn_2)")
    Optional<CarNumb> getCarNumbWithMaxId();
    Optional<CarNumb> getCarNumbByNumbValue(String value);
}
