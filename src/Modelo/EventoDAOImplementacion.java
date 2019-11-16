package Modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EventoDAOImplementacion implements EventoDAO {
    
    BaseConexion base = new BaseConexion();
    Connection conexion = base.getConexion();
    Statement stm;

    @Override
    public boolean create(Evento evento) throws Exception {
        boolean bandera=false;
        try{
            stm = conexion.createStatement();
            String query = "INSERT INTO eventos(ConInfo,FechaEvento,HoraEvento,Evento)"
                    + " VALUES('"+evento.getConInfo()+"', '"+evento.getFechaEvento()+"', '"+evento.getHoraEvento()+"', '"+evento.getEvento()+"')";
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
    public void update(Evento evento) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Usuario get(long evento) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean remove(int evento) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Evento> findAll(int start, int count) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
