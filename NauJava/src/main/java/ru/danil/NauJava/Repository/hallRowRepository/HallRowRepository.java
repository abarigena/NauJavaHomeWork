package ru.danil.NauJava.Repository.hallRowRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.danil.NauJava.Entities.Hall.Hall;
import ru.danil.NauJava.Entities.HallRow.HallRow;

import java.util.List;

public interface HallRowRepository extends CrudRepository<HallRow, Long> {
    List<HallRow> findByHallId(long hallId);

    @Query("SELECT hr FROM HallRow hr WHERE hr.hall.name = :hallName")
    List<HallRow> findHallRowsByHallName(@Param("hallName") String hallNameParam);

    HallRow findByRowAndHall(long row, Hall hall);
}
