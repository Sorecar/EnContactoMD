package Modelo;

import java.util.List;

public interface NoticiaDAO {
    
    boolean create( Noticia noticia) throws Exception;
    void update(Noticia noticia) throws Exception;
    Noticia get(long noticia) throws Exception;
    boolean remove(int noticia);
    List<Noticia> findAll(int start, int count) throws Exception;
    
}
