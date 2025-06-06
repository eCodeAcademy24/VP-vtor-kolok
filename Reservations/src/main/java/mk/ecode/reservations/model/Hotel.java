package mk.ecode.reservations.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Hotel {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Hotel(String name) {
        this.name = name;
    }
}
