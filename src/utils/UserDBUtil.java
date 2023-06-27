package utils;

import entity.User;
import patterns.observer.Subject;

import java.sql.*;

public class UserDBUtil {     //用于与数据库用户表交互的工具

    private static User user;    //用于记录登录的用户

    private static Connection getConnection() {   //联检数据库中用户表
        ConnectionUtil.connect();

        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ManagementSystem", "root", "zyw030303");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    public static User getNowUser(){     //返回当前登录的用户
        return UserDBUtil.user;
    }


    public static void addUser(User user){   //往用户表中插入一个新用户
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

    public static void removeUser(String account){   //从数据表中删除账号为account的用户
        Connection connection = getConnection();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("delete from users where account=?");
            preparedStatement.setString(1, account);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void updateUser(){      //更新用户表中某用户的数据
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

    public static Boolean judgeRight(String account, String password){    //判断用户表中是否存在账号为account，密码为password的用户
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

    public static Boolean judgeIfExits(String account){     //判断数据表中是否存在账号为aacount的用户
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
