package ERP.di;

import ERP.authentication.AccountDatabase;
import ERP.client.ClientDatabase;
import ERP.console.ConsoleManager;
import ERP.console.ConsoleManagerImpl;
import ERP.parse.ControllerIO;
import ERP.parse.DateParser;
import ERP.parse.DateParserImpl;
import ERP.protocol.ProtocolDatabase;
import ERP.protocol.ProtocolManager;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import ERP.authentication.AccountDatabaseImpl;
import ERP.authentication.Authenticator;
import ERP.authentication.AuthenticatorImpl;
import ERP.client.ClientDatabaseImpl;
import ERP.client.ClientManager;
import ERP.client.ClientManagerImpl;
import ERP.parse.ControllerIOImpl;
import ERP.protocol.ProtocolDatabaseImpl;
import ERP.protocol.ProtocolManagerImpl;
import ERP.system.ProgramRunner;

import java.util.Scanner;

public class DataModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Provides
    @Singleton
    ProgramRunner providesProgramRunner(Authenticator authenticator,
                                        ClientManager clientManager,
                                        ConsoleManager consoleManager,
                                        ProtocolManager protocolManager,
                                        DateParser dateParser) {
        return new ProgramRunner(authenticator, clientManager, consoleManager, protocolManager, dateParser);
    }

    @Provides
    @Singleton
    Authenticator providesAuthenticator(AccountDatabase accountDatabase) {
        return new AuthenticatorImpl(accountDatabase);
    }

    @Provides
    @Singleton
    AccountDatabase providesAccountDatabase(ControllerIO controllerIO) {
        return new AccountDatabaseImpl(controllerIO);
    }

    @Provides
    @Singleton
    ClientManager providesClientManager(ClientDatabase clientDatabase) {
        return new ClientManagerImpl(clientDatabase);
    }

    @Provides
    @Singleton
    ClientDatabase providesClientDatabase(ControllerIO controllerIO) {
        return new ClientDatabaseImpl(controllerIO);
    }

    @Provides
    @Singleton
    ConsoleManager providesConsoleManager(Scanner scanner) {
        return new ConsoleManagerImpl(scanner);
    }

    @Provides
    @Singleton
    Scanner providesScanner() {
        return new Scanner(System.in);
    }

    @Provides
    @Singleton
    ProtocolManager providesProtocolManager(ProtocolDatabase protocolDatabase) {
        return new ProtocolManagerImpl(protocolDatabase);
    }

    @Provides
    @Singleton
    ProtocolDatabase providesProtocolDatabase(ControllerIO controllerIO) {
        return new ProtocolDatabaseImpl(controllerIO);
    }

    @Provides
    @Singleton
    ControllerIO providesControllerIO() {
        return new ControllerIOImpl();
    }

    @Provides
    @Singleton
    DateParser providesDateParser() {
        return new DateParserImpl();
    }
}
