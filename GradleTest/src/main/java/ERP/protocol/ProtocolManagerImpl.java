package ERP.protocol;

import ERP.authentication.PublicAccount;
import ERP.client.Client;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProtocolManagerImpl implements ProtocolManager {

    private final ProtocolDatabase protocolDatabase;

    public ProtocolManagerImpl(ProtocolDatabase protocolDatabase) {
        this.protocolDatabase = protocolDatabase;
    }

    @Override
    public Map<Client, Integer> getClientsWorkingTimesByEmployeeNameInput(String name) {
        return protocolDatabase.getProtocols().stream()
                .filter(protocol -> protocol.getEmployee().userName.equalsIgnoreCase(name))
                .map(Protocol::getWorkingTimesPerClient)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Pair::getClient))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .mapToInt(Pair::getWorkingTime)
                                .sum()));
    }

    @Override
    public void createProtocol(Date date, int weekNum, PublicAccount account, List<Pair> workingTimesPerClient) {
        Protocol currentProtocol = new Protocol(date, weekNum, account, workingTimesPerClient);
        protocolDatabase.addProtocol(currentProtocol);
    }

    @Override
    public Map<PublicAccount, Integer> getEmployeesWorkingTimesByWeekInput(int weekNumber) {
        return protocolDatabase.getProtocols().stream()
                .filter(protocol -> protocol.getWeekNum() == weekNumber)
                .collect(Collectors.groupingBy(Protocol::getEmployee))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .map(Protocol::getWorkingTimesPerClient)
                                .flatMap(Collection::stream)
                                .mapToInt(Pair::getWorkingTime)
                                .sum()));
    }
}
