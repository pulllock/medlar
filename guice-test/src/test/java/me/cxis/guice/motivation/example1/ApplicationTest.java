package me.cxis.guice.motivation.example1;

import com.google.inject.Guice;
import com.google.inject.Injector;
import me.cxis.guice.example1.Application;
import me.cxis.guice.example1.MyAppModule;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ApplicationTest {

    private static Injector injector;

    @BeforeClass
    public void setUp() {
        injector = Guice.createInjector(new MyAppModule());
    }

    @Test
    public void testWork() {
        Application application = injector.getInstance(Application.class);
        application.work();
    }
}
