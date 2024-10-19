package ru.danil.NauJava.Entities.HallRow;

import jakarta.persistence.*;
import ru.danil.NauJava.Entities.Hall.Hall;

/**
 * Сущность рядов в базе данных
 *
 * @UniqueConstraint уникальность пары по ряду и залу
 */

@Entity
@Table(name = "hallRows", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"row", "hall_id"})
})
public class HallRow {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private int row;

    @Column
    private int seatCount;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    /**
     * Геттер и сеттер для каждого поля
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    @Override
    public String toString() {
        return "HallRow{" +
                "id=" + id +
                ", row=" + row +
                ", seatCount=" + seatCount +
                ", hall=" + hall +
                '}';
    }
}
