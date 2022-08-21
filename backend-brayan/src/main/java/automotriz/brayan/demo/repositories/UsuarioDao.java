/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automotriz.brayan.demo.repositories;

import automotriz.brayan.demo.entities.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vinix
 */
@Repository
public interface UsuarioDao extends CrudRepository<Usuario, Integer>{
    
}
