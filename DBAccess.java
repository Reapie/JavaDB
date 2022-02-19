package db;

import java.io.IOException;
import java.sql.*;

public class DBAccess {
    private DataBase db;
    private Connection con;

    private static DBAccess INSTANCE;

    private DBAccess() throws ClassNotFoundException, IOException, SQLException {
        db = DataBase.getInstance();
        con = db.getConnection();
    }

    public static DBAccess getInstance() throws SQLException, IOException, ClassNotFoundException {
        if (INSTANCE == null) {
            INSTANCE = new DBAccess();
        }
        return INSTANCE;
    }

    public void disconnect() throws SQLException {
        db.disconnect();
    }

    // Database functions below this

    public String getVersionPreparedStatement() throws SQLException {
        String query = "SELECT VERSION()";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString(1);
    }

    public String getVersionRegularStatement() throws SQLException {
        String query = "SELECT VERSION()";
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        return resultSet.getString(1);
    }
}
