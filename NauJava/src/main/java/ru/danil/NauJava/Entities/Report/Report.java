package ru.danil.NauJava.Entities.Report;

import jakarta.persistence.*;

/**
 * Сущность отчета в базе данных
 */
@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column
    private ReportStatus status;

    @Column(columnDefinition = "TEXT")
    private String content;

    /**
     * Геттеры и сеттеры для каждого поля
     */

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
