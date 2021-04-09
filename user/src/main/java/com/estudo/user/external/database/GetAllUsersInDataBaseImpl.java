package com.estudo.user.external.database;

import com.estudo.user.Entity.User;
import com.estudo.user.external.GetAllUsersInDataBase;
import com.estudo.user.jdbc.template.config.JDBCConfigs;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class GetAllUsersInDataBaseImpl implements GetAllUsersInDataBase {

    @Override
    public List<User> findAll() {
        JDBCConfigs jdbcConfigs = new JDBCConfigs();
        List<User> users = new ArrayList<>();
        Statement stmt = null;
        Connection conn = null;

        try {
            conn = jdbcConfigs.connection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM USUARIOS.USUARIOS";

//            String sqlCategory = "CREATE TABLE USUARIOS.CATEGORIA(" +
//                    "ID INTEGER PRIMARY KEY, NOME VARCHAR(255), TIPO VARCHAR(255)"+")";
//            String newSql = "CREATE TABLE USUARIOS.TASKS (" +
//                    "ID INTEGER PRIMARY KEY, " +
//                    "NOME VARCHAR(255), " +
//                    "ID_USUARIO VARCHAR(255), " +
//                    "ID_CATEGORIA INTEGER, " +
//                    "FOREIGN KEY(ID_USUARIO) " +
//                    "REFERENCES USUARIOS.USUARIOS(ID), " +
//                    "FOREIGN KEY(ID_CATEGORIA) " +
//                    "REFERENCES USUARIOS.CATEGORIA(ID)" +
//                    ")";
            stmt.executeUpdate("GRANT CONNECT TO USUARIOS");

            stmt.execute("GRANT UNLIMITED TABLESPACE TO USUARIOS");
//            stmt.executeQuery(sqlCategory);
//            stmt.executeQuery(newSql);
            ResultSet rs = stmt.executeQuery(sql);

            User user;
            while (rs.next()) {
                user = new User();
                user.setEmail(rs.getString("email"));
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
            System.out.println("USERS: "+users);
            rs.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
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
        return users;
    }
}
