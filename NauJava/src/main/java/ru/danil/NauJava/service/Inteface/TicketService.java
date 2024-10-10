package ru.danil.NauJava.service.Inteface;

import ru.danil.NauJava.Entities.Ticket;

public interface TicketService {
    void createTicket(Long id, String filmName, String hall, String seat);

    Ticket findById(Long id);

    void deleteById(Long id);

    void updateById(Long id, String newFilmName, String newHall, String newSeat);
}
