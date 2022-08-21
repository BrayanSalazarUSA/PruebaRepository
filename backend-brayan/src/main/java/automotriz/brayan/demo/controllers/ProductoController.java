package automotriz.brayan.demo.controllers;

import automotriz.brayan.demo.entities.Producto;
import automotriz.brayan.demo.serivces.ProductoService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @GetMapping("/productos")
    public List<Producto> index(@Param("palabraClave") String palabraClave) {
        return service.findAll(palabraClave);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<?> show(@PathVariable Integer id) {

        Producto producto = null;
        Map<String, Object> response = new HashMap<>();

        try {
            producto = service.findById(id);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos: ");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (producto == null) {
            response.put("mensaje", "El producto ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Producto>(producto, HttpStatus.OK);
    }

    @PostMapping("/productos")
    public ResponseEntity<?> create(@Valid @RequestBody Producto producto, BindingResult result) {
        Producto productoNew = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo " + err.getField() + " " + err.getDefaultMessage()).collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {

            productoNew = service.save(producto);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos: ");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El producto ha sido creado con exito");
        response.put("cliente", productoNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Producto producto, BindingResult result, @PathVariable Integer id) {

        Producto productoActual = service.findById(id);

        Producto productoUpdated = null;

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (productoActual == null) {
            response.put("mensaje", "Error: no se pudo editar, el cliente ID: "
                    .concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {

            productoActual.setNombre(producto.getNombre());
            productoActual.setCantidad(producto.getCantidad());
            productoActual.setFechaIngreso(producto.getFechaIngreso());
            productoActual.setUsuario(producto.getUsuario());

            productoUpdated = service.save(productoActual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el producto en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El producto ha sido actualizado con éxito!");
        response.put("producto", productoUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();

        try {

            service.delete(id);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el iproducto de la base de datos: ");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El producto ha sido eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
}
