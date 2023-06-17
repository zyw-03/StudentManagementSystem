package utils;

import entity.User;
import patterns.observer.Subject;

import java.sql.*;

public class UserDBUtil {

    private static User user;

    private static Connection getConnection() {
        ConnectionUtil.connect();

        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ManagementSystem", "root", "zyw030303");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    public static User getNowUser(){
        return UserDBUtil.user;
    }


    public static void addUser(User user){
        Connection connection = getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement("insert into users values (?, ?, ?, ?)");
            preparedStatement.setString(1, user.getAccount());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getPostbox());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void removeUser(String account){
        Connection connection = getConnection();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("delete from users where account=?");
            preparedStatement.setString(1, account);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void updateUser(){
        Connection connection = getConnection();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("update users set password = ?, phone = ?, postbox = ? where account = ?");
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getPhone());
            preparedStatement.setString(3, user.getPostbox());
            preparedStatement.setString(4, user.getAccount());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static Boolean judgeRight(String account, String password){
        Connection connection = getConnection();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String ac = resultSet.getString("account");
                String pw = resultSet.getString("password");

                if (ac.equals(account) && pw.equals(password)){
                    String phone = resultSet.getString("phone");
                    String postbox = resultSet.getString("postbox");
                    user = new User(ac, pw, phone, postbox);
                    return true;
                }

                if (ac.equals(account) && !pw.equals(password)) return false;
            }


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;

    }

    public static Boolean judgeIfExits(String account){
        Connection connection = getConnection();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where account = ?");
            preparedStatement.setString(1, account);

            ResultSet resultSet = preparedStatement.executeQuery();



            return resultSet.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }




}
