package mk.ecode.reservations.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    private String guestName;

    private LocalDate dateCreated;

    private Integer daysOfStay;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @ManyToOne
    private Hotel hotel;

    public Reservation(String guestName, LocalDate dateCreated, Integer daysOfStay, RoomType roomType, Hotel hotel) {
        this.guestName = guestName;
        this.dateCreated = dateCreated;
        this.daysOfStay = daysOfStay;
        this.roomType = roomType;
        this.hotel = hotel;
    }
}
