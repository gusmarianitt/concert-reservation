package com.example.concertreservation.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity(name = "concert")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Concert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long concertId;
    private String concertCd;
    private String concertName;
    private Integer totalSeat;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "concert")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "concert")
    @JsonIgnore
    private List<Booking> bookingConcert;

    public List<Booking> getBookingConcert() {
        return bookingConcert;
    }

    public void setBookingConcert(List<Booking> bookingConcert) {
        this.bookingConcert = bookingConcert;
    }

    public String getConcertCd() {
        return concertCd;
    }

    public void setConcertCd(String concertCd) {
        this.concertCd = concertCd;
    }

    public Long getConcertId() {
        return concertId;
    }

    public void setConcertId(Long concertId) {
        this.concertId = concertId;
    }

    public String getConcertName() {
        return concertName;
    }

    public void setConcertName(String concertName) {
        this.concertName = concertName;
    }

    public Integer getTotalSeat() {
        return totalSeat;
    }

    public void setTotalSeat(Integer totalSeat) {
        this.totalSeat = totalSeat;
    }
}
