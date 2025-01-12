package mk.ecode.servers.web;

import mk.ecode.servers.model.OSType;
import mk.ecode.servers.model.VirtualServer;
import mk.ecode.servers.service.UserService;
import mk.ecode.servers.service.VirtualServerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping
public class VirtualServerController {

    private final VirtualServerService service;
    private final UserService userService;

    public VirtualServerController(VirtualServerService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    /**
     * This method should use the "list.html" template to display all entities.
     * The method should be mapped on paths '/' and '/VirtualServers'.
     * The arguments that this method takes are optional and can be 'null'.
     *
     * @return The view "list.html".
     */
    @GetMapping({"/", "/VirtualServers"})
    public String showList(@RequestParam(required = false) Integer activeMoreThanDays,
                           @RequestParam(required = false) Long ownerId, Model model) {
        List<VirtualServer> virtualServers;
        if (ownerId == null && activeMoreThanDays == null) {
            virtualServers = service.listAll();
        } else {
            virtualServers = service.filter(ownerId, activeMoreThanDays);
        }

        model.addAttribute("servers", virtualServers);
        model.addAttribute("users", userService.listAll());
        return "list";
    }

    /**
     * This method should display the "form.html" template.
     * The method should be mapped on path '/VirtualServers/add'.
     *
     * @return The view "form.html".
     */
    @GetMapping("/VirtualServers/add")
    public String showAdd(Model model) {
        model.addAttribute("users", userService.listAll());
        model.addAttribute("types", OSType.values());

        return "form";
    }

    /**
     * This method should display the "form.html" template.
     * However, in this case all 'input' elements should be filled with the appropriate value for the entity that is updated.
     * The method should be mapped on path '/VirtualServers/[id]/edit'.
     *
     * @return The view "form.html".
     */
    @GetMapping("/VirtualServers/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        model.addAttribute("server", service.findById(id));
        model.addAttribute("users", userService.listAll());
        model.addAttribute("types", OSType.values());

        return "form";
    }

    /**
     * This method should create an entity given the arguments it takes.
     * The method should be mapped on path '/VirtualServers'.
     * After the entity is created, the list of entities should be displayed.
     *
     * @return The view "list.html".
     */
    @PostMapping("/VirtualServers")
    public String create(@RequestParam String instanceName,
                         @RequestParam String ipAddress,
                         @RequestParam OSType osType,
                         @RequestParam List<Long> owners,
                         @RequestParam String launchDate) {
        service.create(instanceName, ipAddress, osType, owners, LocalDate.parse(launchDate));
        return "redirect:/VirtualServers";
    }

    /**
     * This method should update an entity given the arguments it takes.
     * The method should be mapped on path '/VirtualServers/[id]'.
     * After the entity is updated, the list of entities should be displayed.
     *
     * @return The view "list.html".
     */
    @PostMapping("/VirtualServers/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String instanceName,
                         @RequestParam String ipAddress,
                         @RequestParam OSType osType,
                         @RequestParam List<Long> owners) {
        service.update(id, instanceName, ipAddress, osType, owners);
        return "redirect:/VirtualServers";
    }

    /**
     * This method should delete the entities that have the appropriate identifier.
     * The method should be mapped on path '/VirtualServers/[id]/delete'.
     * After the entity is deleted, the list of entities should be displayed.
     *
     * @return The view "list.html".
     */
    @PostMapping("/VirtualServers/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/VirtualServers";
    }

    /**
     * This method should mark the virtual server that has the appropriate identifier as terminated.
     * The method should be mapped on path '/VirtualServers/[id]/terminate'.
     * After its execution, the list of entities should be displayed.
     *
     * @return The view "list.html".
     */
    @PostMapping("/VirtualServers/{id}/terminate")
    public String terminate(@PathVariable Long id) {
        service.markTerminated(id);
        return "redirect:/VirtualServers";
    }
}
