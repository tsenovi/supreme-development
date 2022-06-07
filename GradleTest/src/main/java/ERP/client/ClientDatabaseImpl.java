package ERP.client;

import ERP.parse.ControllerIO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDatabaseImpl implements ClientDatabase {

    private final String CLIENTS_FILE = "src\\main\\resources\\clients.db";
    private final ControllerIO controllerIO;

    public ClientDatabaseImpl(ControllerIO controllerIO) {
        this.controllerIO = controllerIO;
    }

    @Override
    public List<Client> getClients() {
        return List.copyOf(controllerIO.loadFile(CLIENTS_FILE));
    }

    @Override
    public void addClient(Client client) {
        List<Client> clients = controllerIO.loadFile(CLIENTS_FILE);
        if (clients == null) clients = new ArrayList<>();
        clients.add(client);
        controllerIO.overwriteFile(CLIENTS_FILE, clients);
    }

    @Override
    public boolean clientExist(String clientName) {
        return getClient(clientName) != null;
    }

    private Client getClient(String name) {
        List<Client> clients = controllerIO.loadFile(CLIENTS_FILE);
        if (clients == null) clients = new ArrayList<>();
        Optional<Client> first = clients.stream()
                .filter(user -> user.getName().equals(name))
                .findFirst();

        return first.orElse(null);
    }
}
