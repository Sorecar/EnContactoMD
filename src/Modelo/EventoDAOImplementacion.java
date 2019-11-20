package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

public class EventoDAOImplementacion implements EventoDAO {
    
    BaseConexion base = new BaseConexion();
    Connection conexion = base.getConexion();
    PreparedStatement ps;

    boolean bandera = false;

    @Override
    public boolean create(Evento evento) throws Exception {
        String INSERT = "INSERT INTO eventos (Id, Nombre, Contacto, Fecha, Hora, Lugar, Descripcion, Usuario) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = conexion.prepareStatement(INSERT);
            ps.setString(1, evento.getNombre());
            ps.setString(2, evento.getContacto());
            ps.setString(3, evento.getFecha());
            ps.setString(4, evento.getHora());
            ps.setString(5, evento.getLugar());
            ps.setString(6, evento.getDescripcion());
            ps.setString(7, evento.getUsuario());

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
    public void update(Evento evento) throws Exception {
        String UPDATE = "UPDATE eventos SET Nombre=?, Contacto=?, Fecha=?, Hora=?, Lugar=?, Descripcion=? WHERE Id=?";
        try {
            ps = conexion.prepareStatement(UPDATE);

            ps.setString(1, evento.getNombre() );
            ps.setString(2, evento.getContacto());
            ps.setString(3, evento.getFecha());
            ps.setString(4, evento.getHora());
            ps.setString(5, evento.getLugar());
            ps.setString(6, evento.getDescripcion());
            ps.setInt(7, evento.getId());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Error desde aca");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Usuario get(long evento) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean remove(int evento) {
        String DELETE = "DELETE FROM eventos WHERE Id=?";
        try {
            ps = conexion.prepareStatement(DELETE);
            ps.setInt(1, evento);

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
    public List<Evento> findAll(int start, int count) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
