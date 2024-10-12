package ru.danil.NauJava.Entities.HallShedule;

import jakarta.persistence.*;
import ru.danil.NauJava.Entities.Film.Film;
import ru.danil.NauJava.Entities.Hall.Hall;

import java.time.LocalDate;

@Entity
@Table(name = "hallShedules")
public class HallShedule {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private LocalDate startDate;

    @ManyToOne
    private Film film;

    @ManyToOne
    private Hall hall;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    @Override
    public String toString() {
        return "HallShedule{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", film=" + film +
                ", hall=" + hall +
                '}';
    }
}
