/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author FAMSA
 */
public class AdminDAOImplementacion implements AdministradorDAO{
    BaseConexion base = new BaseConexion();
    Connection conexion = base.getConexion();
    PreparedStatement ps;

    boolean bandera = false;

    @Override
    public boolean create(Admin admin) throws Exception {
        String INSERT = "INSERT INTO administrador (Id, Nombre, Contraseña) VALUES (NULL, ?, ?)";
        try {
            ps = conexion.prepareStatement(INSERT);
            ps.setString(1, admin.getNombre());
            ps.setString(2, admin.getContraseña());
            ps.executeUpdate();
            ps.close();
            bandera = true;
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            return bandera;
        }
    }

    @Override
    public void update(Admin admin) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario get(long admin) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(int admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Admin> findAll(int start, int count) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
