package com.estudo.user.external.database;

import com.estudo.user.Entity.User;
import com.estudo.user.external.GetUserByEmail;
import com.estudo.user.jdbc.template.config.JDBCConfigs;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class GetUserByEmailAndPasswordImpl{

    public User execute(String email, String password) {
        JDBCConfigs jdbcConfigs = new JDBCConfigs();

        User user = new User();
        Statement stmt = null;
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = jdbcConfigs.connection();
            stmt = conn.createStatement();
            st = conn.prepareStatement("SELECT * FROM USUARIOS.USUARIOS WHERE EMAIL = ? AND PASSWORD = ?");
            st.setString(1, email);
            st.setString(2, password);
            stmt.executeUpdate("GRANT CONNECT TO USUARIOS");
            stmt.execute("GRANT UNLIMITED TABLESPACE TO USUARIOS");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                user.setEmail(rs.getString("email"));
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
            }

            System.out.println("USERS: "+user);
            rs.close();
            return user;

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

        return null;
    }
}
