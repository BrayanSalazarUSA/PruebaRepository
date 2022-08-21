/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automotriz.brayan.demo.serivces;

import automotriz.brayan.demo.entities.Producto;
import automotriz.brayan.demo.repositories.ProductoDao;
import java.util.List;
import javax.management.RuntimeErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

/**
 *
 * @author vinix
 */
@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoDao productoDao;

    @Override
    public List<Producto> findAll(String palabraClave) {
        if(palabraClave != null){
            return (List<Producto>) productoDao.findByNombreLikeIgnoreCase("%"+palabraClave+"%");
        }
        return (List<Producto>) productoDao.findAll();
    }

    @Override
    public Producto save(Producto producto) {
        return productoDao.save(producto);
    }

    @Override
    public void delete(int id) {
        productoDao.deleteById(id);
    }

    @Override
    public Producto findById(Integer id) {
        return productoDao.findById(id).orElse(null);
    }

}
