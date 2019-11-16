package Modelo;

import java.util.List;

public interface EventoDAO {
    
    boolean create( Evento evento) throws Exception;
    void update(Evento evento) throws Exception;
    Usuario get(long evento) throws Exception;
    boolean remove(int evento);
    List<Evento> findAll(int start, int count) throws Exception;
    
}
