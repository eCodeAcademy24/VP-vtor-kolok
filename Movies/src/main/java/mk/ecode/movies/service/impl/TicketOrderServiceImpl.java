package mk.ecode.movies.service.impl;

import mk.ecode.movies.model.TicketOrder;
import mk.ecode.movies.service.TicketOrderService;
import org.springframework.stereotype.Service;

@Service
public class TicketOrderServiceImpl implements TicketOrderService {
    @Override
    public TicketOrder placeOrder(String movieTitle, String clientName, String address, int numberOfTickets) {
        return new TicketOrder(movieTitle, clientName, address, (long) numberOfTickets);
    }
}
