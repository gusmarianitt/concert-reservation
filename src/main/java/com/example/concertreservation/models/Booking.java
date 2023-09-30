package com.example.concertreservation.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

@Entity(name = "booking")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private String bookingStatus;
    private Integer seatBooked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="userId")
    @JoinColumn(name = "user_id", nullable = false)
    @JsonProperty("user_id")
    private Usr usr;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="concertId")
//    @JoinColumn(name = "concert_id", nullable = false)
    @JoinColumn(name = "concert_id")
    @JsonProperty("concert_id")
    private Concert concert;

    public Concert getConcert() {
        return concert;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }

    public Usr getUsr() {
        return usr;
    }

    public void setUsr(Usr usr) {
        this.usr = usr;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Integer getSeatBooked() {
        return seatBooked;
    }

    public void setSeatBooked(Integer seatBooked) {
        this.seatBooked = seatBooked;
    }

}
