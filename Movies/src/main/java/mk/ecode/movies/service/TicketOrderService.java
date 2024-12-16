package mk.ecode.movies.service;

import mk.ecode.movies.model.TicketOrder;

public interface TicketOrderService {

    TicketOrder placeOrder(String movieTitle, String clientName, String address, int numberOfTickets);
}
