package mk.ecode.artists.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;

    @ManyToOne
    private Song song;

    public Artist(String firstName, String lastName, String bio) {
        this.id = (long) (Math.random() * 1000);
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
    }
}
