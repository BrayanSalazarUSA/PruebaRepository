
package automotriz.brayan.demo.serivces;

import automotriz.brayan.demo.entities.Usuario;
import java.util.List;


public interface IUsuarioService {
    
    public List<Usuario> findAll();
    
    public Usuario save(Usuario usuario);
    
    public Usuario findById(Integer id);
}
