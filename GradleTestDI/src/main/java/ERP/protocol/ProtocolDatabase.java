package ERP.protocol;

import java.util.List;

public interface ProtocolDatabase {
    List<Protocol> getProtocols();

    void addProtocol(Protocol protocol);
}
