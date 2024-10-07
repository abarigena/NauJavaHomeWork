package ru.danil.NauJava.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.danil.NauJava.CRUD.TicketRepository;
import ru.danil.NauJava.ent.Ticket;
import ru.danil.NauJava.service.Inteface.TicketService;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void createTicket(Long id, String filmName, String hall, String seat) {
        Ticket newTicket = new Ticket();
        newTicket.setId(id);
        newTicket.setHall(hall);
        newTicket.setSeat(seat);
        newTicket.setFilmName(filmName);

        newTicket.setDate(LocalDate.now());
        newTicket.setTime(LocalTime.now());
        newTicket.setSold(true);

        ticketRepository.create(newTicket);


    }

    @Override
    public Ticket findById(Long id) {
        return ticketRepository.read(id);
    }

    @Override
    public void deleteById(Long id) {
        ticketRepository.delete(id);
    }

    @Override
    public void updateById(Long id, String newFilmName, String newHall, String newSeat) {
        Ticket newTicket = new Ticket();
        newTicket.setId(id);
        newTicket.setHall(newHall);
        newTicket.setSeat(newSeat);
        newTicket.setFilmName(newFilmName);

        newTicket.setDate(LocalDate.now());
        newTicket.setTime(LocalTime.now());
        newTicket.setSold(true);

        ticketRepository.update(newTicket);
    }
}
