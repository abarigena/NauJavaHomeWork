package ru.danil.NauJava.Repository.ticketRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.danil.NauJava.Entities.Ticket.Ticket;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

    List<Ticket> findByPrice(double price);

    @Query("select t from Ticket t join t.user u where u.firstName = :userFirstName and u.lastName = :userLastName")
    List<Ticket> findByUser(String userFirstName, String userLastName);
}
