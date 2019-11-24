/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.List;

/**
 *
 * @author FAMSA
 */
public interface AdministradorDAO {
    boolean create( Admin admin) throws Exception;
    void update(Admin admin) throws Exception;
    Usuario get(long admin) throws Exception;
    boolean remove(int admin);
    List<Admin> findAll(int start, int count) throws Exception;
    
}
