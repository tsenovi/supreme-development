package ERP.protocol;

import ERP.authentication.PublicAccount;
import ERP.client.Client;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ProtocolManager {
    Map<Client, Integer> getClientsWorkingTimesByEmployeeNameInput(String empName);

    void createProtocol(Date date, int weekNum, PublicAccount account, List<Pair> workingTimesPerClient);

    Map<PublicAccount, Integer> getEmployeesWorkingTimesByWeekInput(int weekNum);

}
