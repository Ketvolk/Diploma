package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static final String url = System.getProperty("db.url");
    private static final String user = System.getProperty("db.user");
    private static final String pass = System.getProperty("db.password");
    private static final QueryRunner queryRunner = new QueryRunner();

    private SQLHelper(){
    }

    private static Connection getConnect() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

//    @SneakyThrows
//    public static String getPaymentStatus(){
//        String status = "SELECT status FROM payment_entity ORDER BY created DESC Limit 1";
//        var connection = getConnect();
//        var paymentStatus = queryRunner.query(connection,status, new ScalarHandler<String>());
//        return  status;
//    }
//
//    @SneakyThrows
//    public static void clearDB(){
//        var connection = getConnect();
//        queryRunner.execute(connection, "DELETE FROM payment_entity");
//        queryRunner.execute(connection, "DELETE FROM credit_request_entity");
//        queryRunner.execute(connection, "DELETE FROM order_entity");
//    }

    @SneakyThrows
    public static String getPaymentStatus() {
        var connection = getConnect();
        var SQLQuery = "SELECT status FROM payment_entity ORDER BY created DESC";
        return queryRunner.query(connection, SQLQuery, new ScalarHandler<String>());
    }

    @SneakyThrows
    public static void clearDB() {
        var connection = getConnect();
        queryRunner.execute(connection, "DELETE FROM credit_request_entity");
        queryRunner.execute(connection, "DELETE FROM payment_entity");
        queryRunner.execute(connection, "DELETE FROM order_entity");
    }
}
