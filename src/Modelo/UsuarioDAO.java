package Modelo;

import java.util.List;

public interface UsuarioDAO {
    
    boolean create( Usuario usuario) throws Exception;
    void update(Usuario usuario) throws Exception;
    Usuario get(long usuario) throws Exception;
    boolean remove(String usuario);
    List<Usuario> findAll(int start, int count) throws Exception;
    
}
