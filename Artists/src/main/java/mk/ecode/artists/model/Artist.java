package mk.ecode.artists.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Artist {
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;
}
