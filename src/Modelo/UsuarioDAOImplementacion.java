package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UsuarioDAOImplementacion implements UsuarioDAO {

    BaseConexion base = new BaseConexion();
    Connection conexion = base.getConexion();
    PreparedStatement ps;

    @Override
    public boolean create(Usuario usuario) throws Exception {
        boolean bandera = false;
        String INSERT = "INSERT INTO usuarios (id, usuario, contraseña, Telefono, Mascota, Estatus) VALUES (NULL, ?, ?, ?, ?, 'Activo')";
        try {
            ps = conexion.prepareStatement(INSERT);
            
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getContraseña());
            ps.setString(3, usuario.getTelefono());
            ps.setString(4, usuario.getMascota());

            ps.executeUpdate();
            ps.close();
            bandera = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return bandera;
        }
    }

    @Override
    public void update(Usuario usuario) throws Exception {
        String UPDATE = "UPDATE usuarios SET usuario=?, contraseña=?, Telefono=?, Mascota=? WHERE id=?";
        try {
            PreparedStatement ps = conexion.prepareStatement(UPDATE);

            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getContraseña());
            ps.setString(3, usuario.getTelefono());
            ps.setString(4, usuario.getMascota());
            ps.setInt(5, usuario.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Usuario get(long usuario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean remove(String usuario) {
        boolean bandera = false;
        String DELETE = "DELETE FROM usuarios WHERE Usuario=?";
        try {
            ps = conexion.prepareStatement(DELETE);
            ps.setString(1, usuario);

            ps.executeUpdate();
            ps.close();
            bandera = true;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            return bandera;
        }
    }

    @Override
    public List<Usuario> findAll(int start, int count) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
