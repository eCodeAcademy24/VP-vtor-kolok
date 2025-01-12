package mk.ecode.servers.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class VirtualServer {

    public VirtualServer() {
    }

    public VirtualServer(String instanceName, String ipAddress, OSType osType, List<User> owners, LocalDate launchDate) {
        this.instanceName = instanceName;
        this.ipAddress = ipAddress;
        this.OSType = osType;
        this.owners = owners;
        this.launchDate = launchDate;
    }

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate launchDate;

    private String instanceName;

    private String ipAddress;

    @Enumerated(EnumType.STRING)
    private OSType OSType;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> owners;

    private Boolean terminated = false;
}
