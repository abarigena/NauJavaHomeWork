package ru.danil.NauJava.Entities.Ticket;


import jakarta.persistence.*;
import ru.danil.NauJava.Entities.HallShedule.HallShedule;
import ru.danil.NauJava.Entities.User.User;


@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private HallShedule hallShedule;

    @Column
    private double price;

    @Column
    private int row;

    @Column
    private int seat;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HallShedule getHallShedule() {
        return hallShedule;
    }

    public void setHallShedule(HallShedule hallShedule) {
        this.hallShedule = hallShedule;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", user=" + user +
                ", hallShedule=" + hallShedule +
                ", price=" + price +
                ", row=" + row +
                ", seat=" + seat +
                '}';
    }
}
