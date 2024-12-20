package mk.ecode.artists.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Artist {
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;

    public Artist(String firstName, String lastName, String bio) {
        this.id = (long) (Math.random() * 1000);
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
    }
}
