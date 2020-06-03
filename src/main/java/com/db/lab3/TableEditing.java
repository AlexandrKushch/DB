package com.db.lab3;

import java.sql.*;

public class TableEditing {
    public static void delete(Connection connection, String tables, int id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet description = statement.executeQuery("SELECT * FROM " + tables);
        String columnName = description.getMetaData().getColumnName(1);

        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + tables
                + " WHERE " + columnName + " = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    public static void update(Connection connection, String table, int id, String criteria) throws SQLException {
        String[] spl = criteria.split("=");
        int columnID = Integer.parseInt(spl[0].trim());
        String value = spl[1].trim();

        Statement statement = connection.createStatement();
        ResultSet description = statement.executeQuery("SELECT * FROM " + table);
        String columnName = description.getMetaData().getColumnName(columnID);
        String tableID = description.getMetaData().getColumnName(1);

        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + table + " SET " + columnName
                + " = ? WHERE " + tableID + " = ?");
        preparedStatement.setString(1, value);
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();
    }
}
