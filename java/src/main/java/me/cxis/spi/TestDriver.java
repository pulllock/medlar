package me.cxis.spi;

import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ServiceLoader;

public class TestDriver {

    public static void main(String[] args) {
        ServiceLoader<Driver> drivers = ServiceLoader.load(Driver.class);

        for (Driver driver : drivers) {
            try {
                driver.connect("jdbc://mysql/xxxxx", new Properties());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
