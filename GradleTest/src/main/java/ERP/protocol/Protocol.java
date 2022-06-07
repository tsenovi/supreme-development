package ERP.protocol;

import ERP.authentication.PublicAccount;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Protocol implements Serializable {

    private final Date protocolDate;
    private final int weekNum;
    private final PublicAccount employee;
    private final List<Pair> workingTimesPerClient;

    public Protocol(Date protocolDate, int weekNum, PublicAccount employee, List<Pair> workingTimesPerClient) {
        this.protocolDate = protocolDate;
        this.weekNum = weekNum;
        this.employee = employee;
        this.workingTimesPerClient = workingTimesPerClient;

    }

    public PublicAccount getEmployee() {
        return employee;
    }

    public List<Pair> getWorkingTimesPerClient() {
        return workingTimesPerClient;
    }

    @Override
    public String toString() {
        return "Protocol{" + "protocolDate=" + protocolDate + ", employee=" + employee + ", workingTimesPerClient=" + workingTimesPerClient + '}';
    }

    public int getWeekNum() {
        return weekNum;
    }
}
