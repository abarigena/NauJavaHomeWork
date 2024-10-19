package ru.danil.NauJava.Entities.Hall;


import jakarta.persistence.*;

/**
 * Сущность заллов в базе данных
 */
@Entity
@Table(name = "halls")
public class Hall {
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Название зала. Должно быть уникальным, чтобы избежать дублирования залов в базе данных.
     */
    @Column(unique = true)
    private String name;

    @Column
    private boolean active;

    /**
     * Геттер и сеттер для каждого поля
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + active +
                '}';
    }
}
