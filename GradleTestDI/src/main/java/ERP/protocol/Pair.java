package ERP.protocol;

import ERP.client.Client;

import java.io.Serializable;

public class Pair implements Serializable {

    private final Client client;
    private final int workingTime;

    public Pair(Client client, int workingTime) {
        this.client = client;
        this.workingTime = workingTime;
    }

    public Client getClient() {
        return client;
    }

    public int getWorkingTime() {
        return workingTime;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "com.vso.client=" + client +
                ", workingTime=" + workingTime +
                '}';
    }
}
