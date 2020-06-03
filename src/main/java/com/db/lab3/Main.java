package com.db.lab3;

import java.util.ResourceBundle;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("connection");
        Scanner scan = new Scanner(System.in);
        int command = 0;

        try(Connection connection = DriverManager.getConnection(bundle.getString("url"),
                bundle.getString("username"), bundle.getString("password"))){

            while(command != 9) {
                System.out.println("Commands:\n1. Show Tables\n2. Several Selection\n" +
                        "3. By criteria Selection\n4. Delete\n5. Update\n9. Exit");
                System.out.print("Enter the number to execute the command: ");
                command = scan.nextInt();

                switch (command) {
                    case 1:
                        showTables(connection);
                        break;
                    case 2:
                        System.out.println("Enter which tables you want to select(separated by comas): ");
                        String tables = scan.nextLine();
                        tables = scan.nextLine();
                        System.out.println("Enter limit of rows: ");
                        int limit = scan.nextInt();
                        TableSelection.several(connection, tables, limit);
                        break;
                    case 3:
                        System.out.println("Enter which tables you want to select(separated by comas): ");
                        String tables1 = scan.nextLine();
                        tables1 = scan.nextLine();
                        System.out.println("Description of tables: " + tables1);
                        TableSelection.description(connection, tables1);
                        System.out.println("Enter criteria\nFor example: 1 = 5 (1 - column, 5 - value)");
                        String criteria1 = scan.nextLine();
                        System.out.println("Enter limit of rows: ");
                        int limit1 = scan.nextInt();
                        TableSelection.byCriterion(connection, tables1, criteria1, limit1);
                        break;
                    case 4:
                        System.out.println("Enter from which table you want to delete row: ");
                        String tables2 = scan.nextLine();
                        tables2 = scan.nextLine();
                        System.out.println("Enter which row to delete: ");
                        int id2 = scan.nextInt();
                        TableEditing.delete(connection, tables2, id2);
                        break;
                    case 5:
                        System.out.println("Enter table name which you want to update: ");
                        String tables3 = scan.nextLine();
                        tables3 = scan.nextLine();
                        System.out.println("Enter field ID:");
                        int id3 = scan.nextInt();
                        System.out.println("Description of tables: " + tables3);
                        TableSelection.description(connection, tables3);
                        System.out.println("Enter what to update\nFor example: 3 = MASHA (3 - column, MASHA - value)");
                        String criteria3 = scan.nextLine();
                        criteria3 = scan.nextLine();
                        TableEditing.update(connection, tables3, id3, criteria3);
                        break;
                    case 9:
                        break;
                    default:
                        System.out.println("Not found command. Try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void showTables(Connection connection) throws SQLException {
        System.out.println("Tables in sakila:");
        ResultSet showTables = connection.getMetaData().getTables(null, null, "%", null);
        int i = 1;
        while(showTables.next()){
            if(showTables.getString(4).equalsIgnoreCase("TABLE")){
                System.out.println(i + ". " + showTables.getString(3));
                i++;
            }
        }
        System.out.println();
    }
}
