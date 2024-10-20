package ru.danil.NauJava.Repository.hallRowRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.danil.NauJava.Entities.Hall.Hall;
import ru.danil.NauJava.Entities.HallRow.HallRow;

import java.util.List;
/**
 * Репозиторий для управления сущностями {@link HallRow}.
 * Предоставляет методы для выполнения CRUD-операций с объектами фильма в базе данных.
 */
public interface HallRowRepository extends CrudRepository<HallRow, Long> {

    /**
     * Находит все ряды в зале по его названию.
     *
     * @param hallName название зала, для которого нужно найти ряды
     * @return список рядов, относящихся к указанному залу
     */
    @Query("SELECT hr FROM HallRow hr WHERE hr.hall.name = :hallName")
    List<HallRow> findHallRowsByHallName(@Param("hallName") String hallName);

    /**
     * Находит ряд по его номеру и залу.
     *
     * @param row номер ряда
     * @param hall объект зала, к которому принадлежит ряд
     * @return объект {@link HallRow}, соответствующий указанному ряду и залу, или null, если такой ряд не найден
     */
    HallRow findByRowAndHall(long row, Hall hall);
}
