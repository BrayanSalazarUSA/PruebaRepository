/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automotriz.brayan.demo.repositories;

import automotriz.brayan.demo.entities.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vinix
 */
@Repository
public interface ProductoDao extends CrudRepository<Producto, Integer> {

//    @Query("select p from Producto p where p.nombre like %?1%")
//    public List<Producto> findByNombre(String term);
    
    public List<Producto> findByNombreLikeIgnoreCase(String term);
}
