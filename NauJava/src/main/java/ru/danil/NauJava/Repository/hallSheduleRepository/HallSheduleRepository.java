package ru.danil.NauJava.Repository.hallSheduleRepository;

import org.springframework.data.repository.CrudRepository;
import ru.danil.NauJava.Entities.HallRow.HallRow;
import ru.danil.NauJava.Entities.HallShedule.HallShedule;
/**
 * Репозиторий для управления сущностями {@link HallShedule}.
 * Предоставляет методы для выполнения CRUD-операций с объектами фильма в базе данных.
 */
public interface HallSheduleRepository extends CrudRepository<HallShedule, Long> {
}
