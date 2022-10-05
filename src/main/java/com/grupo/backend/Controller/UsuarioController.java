package com.grupo.backend.Controller;

import com.grupo.backend.Model.Persona;
import com.grupo.backend.Repository.PersonaRepository;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.mindrot.jbcrypt.BCrypt;

@Controller
public class UsuarioController extends ControllerGenerico{
    @Autowired
    private PersonaRepository personaRepository;

    
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/login")
    public @ResponseBody ResponseEntity<String> login(@RequestBody Persona credenciales) {
        ResponseEntity<String> error = this.validar(credenciales);

        if (error != null) {
            return error;
        }

        String jws = this.generarToken();

        return new ResponseEntity<>("\"" + jws + "\"", HttpStatus.OK); // se escapan las comillas para que sean parte del retorno
    }

    private ResponseEntity<String> validar(Persona credenciales) {
        ResponseEntity<String> error = null;
        String usuario = credenciales.getUsuario();
        String clave = credenciales.getClave();
        Persona persona;

        if (usuario == null || usuario.length() < 1) {
            error = new ResponseEntity<String>("Usuario no puede estar vacio", HttpStatus.BAD_REQUEST);    
        }

        if (clave == null || clave.length() < 1) {
            error = new ResponseEntity<String>("Clave no puede estar vacia", HttpStatus.BAD_REQUEST);    
        }

        // Que el usuario exista
        persona = personaRepository.getPersonaByUsuario(usuario);
        if (persona == null) {
            error = new ResponseEntity<String>("Usuario o clave incorrecto", HttpStatus.UNAUTHORIZED);
        }

        // Que la clave sea correcta
        if (!BCrypt.checkpw(clave, persona.getClave())) {
            error = new ResponseEntity<String>("Usuario o clave incorrecto", HttpStatus.UNAUTHORIZED);
        }

        return error;
    }
}
