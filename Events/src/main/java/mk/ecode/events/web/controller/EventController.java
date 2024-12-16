package mk.ecode.events.web.controller;

import mk.ecode.events.model.Event;
import mk.ecode.events.model.Location;
import mk.ecode.events.service.EventService;
import mk.ecode.events.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EventController {

    private final EventService eventService;
    private final LocationService locationService;

    public EventController(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping("/events")
    public String getEventsPage(@RequestParam(required = false) String error,
                                @RequestParam(required = false) String searchText,
                                @RequestParam(required = false) String popularityScore,
                                @RequestParam(required = false) Long locationId,
                                Model model) {
        List<Location> locations = locationService.findAll();

        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("events", eventService.listAll());
            model.addAttribute("locations", locations);
            return "listEvents.html";
        }

        model.addAttribute("locations", locations);
        if (searchText != null || popularityScore != null) {
            model.addAttribute("events", eventService.filter(searchText, popularityScore));
        } else if (locationId != null) {
            model.addAttribute("events", eventService.findByLocationId(locationId));
        } else {
            model.addAttribute("events", eventService.listAll());
        }


        return "listEvents.html";
    }

    @GetMapping("/add-event")
    public String addEventForm(Model model) {
        model.addAttribute("locations", locationService.findAll());
        return "add-form.html";
    }

    @PostMapping("/events/add")
    public String saveEvent(@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam Double popularityScore,
                            @RequestParam Long locationId) {
        eventService.saveEvent(name, description, popularityScore, locationId);
        return "redirect:/events";
    }

    @GetMapping("/events/edit-form/{id}")
    public String editEventForm(@PathVariable Long id, Model model) {
        Event event = eventService.findById(id);
        if (event != null) {
            model.addAttribute("event", event);
            model.addAttribute("locations", locationService.findAll());
            return "add-form.html";
        }
        return "redirect:/events?error=notFound";
    }

    @PostMapping("/events/edit/{id}")
    public String editEvent(@PathVariable Long id,
                            @RequestParam String name,
                            @RequestParam String description,
                            @RequestParam Double popularityScore,
                            @RequestParam Long locationId) {
        eventService.editEvent(id, name, description, popularityScore, locationId);
        return "redirect:/events";
    }

    @GetMapping("/events/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.delete(id);
        return "redirect:/events";
    }
}
