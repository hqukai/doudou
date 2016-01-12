package com.hqukai.learning.java.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/11.
 */
public class TransactionRead {

    public static void main(String[] args) throws SQLException {
        JdbcMain jdbcMain = new JdbcMain();

        Connection connection = null;
        try {
            connection = jdbcMain.getConnection();
//            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            Statement s = connection.createStatement();
            ResultSet r = s.executeQuery("SELECT * from student");
//            List<Student> l = new ArrayList<>();
            Student student = null;
            while (r.next()) {
                student = new Student(r.getLong("id"), r.getString("name"), r.getInt("age"));
//                l.add(student);
                System.out.println(student);
            }
//            System.out.println(l.toString());
        } catch (SQLException e) {
            e.printStackTrace();
//            connection.rollback();
//            connection.commit();
        } finally {
            connection.close();
        }
    }

}
