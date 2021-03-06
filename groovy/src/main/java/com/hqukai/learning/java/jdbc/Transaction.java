package com.hqukai.learning.java.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Administrator on 2016/1/11.
 */
public class Transaction {

    public static void main(String[] args) throws SQLException {
        JdbcMain jdbcMain = new JdbcMain();

        Connection connection = null;
        try {
            connection = jdbcMain.getConnection();
            connection.setAutoCommit(false);
//            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            System.out.println(connection.getTransactionIsolation());
            Statement s = connection.createStatement();



//            int x = s.executeUpdate("delete from student");
//            System.out.println(x);
            s.executeUpdate("insert into student values(1,'hankai',12)");
            s.executeUpdate("insert into student values(12,'hankai',12)");

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
//            connection.commit();
        }
    }

}
