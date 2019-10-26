package com.naridsara.halaldelivery.utility;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnect {

    private static final String TAG = "MyConnect";
    private static final String LOCALHOST = "192.168.1.23";
    private static final String DB_NAME = "halaldelivery";
    private static final String USERNAME = "naridsara";
    private static final String PASSWORD = "242541";

    public static Connection conn() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + LOCALHOST + "/" + DB_NAME + "?useUnicode=true&characterEncoding=utf-8";
            return DriverManager.getConnection(url, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void execute(String sql, MyExecuteUpdate update) {
        Connection conn = null;
        Statement statement = null;

        try {
            conn = conn();
            statement = conn.createStatement();
            int result = statement.executeUpdate(sql);
            if (result == 1) {
                update.onComplete();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
    }

    public static void execute(String sql, MyExecuteQuery query) {
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            conn = conn();
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            query.onComplete(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
    }

    // TODO: 26/09/2562 SELECT
//    String sql = "";
//        MyConnect.execute(sql, new MyExecuteQuery() {
//        @Override
//        public void onComplete(ResultSet rs) {
//            try {
//                while (rs.next()) {
//                    // TODO: 26/09/2562
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    });

    // TODO: 26/09/2562 INSERT,UPDATE,DELETE
//    String sql = "";
//        MyConnect.execute(sql, new MyExecuteUpdate() {
//        @Override
//        public void onComplete() {
//            // TODO: 27/09/2562
//        }
//    });
}