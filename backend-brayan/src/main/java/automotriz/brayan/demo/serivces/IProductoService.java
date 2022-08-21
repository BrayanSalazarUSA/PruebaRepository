
package automotriz.brayan.demo.serivces;

import automotriz.brayan.demo.entities.Producto;
import java.util.List;


public interface IProductoService {
    
    public List<Producto> findAll(String palabraClave);
    
    public Producto save(Producto producto);
    
    public void delete(int id);
    
    public Producto findById(Integer id);
    
}
