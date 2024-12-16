package mk.ecode.events.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ecode.events.model.Event;
import mk.ecode.events.model.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Event> events = new ArrayList<>();
    public static List<Location> locations = new ArrayList<>();

    @PostConstruct
    public void init() {
        Location location1 = new Location("Loc 1", "Addr 1", "Cap 1", "Desc 1");
        Location location2 = new Location("Loc 2", "Addr 2", "Cap 2", "Desc 2");
        Location location3 = new Location("Loc 3", "Addr 3", "Cap 3", "Desc 3");
        Location location4 = new Location("Loc 4", "Addr 4", "Cap 4", "Desc 4");
        Location location5 = new Location("Loc 5", "Addr 5", "Cap 5", "Desc 5");
        locations.add(location1);
        locations.add(location2);
        locations.add(location3);
        locations.add(location4);
        locations.add(location5);

        events.add(new Event("Test 1", "Desc 1", 2.50, location1));
        events.add(new Event("Test 2", "Desc 2", 5.50, location2));
        events.add(new Event("Test 3", "Desc 3", 3.50, location3));
        events.add(new Event("Test 4", "Desc 4", 1.50, location4));
        events.add(new Event("Test 5", "Desc 5", 2.34, location5));
        events.add(new Event("Test 6", "Desc 6", 8.55, location1));
        events.add(new Event("Test 7", "Desc 7", 1.50, location2));
        events.add(new Event("Test 8", "Desc 8", 2.50, location3));
        events.add(new Event("Test 9", "Desc 9", 3.50, location4));
        events.add(new Event("Test 10", "Desc 10", 6.50, location5));
    }
}
