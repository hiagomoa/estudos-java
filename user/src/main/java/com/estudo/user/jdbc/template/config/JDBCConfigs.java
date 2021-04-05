package com.estudo.user.jdbc.template.config;

import java.sql.*;

public class JDBCConfigs {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@localhost:49161:xe";

    //  Database credentials
    static final String USER = "system";
    static final String PASS = "oracle";

    public Connection connection() throws Exception {
        Connection conn = null;
         conn = DriverManager.getConnection(DB_URL, USER, PASS);
        return conn;
    }
}