package ERP.system;

import ERP.authentication.Authenticator;
import ERP.authentication.LoginStatus;
import ERP.authentication.PublicAccount;
import ERP.client.Client;
import ERP.client.ClientManager;
import ERP.console.ConsoleManager;
import ERP.parse.DateParser;
import ERP.protocol.Pair;
import ERP.protocol.ProtocolManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ProgramRunner {

    private final Authenticator authenticator;
    private final ClientManager clientManager;
    private final ConsoleManager consoleManager;
    private final ProtocolManager protocolManager;

    private final DateParser dateParser;

    public ProgramRunner(Authenticator authenticator,
                         ClientManager clientManager,
                         ConsoleManager consoleManager,
                         ProtocolManager protocolManager,
                         DateParser dateParser) {
        this.authenticator = authenticator;
        this.clientManager = clientManager;
        this.consoleManager = consoleManager;
        this.protocolManager = protocolManager;
        this.dateParser = dateParser;
    }

    public void start() {
        while (true) {
            consoleManager.show("\tEmployee Tracking System");
            if (authenticator.hasLoggedAccount()) {
                processAccountOptions();
            } else {
                runLoginProcess();
            }
        }
    }

    private void runLoginProcess() {
        consoleManager.show("Please login to continue!");
        String userName = getCredentials("Username: ");
        String password = getCredentials("Password: ");

        LoginStatus loginStatus = authenticator.login(userName, password);
        if (loginStatus == LoginStatus.LOGIN_FAILED) {
            consoleManager.show("Login failed!");
        } else {
            consoleManager.show("Login successful!");
        }
    }

    private void processAccountOptions() {
        if (authenticator.hasLoggedAdmin()) {
            runAdminOptions();
        } else {
            runEmployeeOptions();
        }
    }

    private void runAdminOptions() {
        consoleManager.showAdminOptions();
        int accountChoice = consoleManager.getDecimalInput();
        switch (accountChoice) {
            case 1 -> authenticator.logout();
            case 2 -> runNewClientProcess();
            case 3 -> runNewEmployeeProcess();
            case 4 -> runStatisticsForEmployees();
            default -> consoleManager.show("No such option!");
        }
    }

    private void runStatisticsForEmployees() {
        consoleManager.showStatisticsOptions();
        int accountChoice = consoleManager.getDecimalInput();
        switch (accountChoice) {
            case 1 -> runStatisticsByEmployeeName();
            case 2 -> runStatisticsByWeekNumber();
            default -> consoleManager.show("No such option!");
        }
    }

    private void runStatisticsByWeekNumber() {
        consoleManager.show("Enter week number: ");
        int weekNumber = consoleManager.getDecimalInput();

        Map<PublicAccount, Integer> workingTimesOfEmployees = protocolManager.getEmployeesWorkingTimesByWeekInput(weekNumber);
        if (workingTimesOfEmployees.isEmpty()) consoleManager.show("No data for week " + weekNumber);
        else consoleManager.printMap(workingTimesOfEmployees);
    }

    private void runStatisticsByEmployeeName() {
        List<PublicAccount> employees = authenticator.getEmployees();
        consoleManager.printList(employees);
        consoleManager.show("Choose employee number:");
        int accountChoice = consoleManager.getListIndexInput(employees);
        PublicAccount selectedEmployee = employees.get(accountChoice);
        String employeeName = selectedEmployee.userName;

        Map<Client, Integer> workingTimesForClients = protocolManager.getClientsWorkingTimesByEmployeeNameInput(employeeName);
        if (workingTimesForClients.isEmpty()) consoleManager.show("No data for employee " + employeeName);
        else consoleManager.printMap(workingTimesForClients);
    }

    private void runNewEmployeeProcess() {
        consoleManager.show("Enter employee name: ");
        String employeeUserName = consoleManager.getTextInput();
        consoleManager.show("Enter employee password: ");
        String employeePassword = consoleManager.getTextInput();
        boolean isRegistered = authenticator.registerEmployee(employeeUserName, employeePassword);
        if (isRegistered) {
            consoleManager.show("New employee was successfully registered!");
        } else {
            consoleManager.show("This employee already exists!");
        }
    }

    private void runNewClientProcess() {
        consoleManager.show("Enter client name: ");
        String clientName = consoleManager.getTextInput();
        consoleManager.show("Enter client project name: ");
        String clientProject = consoleManager.getTextInput();
        Date contractEndDate = getInputDate("Enter contract end date: ");

        boolean isRegistered = clientManager.registerClient(clientName, clientProject, contractEndDate);
        if (isRegistered) {
            consoleManager.show("New client was successfully registered!");
        } else {
            consoleManager.show("This client already exists! ");
        }
    }

    private void runEmployeeOptions() {
        consoleManager.showEmployeeOptions();
        int accountChoice = consoleManager.getDecimalInput();
        switch (accountChoice) {
            case 1 -> authenticator.logout();
            case 2 -> runNewProtocolProcess();
            default -> consoleManager.show("No such option!");
        }
    }

    private void runNewProtocolProcess() {
        Date selectedDate = getInputDate("Enter protocol date: ");
        int weekNum = dateParser.parseWeekNumber(selectedDate);
        PublicAccount employee = authenticator.getLoggedAccount();
        List<Pair> workingTimesForClients = getWorkingTimesForClientsInput();

        protocolManager.createProtocol(selectedDate, weekNum, employee, workingTimesForClients);
    }

    private List<Pair> getWorkingTimesForClientsInput() {
        List<Pair> workingTimesForClients = new ArrayList<>();
        List<Client> clients = clientManager.getClients();
        boolean repeat = true;

        while (repeat) {
            consoleManager.printList(clients);
            consoleManager.show("Select client number: ");
            int selectedClientIndex = consoleManager.getListIndexInput(clients);
            Client selectedClient = clients.get(selectedClientIndex);
            consoleManager.show("Insert working time in minutes: ");
            int selectedWorkingTime = consoleManager.getDecimalInput();
            workingTimesForClients.add(new Pair(selectedClient, selectedWorkingTime));

            consoleManager.show("If you want to continue - type \"Yes\"! For exit - press Enter!");
            String userChoice = consoleManager.getTextInput();
            repeat = userChoice.equalsIgnoreCase("yes");
        }
        return workingTimesForClients;
    }

    private Date getInputDate(String text) {
        consoleManager.show(text + dateParser.getDateFormat());
        String dateInput = consoleManager.getTextInput();
        while (!dateParser.isCorrectDate(dateInput)) {
            dateInput = consoleManager.getTextInput();
        }
        return dateParser.parse(dateInput);
    }

    private String getCredentials(String text) {
        consoleManager.show(text);
        return consoleManager.getTextInput();
    }
}
