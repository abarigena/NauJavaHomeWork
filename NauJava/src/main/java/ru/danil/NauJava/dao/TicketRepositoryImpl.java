package ru.danil.NauJava.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.danil.NauJava.Entities.Ticket.Ticket;
import ru.danil.NauJava.Entities.User.User;

import java.util.List;

@Repository
public class TicketRepositoryImpl implements TicketRepositoryCustom {

    private final EntityManager entityManager;

    @Autowired
    public TicketRepositoryImpl(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public List<Ticket> findByPrice(double price) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ticket> criteriaQuery = criteriaBuilder.createQuery(Ticket.class);

        Root<Ticket> ticketRoot = criteriaQuery.from(Ticket.class);
        Predicate predicate = criteriaBuilder.equal(ticketRoot.get("price"), price);

        criteriaQuery.select(ticketRoot).where(predicate);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Ticket> findByUser(String firstName, String lastName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ticket> criteriaQuery = criteriaBuilder.createQuery(Ticket.class);

        Root<Ticket> ticketRoot = criteriaQuery.from(Ticket.class);
        Join<Ticket, User> userJoin = ticketRoot.join("user", JoinType.INNER);
        Predicate firstNamePredicate = criteriaBuilder.equal(userJoin.get("firstName"), firstName);
        Predicate lastNamePredicate = criteriaBuilder.equal(userJoin.get("lastName"), lastName);

        criteriaQuery.select(ticketRoot).where(firstNamePredicate, lastNamePredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Ticket save(Ticket ticket) {
        if(ticket.getId() == null){
            entityManager.persist(ticket);
            return ticket;
        }else {
            return entityManager.merge(ticket);
        }
    }

    @Override
    public void delete(Ticket ticket) {
        ticket = entityManager.merge(ticket);
        entityManager.remove(ticket);
    }
}
