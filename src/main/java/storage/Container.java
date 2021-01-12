package storage;

import net.dv8tion.jda.api.entities.Guild;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Container {

    // Dateien
    public static final File SensitiveDataFile = new File("./Sensitive-data");

    // Guild
    private static Guild guild;

    public Container(String connection) {
        this.connection = connection;
    }

    public static Guild getGuild() {
        return guild;
    }

    public static void setGuild(Guild g) {
        guild = g;
    }

    //DB Connection
    private static String connection;

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
