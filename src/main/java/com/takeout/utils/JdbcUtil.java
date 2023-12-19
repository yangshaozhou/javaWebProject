package com.takeout.utils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

public class JdbcUtil {
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://121.41.92.252:3306/java_web_project";
    private static String user = "root";
    private static String password = "Yangsz04150018";
    private static Properties pr = new Properties();

    static {
        try {
//            pr.load(JdbcUtil.class.getClassLoader().getResourceAsStream(
//                    "/db.properties"));
//            driver = pr.getProperty("com.mysql.cj.jdbc.Driver");
//            url = pr.getProperty("jdbc:mysql://121.41.92.252:3306/java_web_project");
//            user = pr.getProperty("username");
//            password = pr.getProperty("password");
//            System.out.println("启动"+driver + " " + url);
            Class.forName(driver);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private JdbcUtil() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void free(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null)
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
}
