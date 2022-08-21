
package automotriz.brayan.demo.serivces;

import automotriz.brayan.demo.entities.Rol;
import java.util.List;


public interface IRolService {
    
    public List<Rol> findAll();
    
    public Rol save(Rol rol);
    
}
