package ERP.authentication;

import ERP.parse.ControllerIO;

import java.util.ArrayList;
import java.util.List;

public class AccountDatabaseImpl implements AccountDatabase {

    private final String ADMIN_FILE = "src\\main\\resources\\admin.db";
    private final String EMPLOYEES_FILE = "src\\main\\resources\\employees.db";
    private final ControllerIO controllerIO;

    public AccountDatabaseImpl(ControllerIO controllerIO) {
        this.controllerIO = controllerIO;
    }

    @Override
    public void addEmployee(Employee employee) {
        List<Employee> employees = controllerIO.loadFile(EMPLOYEES_FILE);
        if (employees == null) employees = new ArrayList<>();
        employees.add(employee);
        controllerIO.overwriteFile(EMPLOYEES_FILE, employees);
    }

    @Override
    public List<Employee> getEmployees() {
        return List.copyOf(controllerIO.loadFile(EMPLOYEES_FILE));
    }

    @Override
    public boolean accountExists(String userName) {
        return userExistsAsAdmin(userName) || userExistsAsEmployee(userName);
    }

    @Override
    public Account getAccount(String userName) {
        Account account = getAdmin(userName);
        if (account == null) {
            account = getEmployee(userName);
        }
        return account;
    }

    private Account getEmployee(String userName) {
        List<Employee> employees = controllerIO.loadFile(EMPLOYEES_FILE);
        if (employees == null) employees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getAccountName().equalsIgnoreCase(userName)) {
                return employee;
            }
        }
        return null;
    }

    private Account getAdmin(String userName) {
        Account admin = controllerIO.loadFile(ADMIN_FILE);
        if (admin == null) {
            admin = new Admin("admin", "admin");
            controllerIO.overwriteFile(ADMIN_FILE, admin);
        }
        if (admin.getAccountName().equalsIgnoreCase(userName)) return admin;
        return null;
    }

    private boolean userExistsAsEmployee(String userName) {
        return getEmployee(userName) != null;
    }

    private boolean userExistsAsAdmin(String userName) {
        return getAdmin(userName) != null;
    }
}
