package ru.danil.NauJava.Repository.reportRepository;

import org.springframework.data.repository.CrudRepository;
import ru.danil.NauJava.Entities.Report.Report;

/**
 * Репозиторий для управления сущностями {@link Report}.
 * Предоставляет методы для выполнения CRUD-операций с объектами фильма в базе данных.
 */
public interface ReportRepository extends CrudRepository<Report, Long> {
}
