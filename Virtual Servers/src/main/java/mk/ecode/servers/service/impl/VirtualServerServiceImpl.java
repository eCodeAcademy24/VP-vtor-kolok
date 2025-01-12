package mk.ecode.servers.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ecode.servers.model.OSType;
import mk.ecode.servers.model.User;
import mk.ecode.servers.model.VirtualServer;
import mk.ecode.servers.model.exceptions.InvalidVirtualMachineIdException;
import mk.ecode.servers.repository.VirtualServerRepository;
import mk.ecode.servers.service.UserService;
import mk.ecode.servers.service.VirtualServerService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VirtualServerServiceImpl implements VirtualServerService {

    private final VirtualServerRepository serverRepository;
    private final UserService userService;

    @Override
    public List<VirtualServer> listAll() {
        return serverRepository.findAll();
    }

    @Override
    public VirtualServer findById(Long id) {
        return serverRepository.findById(id)
                .orElseThrow(InvalidVirtualMachineIdException::new);
    }

    @Override
    public VirtualServer create(String name, String ipAddress, OSType osType, List<Long> owners, LocalDate launchDate) {
        VirtualServer virtualServer = new VirtualServer();
        List<User> users = owners.stream()
                .map(userService::findById)
                .collect(Collectors.toList());

        virtualServer.setInstanceName(name);
        virtualServer.setIpAddress(ipAddress);
        virtualServer.setOSType(osType);
        virtualServer.setLaunchDate(launchDate);
        virtualServer.setOwners(users);

        return serverRepository.save(virtualServer);
    }

    @Override
    public VirtualServer update(Long id, String name, String ipAddress, OSType osType, List<Long> owners) {
        VirtualServer virtualServer = findById(id);
        List<User> users = owners.stream()
                .map(userService::findById)
                .collect(Collectors.toList());

        virtualServer.setInstanceName(name);
        virtualServer.setIpAddress(ipAddress);
        virtualServer.setOSType(osType);
        virtualServer.setOwners(users);

        return serverRepository.save(virtualServer);
    }

    @Override
    public VirtualServer delete(Long id) {
        VirtualServer virtualServer = findById(id);
        serverRepository.delete(virtualServer);

        return virtualServer;
    }

    @Override
    public VirtualServer markTerminated(Long id) {
        VirtualServer virtualServer = findById(id);
        virtualServer.setTerminated(true);

        return serverRepository.save(virtualServer);

    }

    @Override
    public List<VirtualServer> filter(Long ownerId, Integer activeMoreThanDays) {
        if (ownerId != null && activeMoreThanDays != null) {
            return serverRepository.findAllByOwnersContainingAndLaunchDateBefore(
                    userService.findById(ownerId),
                    LocalDate.now().minusDays(activeMoreThanDays));
        } else if (activeMoreThanDays != null) {
            return serverRepository.findAllByLaunchDateBefore(
                    LocalDate.now().minusDays(activeMoreThanDays));
        } else if (ownerId != null) {
            return serverRepository.findAllByOwnersContaining(userService.findById(ownerId));
        }

        return this.serverRepository.findAll();

    }
}
