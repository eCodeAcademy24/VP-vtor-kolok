package mk.ecode.servers.repository;

import mk.ecode.servers.model.User;
import mk.ecode.servers.model.VirtualServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VirtualServerRepository extends JpaRepository<VirtualServer, Long> {

    List<VirtualServer> findAllByLaunchDateBefore(LocalDate localDate);

    List<VirtualServer> findAllByOwnersContaining(User byId);

    List<VirtualServer> findAllByOwnersContainingAndLaunchDateBefore(User byId, LocalDate localDate);
}
