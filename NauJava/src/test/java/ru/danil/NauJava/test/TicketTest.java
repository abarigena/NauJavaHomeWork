package ru.danil.NauJava.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.danil.NauJava.Entities.Ticket.Ticket;
import ru.danil.NauJava.Entities.User.User;
import ru.danil.NauJava.Repository.ticketRepository.TicketRepository;
import ru.danil.NauJava.Repository.userRepository.UserRepository;

import java.util.List;
import java.util.UUID;

@SpringBootTest
class TicketTest {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    @Autowired
    TicketTest(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @Test
    void testFindByPrice()
    {
        double ticketPrice = Math.random() * 1000;

        Ticket ticket = new Ticket();
        ticket.setPrice(ticketPrice);
        ticketRepository.save(ticket);

        Ticket foundTicket = ticketRepository.findByPrice(ticketPrice).getFirst();

        Assertions.assertNotNull(foundTicket);
        Assertions.assertEquals(ticketPrice, foundTicket.getPrice());
    }

    @Test
    void testFindByUser(){
        String ticketUserFirstName = UUID.randomUUID().toString();
        String ticketUserLastName = UUID.randomUUID().toString();

        User user = new User();
        user.setFirstName(ticketUserFirstName);
        user.setLastName(ticketUserLastName);

        userRepository.save(user);

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticketRepository.save(ticket);

        List<Ticket> foundTickets = ticketRepository.findByUser(ticketUserFirstName,ticketUserLastName);

        Assertions.assertFalse(foundTickets.isEmpty());

        Ticket foundTicket = foundTickets.get(0);
        Assertions.assertEquals(ticketUserFirstName, foundTicket.getUser().getFirstName());
        Assertions.assertEquals(ticketUserLastName, foundTicket.getUser().getLastName());
    }
}
