package ERP.client;

import java.util.Date;
import java.util.List;

public class ClientManagerImpl implements ClientManager {

    private final ClientDatabase clientDatabase;

    public ClientManagerImpl(ClientDatabase clientDatabase) {
        this.clientDatabase = clientDatabase;
    }

    @Override
    public List<Client> getClients() {
        return clientDatabase.getClients();
    }

    @Override
    public boolean registerClient(String clientName, String clientProject, Date contractEndDate) {
        if (clientDatabase.clientExist(clientName)) {
            return false;
        }

        Client client = new Client(clientName, clientProject, contractEndDate);
        clientDatabase.addClient(client);
        return true;
    }
}
