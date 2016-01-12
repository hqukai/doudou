package com.hqukai.learning.java.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Administrator on 2016/1/11.
 */
public class TransactionThread {

    public static void main(String[] args) throws SQLException {
//        final JdbcMain jdbcMain = new JdbcMain();

//        Connection connection = null;

        new Thread(() -> {
            System.out.println("线程2开始执行");
            Connection c = null;
            try {
                JdbcMain jdbcMain = new JdbcMain();
                c = jdbcMain.getConnection();
                c.setAutoCommit(false);
//                c.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
//                c.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
//                c.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
                c.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                Statement s = c.createStatement();


                for (int i = 0; i < 10; i++) {
                    System.out.println("第" + i + "次查询");
                    select(s);
                    Thread.sleep(1000);
                }
                c.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程2结束");

        }).start();
        System.out.println("中间");

        new Thread(() -> {
            System.out.println("线程1开始执行");
            Connection c = null;
            try {
                JdbcMain jdbcMain = new JdbcMain();
                c = jdbcMain.getConnection();
                c.setAutoCommit(false);
                c.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
//                c.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
//                c.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
                Statement s = c.createStatement();
                for (int i = 0; i < 2; i++) {
                    Thread.sleep(1 * 1000);
                    System.out.println("线程1  sleep time:" + i);
                }
                System.out.println("执行 insert into student values(23,'test',1)");
                s.executeUpdate("insert into student values(23,'test',1)");
                System.out.println("insert结束");
                for (int i = 0; i < 2; i++) {
                    Thread.sleep(1 * 1000);
                    System.out.println("线程1  sleep time:" + i);
                }

                System.out.println("执行更新");
                int x = s.executeUpdate("update student set name ='zzzzzzz'where id ='18'");
                System.out.println("更新：" + x);

                for (int i = 0; i < 3; i++) {
                    Thread.sleep(1 * 1000);
                    System.out.println("更新以后线程1  sleep time:" + i);
                }
//                System.out.println("执行 insert into student values(12,'test',1)");
//                s.executeUpdate("insert into student values(12,'sdfad',1)");
                System.out.println("结束");


                c.commit();
                c.close();
            } catch (SQLException e) {
//                e.printStackTrace();
                System.err.println(e.getMessage());
                try {
                    System.out.println("线程1回滚");
                    c.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程1结束");
        }).start();

        System.out.println("主进程中间");

        new Thread(() -> {
            System.out.println("线程3开始执行");
            Connection c = null;

            try {
                Thread.sleep(15 * 1000);
                System.out.println("线程3开始读啦");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                JdbcMain jdbcMain = new JdbcMain();
                c = jdbcMain.getConnection();
                c.setAutoCommit(false);
//                c.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
//                c.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                c.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
                Statement s = c.createStatement();


                for (int i = 0; i < 10; i++) {
                    System.out.println("第" + i + "次查询");
                    select(s);
                    Thread.sleep(1000);
                }
                c.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程3结束");

        }).start();


        System.out.println("主进程结束");
    }

    private static void select(Statement s) throws SQLException {
        ResultSet rs = s.executeQuery("select * from student");
        Student student = null;
        while (rs.next()) {
            student = new Student(rs.getLong("id"), rs.getString("name"), rs.getInt("age"));
            System.out.println(student);
        }
    }

}
