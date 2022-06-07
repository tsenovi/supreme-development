package ERP;

import ERP.di.DataModule;
import ERP.system.ProgramRunner;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class App {

    public static void main(String[] args) {

        try {
            Injector injector = Guice.createInjector(new DataModule());
            injector.getInstance(ProgramRunner.class).start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
