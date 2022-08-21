/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automotriz.brayan.demo.controllers;

import automotriz.brayan.demo.entities.Rol;
import automotriz.brayan.demo.serivces.RolService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200"})
public class RolController {

    @Autowired
    private RolService service;

    @GetMapping("/roles")
    public List<Rol> listar() {
        return service.findAll();
    }

    @PostMapping("/roles")
    public ResponseEntity<?> save(@RequestBody Rol rol) {

        Rol newRol = null;

        Map<String, Object> response = new HashMap<>();

        try {
            newRol = service.save(rol);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos: ");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El rol ha sido creado con exito");
        response.put("rol", newRol);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
}
