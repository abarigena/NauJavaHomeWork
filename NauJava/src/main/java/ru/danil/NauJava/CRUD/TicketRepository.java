package ru.danil.NauJava.CRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.danil.NauJava.CRUD.Interface.CrudRepository;
import ru.danil.NauJava.ent.Ticket;

import java.util.List;

@Component
public class TicketRepository implements CrudRepository<Ticket, Long>
{
    private final List<Ticket> ticketsConteiner;

    @Autowired
    public TicketRepository(List<Ticket> ticketsConteiner){
        this.ticketsConteiner = ticketsConteiner;
    }

    @Override
    public void create(Ticket ent) {
        ticketsConteiner.add(ent);
    }

    @Override
    public Ticket read(Long id) {
        for (int i = 0; i < ticketsConteiner.size(); i++) {
            if (ticketsConteiner.get(i).getId().equals(id)) {
                return ticketsConteiner.get(i);
            }
        }
        return null;
    }

    @Override
    public void update(Ticket ent) {
        for (int i = 0; i < ticketsConteiner.size(); i++) {
            if(ticketsConteiner.get(i).getId().equals(ent.getId())){
                ticketsConteiner.set(i, ent);
                return;
            }
        }
    }

    @Override
    public void delete(Long id) {
        Ticket ent = read(id);
        ticketsConteiner.remove(ent);
    }
}
