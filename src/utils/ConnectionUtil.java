package utils;

public class ConnectionUtil {     //连接mysql数据库的工具
    public static void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
