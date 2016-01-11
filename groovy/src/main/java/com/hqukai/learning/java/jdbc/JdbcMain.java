package com.hqukai.learning.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Administrator on 2016/1/11.
 */
public class JdbcMain {

    private final static String url = "";

    private Connection connection = null;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("mysql驱动加载成功");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Connection getConnection() throws SQLException {
        if (null == connection) {
            connection = DriverManager.getConnection(url);
        }
        return connection;
    }


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("mysql驱动加载成功");
        Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();
        String droptable = "drop table status;";
        String sql = "create table student (id varchar(20),name varchar(30),age int,primary key(id))";
//        boolean isDelete= statement.execute(droptable);
//        System.out.println(isDelete);
        boolean b = statement.execute(sql);

        System.out.println(b);
        if (b) {
            System.out.println("创建表成功");
        }
    }

}
