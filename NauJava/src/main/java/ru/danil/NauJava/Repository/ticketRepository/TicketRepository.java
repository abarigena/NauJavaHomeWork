package ru.danil.NauJava.Repository.ticketRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.danil.NauJava.Entities.HallRow.HallRow;
import ru.danil.NauJava.Entities.Ticket.Ticket;

import java.util.List;

/**
 * Репозиторий для управления сущностями {@link Ticket}.
 * Предоставляет методы для выполнения CRUD-операций с объектами фильма в базе данных.
 */
public interface TicketRepository extends CrudRepository<Ticket, Long> {

    /**
     * Находит все билеты с указанной ценой.
     *
     * @param price цена билетов, которые нужно найти
     * @return список билетов с указанной ценой
     */
    List<Ticket> findByPrice(double price);

    /**
     * Находит все билеты, принадлежащие пользователю по его номеру телефона.
     *
     * @param phoneNumber номер телефона пользователя, чьи билеты нужно найти
     * @return список билетов, принадлежащих пользователю с указанным номером телефона
     */
    @Query("select t from Ticket t where t.user.phoneNumber = :phoneNumber ")
    List<Ticket> findByUser(String phoneNumber);
}
