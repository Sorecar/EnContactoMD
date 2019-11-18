package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class NoticiaDAOImplementacion implements NoticiaDAO{
    
    BaseConexion base = new BaseConexion();
    Connection conexion = base.getConexion();
    PreparedStatement ps;
    
    @Override
    public boolean create(Noticia noticia) throws Exception {
        boolean bandera=false;
        Date fecha = new Date();
        String INSERT = "INSERT INTO noticia (idNoticia, usuario, Noticia, Fecha) VALUES (NULL, ?, ?, NOW())";
        try{
            ps = conexion.prepareStatement(INSERT);
            ps.setString(1, noticia.getUsuario());
            ps.setString(2, noticia.getNoticia());
            
            ps.executeUpdate();
            ps.close();
        }catch(SQLException ex) {
            System.out.println(ex);
            bandera=false;
        }
        finally{
            return bandera;
        }
    }

    @Override
    public void update(Noticia noticia) throws Exception {
        String UPDATE = "UPDATE noticia SET noticia=? WHERE idnoticia=?";
        try {
            PreparedStatement ps = conexion.prepareStatement(UPDATE);

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
    public Usuario get(long noticia) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean remove(int noticia) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Noticia> findAll(int start, int count) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
