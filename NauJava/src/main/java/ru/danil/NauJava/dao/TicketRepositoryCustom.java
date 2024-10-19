package ru.danil.NauJava.dao;

import ru.danil.NauJava.Entities.Ticket.Ticket;

import java.util.List;

/**
 * Интерфейс для пользовательского репозитория билетов {@link Ticket}.
 * Предоставляет методы для выполнения операций поиска, сохранения и удаления билетов.
 */
public interface TicketRepositoryCustom {

    /**
     * Находит все билеты с указанной ценой.
     *
     * @param price цена билетов, которые нужно найти
     * @return список билетов с указанной ценой
     */
    List<Ticket> findByPrice(double price);

    /**
     * Находит все билеты, принадлежащие пользователю с указанным именем и фамилией.
     *
     * @param userFirstName имя пользователя, чьи билеты нужно найти
     * @param userLastName фамилия пользователя, чьи билеты нужно найти
     * @return список билетов, принадлежащих указанному пользователю
     */
    List<Ticket> findByUser(String userFirstName, String userLastName);

    /**
     * Сохраняет билет в базе данных.
     *
     * @param ticket объект {@link Ticket}, который необходимо сохранить
     * @return сохраненный объект билета
     */
    Ticket save(Ticket ticket);

    /**
     * Удаляет указанный билет из базы данных.
     *
     * @param ticket объект {@link Ticket}, который необходимо удалить
     */
    void delete(Ticket ticket);
}
