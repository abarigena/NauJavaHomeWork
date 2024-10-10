package ru.danil.NauJava.CRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.danil.NauJava.CRUD.Interface.CrudRepository;
import ru.danil.NauJava.Entities.Ticket;

import java.util.List;

@Component
public class TicketRepository implements CrudRepository<Ticket, Long> {
    private final List<Ticket> ticketsContainer;

    @Autowired
    public TicketRepository(List<Ticket> ticketsContainer) {
        this.ticketsContainer = ticketsContainer;
    }

    @Override
    public void create(Ticket ent) {
        ticketsContainer.add(ent);
    }

    @Override
    public Ticket read(Long id) {
        for (Ticket t : ticketsContainer) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }

    @Override
    public void update(Ticket ent) {
        for (int i = 0; i < ticketsContainer.size(); i++) {
            if (ticketsContainer.get(i).getId().equals(ent.getId())) {
                ticketsContainer.set(i, ent);
                return;
            }
        }
    }

    @Override
    public void delete(Long id) {
        Ticket ent = read(id);
        ticketsContainer.remove(ent);
    }
}
