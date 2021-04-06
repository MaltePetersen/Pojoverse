package de.fh.kiel.advancedjava.pojomodel.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket {
    private Long id;
    private String concertName;
    private String firstName;
    private String surName;
    private LocalDateTime dateTime;

    public Ticket(){

    }
    public Ticket(Long id, String concertName, String firstName, String surName, LocalDateTime dateTime) {
        this.id = id;
        this.concertName = concertName;
        this.firstName = firstName;
        this.surName = surName;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConcertName() {
        return concertName;
    }

    public void setConcertName(String concertName) {
        this.concertName = concertName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket that = (Ticket) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(concertName, that.concertName) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(surName, that.surName) &&
                Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, concertName, firstName, surName, dateTime);
    }

    @Override
    public String toString() {
        return "ConcertTicket{" +
                "id=" + id +
                ", concertName='" + concertName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }

}
