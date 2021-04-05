package com.estudo.user.external.database;

import com.estudo.user.Entity.User;
import com.estudo.user.jdbc.template.config.JDBCConfigs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

@Component
public class InsertUserInDataBase {

    @Autowired
    GetUserByEmailImpl getUserByEmail;

    public User execute(User userParam) {
        JDBCConfigs jdbcConfigs = new JDBCConfigs();

        User user = new User();
        Statement stmt = null;
        Connection conn = null;
        UUID uuid = UUID.randomUUID();
        System.out.println("MEU UUID: " + uuid);
        try {
            conn = jdbcConfigs.connection();
            stmt = conn.createStatement();
          String insert =  "INSERT INTO USUARIOS.USUARIOS (ID, NAME, EMAIL, PASSWORD) " +
                    "VALUES ('" + uuid +"', '"+ userParam.getName() +"', '"+ userParam.getEmail() +"', '"+ userParam.getPassword()+"')";
            //String sql = "SELECT * FROM USUARIOS.USUARIOS WHERE EMAIL = '" + email +"'";

            System.out.println("VALOR: "+insert);
            stmt.executeUpdate("GRANT CONNECT TO USUARIOS");
            stmt.execute("GRANT UNLIMITED TABLESPACE TO USUARIOS");
            stmt.executeQuery(insert);
            user = getUserByEmail.execute(userParam.getEmail());

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
        System.out.println("AAAAAAAA: "+ user.getEmail());
        return user;
    }

}
