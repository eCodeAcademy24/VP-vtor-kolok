package mk.ecode.events.web.controller;

import mk.ecode.events.model.EventBooking;
import mk.ecode.events.service.EventBookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EventBookingController {

    private final EventBookingService eventBookingService;

    public EventBookingController(EventBookingService eventBookingService) {
        this.eventBookingService = eventBookingService;
    }

    @PostMapping("/eventBooking")
    public String bookingConfirmation(@RequestParam String selectedEvent, @RequestParam String numTickets, Model model) {
        EventBooking eventBooking = eventBookingService.placeBooking(selectedEvent, "Test", "Addr", Integer.parseInt(numTickets));

        model.addAttribute("eventBooking", eventBooking);
        return "bookingConfirmation.html";
    }
}
