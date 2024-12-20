package mk.ecode.artists.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;

    @OneToMany(mappedBy = "song")
    private List<Artist> artists;

    @ManyToOne
    private Album album;
}
