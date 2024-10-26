package ru.danil.NauJava.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.danil.NauJava.Entities.Ticket.Ticket;
import ru.danil.NauJava.dao.TicketRepositoryImpl;

import java.util.List;

/**
 * Контроллер для управления билетами.
 * Этот контроллер предоставляет RESTful API для поиска билетов по различным критериям,
 * таким как цена и имя пользователя. Он использует {@link TicketRepositoryImpl}
 */
@RestController
@RequestMapping("/custom/tickets")
public class TicketController {


    private final TicketRepositoryImpl ticketRepositoryImpl;

    @Autowired
    public TicketController(TicketRepositoryImpl ticketRepositoryImpl) {
        this.ticketRepositoryImpl = ticketRepositoryImpl;
    }

    @GetMapping("/findByPrice")
    public List<Ticket> findByPrice(@RequestParam double price) {
        return ticketRepositoryImpl.findByPrice(price);
    }

    @GetMapping("/findByUser")
    public List<Ticket> findByUser(@RequestParam String firstName, @RequestParam String lastName) {
        return ticketRepositoryImpl.findByUser(firstName, lastName);
    }
}
