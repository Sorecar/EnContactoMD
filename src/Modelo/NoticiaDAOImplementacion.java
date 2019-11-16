package Modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class NoticiaDAOImplementacion implements NoticiaDAO{
    
    BaseConexion base = new BaseConexion();
    Connection conexion = base.getConexion();
    Statement stm;

    @Override
    public boolean create(Noticia noticia) throws Exception {
        boolean bandera=false;
        try{
            stm = conexion.createStatement();
            String query = "INSERT INTO noticias(Usuario,Noticia,HoraNoticia,FechaNoticia)"
                    + " VALUES('"+noticia.getUsuario()+"', '"+noticia.getNoticia()+"', '"+noticia.getHoraNoticia()+"', '"+noticia.getFechaNoticia()+"')";
            stm.execute(query);
            bandera=true;
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
        throw new UnsupportedOperationException("Not supported yet.");
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
