package ru.danil.NauJava.Entities.Film;

import jakarta.persistence.*;

@Entity
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String title;

    @Column
    private int minAge;

    @Column
    private int duration;

    @Column
    private double priceCoefficent;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPriceCoefficent() {
        return priceCoefficent;
    }

    public void setPriceCoefficent(double priceCoefficent) {
        this.priceCoefficent = priceCoefficent;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", minAge=" + minAge +
                ", duration=" + duration +
                ", priceCoefficent=" + priceCoefficent +
                '}';
    }
}
