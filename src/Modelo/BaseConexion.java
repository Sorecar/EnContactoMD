package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseConexion {
    
    private final String base = "encontacto";
    private final String user = "root";
    private final String password = "1234";
    private final String url = "jdbc:mysql://localhost:3306/" + base + "?useTimezone=true&serverTimezone=UTC&useSSL=false";
    private Connection con = null;

    public Connection getConexion() {
        try {            
            con = (Connection) DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return con;
    }

    public void desconectar() throws SQLException {
        con.close();
    }

}
