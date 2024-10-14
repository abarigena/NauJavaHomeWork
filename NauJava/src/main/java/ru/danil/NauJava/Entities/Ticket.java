package ru.danil.NauJava.Entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Ticket {
    private Long id;

    private String filmName; // Обьеденить LocalDateTime, в сервисе вынести в один метод, не создавать новый обьект тикет в update

    private LocalDateTime dateTime;

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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = dateTime.format(formatter);

        return "Ticket{" +
                "id=" + id +
                ", filmName='" + filmName + '\'' +
                ", dateTime=" + formattedDateTime +
                ", hall='" + hall + '\'' +
                ", seat='" + seat + '\'' +
                ", isSold=" + isSold +
                '}';
    }
}
