import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

public class SampleJDBCTest {
    Connection conn = null;
    Statement stmt = null;
    ResultSet result;
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/test";

    static final String USER = "sayali";
    static final String PASS = "Sayali";

    @Before
    public void setUp() throws Exception {
        System.out.println("Setup is running");
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String jCustomer = "create table jCustomer(" +
                    "cust_id int primary key auto_increment," +
                    "cust_name char(40)," +
                    "address char(100)," +
                    "phone_no char(20)" +
                    ");";
            String jOrder = "create table jOrders(" +
                    "cust_id int," +
                    "order_id int primary key auto_increment," +
                    "total_bill int," +
                    "date_of_order date" +
                    ");";
            String jPayment = "create table jPayment(" +
                    "payment_status char(1)," +
                    "payment_id int primary key auto_increment," +
                    "order_id int," +
                    "date_of_payment date" +
                    ");";
            String jProducts = "create table jProducts(" +
                    "product_id int primary key auto_increment," +
                    "product_name char(30)," +
                    "unit_price int" +
                    ");";
            String jOrderItem = "create table jOrderItem(\n" +
                    "order_item int primary key auto_increment,\n" +
                    "order_id int," +
                    "price int," +
                    "quantity int," +
                    "product_id int" +
                    ");";

            stmt.execute(jCustomer);
            stmt.execute(jOrder);
            stmt.execute(jOrderItem);
            stmt.execute(jPayment);
            stmt.execute(jProducts);
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("tear down is running");
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql1 = "drop table jCustomer";
            String sql2 = "drop table jOrders";
            String sql3 = "drop table jOrderItem";
            String sql4 = "drop table jPayment";
            String sql5 = "drop table jProducts";
            stmt.execute(sql1);
            stmt.execute(sql2);
            stmt.execute(sql3);
            stmt.execute(sql4);
            stmt.execute(sql5);
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testForGetConnection() throws Exception {
        Class.forName("org.mariadb.jdbc.Driver");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();
        String insertQuery = "insert into jCustomer(cust_name,address,phone_no)" +
                " values('Sayali','Bhosari, Pune','1234567890')";
        int affectedRows = stmt.executeUpdate(insertQuery);
        Assert.assertEquals(1,affectedRows);

    }
}