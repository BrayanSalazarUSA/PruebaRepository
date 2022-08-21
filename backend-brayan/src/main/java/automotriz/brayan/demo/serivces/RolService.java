/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automotriz.brayan.demo.serivces;

import automotriz.brayan.demo.entities.Rol;
import automotriz.brayan.demo.repositories.RolDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vinix
 */
@Service
public class RolService implements IRolService {

    @Autowired
    private RolDao rolDao;

    @Override
    public List<Rol> findAll() {
        return (List<Rol>) rolDao.findAll();
    }

    @Override
    public Rol save(Rol rol) {
        return rolDao.save(rol);
    }

}
