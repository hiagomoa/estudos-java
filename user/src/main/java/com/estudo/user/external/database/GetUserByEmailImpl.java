package com.estudo.user.external.database;

import com.estudo.user.Entity.User;
import com.estudo.user.external.GetUserByEmail;
import com.estudo.user.jdbc.template.config.JDBCConfigs;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class GetUserByEmailImpl implements GetUserByEmail {


    @Override
    public User execute(String email) {
        JDBCConfigs jdbcConfigs = new JDBCConfigs();

        User user = new User();
        Statement stmt = null;
        Connection conn = null;
        try {
            conn = jdbcConfigs.connection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM USUARIOS.USUARIOS WHERE EMAIL = '" + email +"'";
            stmt.executeUpdate("GRANT CONNECT TO USUARIOS");
            stmt.execute("GRANT UNLIMITED TABLESPACE TO USUARIOS");
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                user.setEmail(rs.getString("email"));
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                System.out.println(" "+user.getId()+ " "+ user.getEmail() + " " + user.getName());
            }
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
        return user;
    }
}
