package ru.danil.NauJava.ent;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Ticket
{
    private Long id;

    private String filmName;

    private LocalDate date;

    private LocalTime time;

    private String hall;

    private String seat;

    private Boolean isSold;

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public Boolean getSold() {
        return isSold;
    }

    public void setSold(Boolean sold) {
        isSold = sold;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", filmName='" + filmName + '\'' +
                ", date=" + date +
                ", time=" + time.truncatedTo(ChronoUnit.MINUTES) +
                ", hall='" + hall + '\'' +
                ", seat='" + seat + '\'' +
                ", isSold=" + isSold +
                '}';
    }
}
