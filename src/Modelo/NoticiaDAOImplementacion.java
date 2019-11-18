package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class NoticiaDAOImplementacion implements NoticiaDAO {

    BaseConexion base = new BaseConexion();
    Connection conexion = base.getConexion();
    PreparedStatement ps;

    boolean bandera = false;

    @Override
    public boolean create(Noticia noticia) throws Exception {
        Date fecha = new Date();
        String INSERT = "INSERT INTO noticia (idNoticia, usuario, Noticia, Fecha) VALUES (NULL, ?, ?, NOW())";
        try {
            ps = conexion.prepareStatement(INSERT);
            ps.setString(1, noticia.getUsuario());
            ps.setString(2, noticia.getNoticia());

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
    public void update(Noticia noticia) throws Exception {
        String UPDATE = "UPDATE noticia SET noticia=? WHERE idnoticia=?";
        try {
            ps = conexion.prepareStatement(UPDATE);

            ps.setString(1, noticia.getNoticia());
            ps.setInt(2, noticia.getId());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Error desde aca");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Noticia get(long noticia) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean remove(int noticia) {
        String DELETE = "DELETE FROM noticia WHERE idnoticia=?";
        try {
            ps = conexion.prepareStatement(DELETE);
            ps.setInt(1, noticia);

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
    public List<Noticia> findAll(int start, int count) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
