package com.estudo.user.external.database;

import com.estudo.user.Entity.User;
import com.estudo.user.jdbc.template.config.JDBCConfigs;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

@Component
public class UpdateUserImpl {

    public void execute(User userParam) {
        JDBCConfigs jdbcConfigs = new JDBCConfigs();

        User user = new User();
        Statement stmt = null;
        Connection conn = null;
        UUID uuid = UUID.randomUUID();
        System.out.println("MEU UUID: " + uuid);
        PreparedStatement st = null;
        try {
            conn = jdbcConfigs.connection();
            stmt = conn.createStatement();
            st = conn.prepareStatement("UPDATE USUARIOS.USUARIOS SET PASSWORD = ? WHERE ID = ?");
            //String insert =  ;
            //String sql = "SELECT * FROM USUARIOS.USUARIOS WHERE EMAIL = '" + email +"'";

            //System.out.println("VALOR: "+insert);
            st.setString(1, userParam.getPassword());
            st.setString(2, userParam.getId());
            stmt.executeUpdate("GRANT CONNECT TO USUARIOS");
            stmt.execute("GRANT UNLIMITED TABLESPACE TO USUARIOS");
            st.executeUpdate();

        }  catch (Exception e) {
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
    }
}
