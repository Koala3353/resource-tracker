package com.general_hello.commands.commands.Commands.RpgUser;

import com.general_hello.commands.Database.SQLiteDataSource;
import com.general_hello.commands.commands.Commands.Items.Initializer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResourcesUser {
    public static long getItemCount(long userId, String item) {
        checkUser(userId);
        try (Connection connection = SQLiteDataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("SELECT " + SQLiteDataSource.filter(Initializer.allItems.get(SQLiteDataSource.filter(item)).getName()) + " FROM Resources WHERE UserId = ?")) {

            preparedStatement.setLong(1, (userId));
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getLong(SQLiteDataSource.filter(Initializer.allItems.get(SQLiteDataSource.filter(item)).getName()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static long getMoneyNoCheck(long userId) {
        try (Connection connection = SQLiteDataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("SELECT UserId FROM Resources WHERE UserId = ?")) {

            preparedStatement.setLong(1, userId);

            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getLong("UserId");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void addItem(long userId, long amount, String item) {
        checkUser(userId);
        item = SQLiteDataSource.filter(Initializer.allItems.get(SQLiteDataSource.filter(item)).getName());
        long total = (amount) + getItemCount(userId, item);

        try (final PreparedStatement preparedStatement = SQLiteDataSource.getConnection()
                .prepareStatement("UPDATE Resources SET " + item + "=? WHERE UserId=?"
                )) {

            preparedStatement.setLong(2, userId);
            preparedStatement.setLong(1, total);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void newInfo(long userId) {
        try (final PreparedStatement preparedStatement = SQLiteDataSource.getConnection()
                .prepareStatement("INSERT INTO Resources" +
                        "(UserId)" +
                        "VALUES (?);")) {

            preparedStatement.setString(1, String.valueOf(userId));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void checkUser(long userId) {
        if (getMoneyNoCheck(userId) == -1) {
            newInfo(userId);
        }
    }
}
