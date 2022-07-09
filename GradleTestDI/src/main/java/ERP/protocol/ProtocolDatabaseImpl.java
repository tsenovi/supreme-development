package ERP.protocol;

import ERP.parse.ControllerIO;

import java.util.ArrayList;
import java.util.List;

public class ProtocolDatabaseImpl implements ProtocolDatabase {

    private final String PROTOCOLS_FILE = "src\\main\\resources\\protocols.db";
    private final ControllerIO controllerIO;

    public ProtocolDatabaseImpl(ControllerIO controllerIO) {
        this.controllerIO = controllerIO;
    }

    @Override
    public List<Protocol> getProtocols() {
        return List.copyOf(controllerIO.loadFile(PROTOCOLS_FILE));
    }

    @Override
    public void addProtocol(Protocol protocol) {
        List<Protocol> protocols = controllerIO.loadFile(PROTOCOLS_FILE);
        if (protocols == null) protocols = new ArrayList<>();
        protocols.add(protocol);
        controllerIO.overwriteFile(PROTOCOLS_FILE, protocols);
    }
}
