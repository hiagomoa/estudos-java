package com.estudo.user;

import org.springframework.boot.SpringApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConfigs {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
   static final String DB_URL = "jdbc:oracle:thin:@localhost:49161/xe";
   // static final String DB_URL = "jdbc:oracle:thin:@localhost:49161:ORCL";

    //  Database credentials
    static final String USER = "system";
    static final String PASS = "oracle";

       public void conection() throws Exception {
        Connection conn = null;
        Statement stmt = null;
        try {
           Class.forName("oracle.jdbc.driver.OracleDriver");
            //Class.forName("oracle.jdbc.OracleDriver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();

            stmt.execute("GRANT CONNECT TO USUARIOS");
            stmt.execute("GRANT UNLIMITED TABLESPACE TO USUARIOS");
            stmt.executeUpdate("CREATE TABLE USUARIOS.USERS " +
                    "(NAME VARCHAR(256), EMAIL VARCHAR(256), PASSWORD VARCHAR(256), " +
                    "ID VARCHAR(256), PRIMARY KEY(ID))");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            throw new Exception("Excessão SQL");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("ERRO");

        } finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        //return stmt;
    }

         /*
            CREATE USER vaccine IDENTIFIED BY teste123;
*
            String sql = "CREATE TABLE DB.REGISTRATION " +
                    "(id INTEGER not NULL, " +
                    " first VARCHAR(255), " +
                    " last VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";

            //.execute();
            stmt.execute("GRANT CONNECT TO USUARIOS");
            stmt.execute("GRANT UNLIMITED TABLESPACE TO USUARIOS");
            stmt.executeUpdate("CREATE TABLE USUARIOS.BOAAAAA " +
                    "(COF_NAME VARCHAR(32), SUP_ID INTEGER, PRICE FLOAT, " +
                    "SALES INTEGER, TOTAL INTEGER)");
            System.out.println("Created table in given database...");*/
}