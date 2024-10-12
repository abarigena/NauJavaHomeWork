package ru.danil.NauJava.dao;

import ru.danil.NauJava.Entities.Ticket.Ticket;

import java.util.List;

public interface TicketRepositoryCustom {


    List<Ticket> findByPrice(double price);

    List<Ticket> findByUser(String userFirstName, String userLastName);

    Ticket save(Ticket ticket);

    void delete(Ticket ticket);
}
