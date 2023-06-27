package utils;

import entity.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class StudentDBUtil {     //用于暂存的学生成绩和数据库中的学生表的交互的工具

    private static Connection getConnection() {   //跟数据库中的学生表连接
        ConnectionUtil.connect();
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ManagementSystem", "root", "zyw030303");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    public static void addStudents(List<Student> students){     //将暂存的学生插入学生表中
        Connection connection = getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement("insert into students values (?, ?, ?, ?)");

            for (Student student : students) {
                preparedStatement.setString(1, student.getId());
                preparedStatement.setString(2, student.getName());
                preparedStatement.setString(3, student.getCourse());
                preparedStatement.setString(4, student.getScore());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
