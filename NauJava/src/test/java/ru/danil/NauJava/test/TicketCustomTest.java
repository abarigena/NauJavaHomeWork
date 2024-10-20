package ru.danil.NauJava.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.danil.NauJava.Entities.Ticket.Ticket;
import ru.danil.NauJava.Entities.User.User;
import ru.danil.NauJava.Repository.userRepository.UserRepository;
import ru.danil.NauJava.dao.TicketRepositoryImpl;

import java.util.List;
import java.util.UUID;

@SpringBootTest
class TicketCustomTest {
    private final TicketRepositoryImpl ticketRepositoryImpl;
    private final UserRepository userRepository;

    private String ticketUserFirstName;
    private String ticketUserLastName;
    private double ticketPrice;

    @Autowired
    TicketCustomTest(TicketRepositoryImpl ticketRepositoryImpl , UserRepository userRepository) {
        this.ticketRepositoryImpl = ticketRepositoryImpl;
        this.userRepository = userRepository;
    }

    @BeforeEach
    void setUp() {
        ticketUserFirstName = UUID.randomUUID().toString();
        ticketUserLastName = UUID.randomUUID().toString();
        ticketPrice = Math.random()*1000;

        User user = new User();
        user.setFirstName(ticketUserFirstName);
        user.setLastName(ticketUserLastName);

        userRepository.save(user);

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setPrice(ticketPrice);

        ticketRepositoryImpl.save(ticket);
    }

    @Test
    void testFindByPrice(){
        List<Ticket> tickets = ticketRepositoryImpl.findByPrice(ticketPrice);

        Assertions.assertFalse(tickets.isEmpty());
        Assertions.assertEquals(ticketPrice, tickets.getFirst().getPrice());
    }

    @Test
    void testFindByUser(){
        List<Ticket> tickets = ticketRepositoryImpl.findByUser(ticketUserFirstName, ticketUserLastName);

        Assertions.assertFalse(tickets.isEmpty());
        Ticket foundTicket = tickets.getFirst();
        Assertions.assertEquals(ticketUserFirstName, foundTicket.getUser().getFirstName());
        Assertions.assertEquals(ticketUserLastName, foundTicket.getUser().getLastName());
    }
}
