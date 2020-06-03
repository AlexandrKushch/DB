package com.db.lab3;

import java.sql.*;

public class TableSelection {
    public static void several(Connection connection, String tables, int limit) throws SQLException {
        Statement statement = connection.createStatement();
        String[] spl = tables.split(",");

        for (int i = 0; i < spl.length; i++) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + spl[i] + " LIMIT " + limit);

            int column = resultSet.getMetaData().getColumnCount();
            for (int j = 1; j <= column; j++) {
                System.out.print(resultSet.getMetaData().getColumnName(j) + "\t");
            }
            System.out.println();
            while (resultSet.next()) {
                for (int j = 1; j <= column; j++) {
                    System.out.print(resultSet.getString(j) + "\t");
                }
                System.out.println();
            }
        }
    }

    public static void description(Connection connection, String tables) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tables);
        int column = resultSet.getMetaData().getColumnCount();
        for (int i = 1; i <= column; i++) {
            System.out.println(i + ". " + resultSet.getMetaData().getColumnName(i));
        }
        System.out.println();
    }

    public static void byCriterion(Connection connection, String tables, String criteria, int limit) throws SQLException {
        String [] spl = criteria.split("=");
        int columnID = Integer.parseInt(spl[0].trim());
        String value = spl[1].trim();

        Statement statement = connection.createStatement();
        ResultSet description = statement.executeQuery("SELECT * FROM " + tables);
        String columnName = description.getMetaData().getColumnName(columnID);

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + tables +" WHERE "
                + columnName +" = ? LIMIT " + limit);
        preparedStatement.setString(1, value);
        ResultSet resultSet = preparedStatement.executeQuery();

        int column = resultSet.getMetaData().getColumnCount();
        for (int j = 1; j <= column; j++) {
            System.out.print(resultSet.getMetaData().getColumnName(j) + "\t");
        }
        System.out.println();
        while (resultSet.next()) {
            for (int j = 1; j <= column; j++) {
                System.out.print(resultSet.getString(j) + "\t");
            }
            System.out.println();
        }
    }
}
