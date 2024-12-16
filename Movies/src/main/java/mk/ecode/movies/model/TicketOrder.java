package mk.ecode.movies.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TicketOrder {
    private String movieTitle;
    private String clientName;
    private String clientAddress;
    private Long numberOfTickets;
}
