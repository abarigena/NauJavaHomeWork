package ru.danil.NauJava.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.danil.NauJava.CRUD.TicketRepository;
import ru.danil.NauJava.Entities.Ticket;
import ru.danil.NauJava.service.Inteface.TicketService;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        initTicket(newTicket, id, filmName, hall, seat);
        newTicket.setDateTime(LocalDateTime.now());
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
        Ticket ticket = ticketRepository.read(id);
        initTicket(ticket, id, newFilmName, newHall, newSeat);
        ticketRepository.update(ticket);
    }
    private void initTicket(Ticket ticket, Long id, String filmName, String hall, String seat){
        ticket.setId(id);
        ticket.setHall(hall);
        ticket.setSeat(seat);
        ticket.setFilmName(filmName);
    }
}
